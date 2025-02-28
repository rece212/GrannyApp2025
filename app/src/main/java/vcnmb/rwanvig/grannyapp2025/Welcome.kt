package vcnmb.rwanvig.grannyapp2025

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.concurrent.Executors

class Welcome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var tvOutput :TextView = findViewById(R.id.tvWelcome)
        tvOutput.setText("Welcome :\n"+arrUser[SignedIN].Name)

        var image: Bitmap? =null
        val imOutput :ImageView = findViewById(R.id.imWelcome)
        val handler = Handler(Looper.getMainLooper())
        val executor = Executors.newSingleThreadExecutor()
        executor.execute {
            val imageURL = arrUser[SignedIN].imageURL
            try {
                val `in` = java.net.URL(imageURL).openStream()
                image = BitmapFactory.decodeStream(`in`)
                Log.d("Welcome","Image has been added  "+image.toString())
                handler.post{
                    Log.d("Welcome","Image has been added")
                    imOutput.setImageBitmap(image)
                }
            }
            catch (e:java.lang.Exception)
            {
                Log.d("Welcome","Error happened ...oh no .."+e.toString())
                e.printStackTrace()
            }


        }

    }
}