package pk.edu.iqra.onlinemart24_7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_checkout.*

class checkout : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

//        for homepage button
        homebutton.setOnClickListener{
            showToast("Home Page")
            val intent = Intent(this, HomepageActivity::class.java)
            startActivity(intent)
        }
    }
}