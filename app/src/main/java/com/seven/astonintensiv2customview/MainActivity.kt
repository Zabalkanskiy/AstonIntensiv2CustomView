package com.seven.astonintensiv2customview

import android.animation.Animator
import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import coil.ImageLoader
import coil.load
import coil.request.CachePolicy
import com.seven.astonintensiv2customview.databinding.ActivityMainBinding
import com.seven.astonintensiv2customview.model.CustomViewModelFactory
import com.seven.astonintensiv2customview.model.CustomViewRepository
import com.seven.astonintensiv2customview.model.CustomViewViewModel
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var customViewViewModel: CustomViewViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)

        val viewModelFactory = CustomViewModelFactory(CustomViewRepository())
         customViewViewModel = ViewModelProvider(this, viewModelFactory).get(CustomViewViewModel::class.java)

        customViewViewModel.setRotateScreenFalse()
        if(customViewViewModel.getCurrentAngle()!=0f){

        }
        val rainbowView = findViewById<RainbowViev>(R.id.main_activity_rainbow_view)


        //for not change image when rotate screen
        val imgLoader = ImageLoader.Builder(this)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .diskCachePolicy(CachePolicy.ENABLED)
            .build()


        //binding.gradus.text = rainbowView.rotationAngle.toString()
        binding.activityMainButtonRotate.setOnClickListener {

            val randomAngel = Random.nextFloat()*360 + 360f
            customViewViewModel.setFinalAngle(randomAngel)
            customViewViewModel.setRotateScreenTrue()

            rotateView(rainbowView, startDegrees = 0f, finalDegrees = customViewViewModel.getFinalAngle())
        }

        customViewViewModel.getAngleInRepository().observe(this) { angle ->
            val rightAngle = angle - 360f
            val sectorAngle = 360f / 7
            val colorInt = (rightAngle/sectorAngle).toInt()
            if (colorInt%2==0){
                binding.activityMainImageView.visibility = View.INVISIBLE
                binding.activityMainTextViewText.visibility = View.VISIBLE
                binding.activityMainTextViewText.text = "Text Text Text Text"
            } else{
                //binding.activityMainImageView.
                binding.activityMainImageView.visibility = View.VISIBLE
                binding.activityMainTextViewText.visibility = View.INVISIBLE
            //    if (customViewViewModel.getRotateSceen()) {
                    binding.activityMainImageView.load(customViewViewModel.getPlaceHolderUrl()) {
                        memoryCachePolicy(
                            //CachePolicy.DISABLED
                            CachePolicy.ENABLED
                        )
                      //  diskCachePolicy(CachePolicy.DISABLED)
                        diskCachePolicy(CachePolicy.ENABLED)
                    }
            //    }
            }
        }

        binding.mainActivityButtonRefresh.setOnClickListener {
            binding.activityMainImageView.visibility = View.INVISIBLE
            binding.activityMainTextViewText.visibility = View.INVISIBLE

        }
    }



    private fun rotateView(view: View, startDegrees: Float, finalDegrees: Float) {
      //  var currentRotation = 0f
        // Создаем анимацию вращения на 360 градусов
        val rotateAnimator = ObjectAnimator.ofFloat(view, View.ROTATION, startDegrees, finalDegrees)
        rotateAnimator.duration = 3000 // Длительность анимации в миллисекундах
        rotateAnimator.interpolator = AccelerateDecelerateInterpolator()
        // Добавляем слушатель для отслеживания угла вращения
        rotateAnimator.addUpdateListener { animation ->
         //    currentRotation = animation.animatedValue as Float

            customViewViewModel.setCurrentAngle(animation.animatedValue as Float)
            // Здесь вы можете использовать текущий угол вращения для дополнительных действий
            // Например, выводить его в лог или обновлять интерфейс
           // println("Current Rotation: $currentRotation degrees")
        }

        rotateAnimator.addListener(object : Animator.AnimatorListener{
            override fun onAnimationStart(animation: Animator) {
            }

            override fun onAnimationEnd(animation: Animator) {

                customViewViewModel.setAngleInRepository(customViewViewModel.getCurrentAngle())
            }

            override fun onAnimationCancel(animation: Animator) {
            }

            override fun onAnimationRepeat(animation: Animator) {
            }

        })

        // Запускаем анимацию
        rotateAnimator.start()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)

    }

}