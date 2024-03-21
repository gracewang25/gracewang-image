package com.example.gracewang_image

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var imageView: ImageView
    lateinit var editText: EditText
    private val imageIds = listOf(R.drawable.image1, R.drawable.image2, R.drawable.image3)
    private var currentImageResId: Int = R.drawable.default_image

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)
        editText = findViewById(R.id.editText)
        val button: Button = findViewById(R.id.button)

        button.setOnClickListener {
            val randomIndex = Random.nextInt(imageIds.size)
            currentImageResId = imageIds[randomIndex]
            imageView.setImageResource(currentImageResId)
        }

        val sharedPref = getSharedPreferences("AppPrefs", MODE_PRIVATE)
        editText.setText(sharedPref.getString("text", ""))
        imageView.setImageResource(sharedPref.getInt("image", R.drawable.default_image))
    }

    override fun onDestroy() {
        super.onDestroy()

        val sharedPref = getSharedPreferences("AppPrefs", MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("text", editText.text.toString())
            putInt("image", currentImageResId)
            apply()
        }

    }
}
