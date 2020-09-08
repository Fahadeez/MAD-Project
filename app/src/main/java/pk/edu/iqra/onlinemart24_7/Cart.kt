package pk.edu.iqra.onlinemart24_7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cart.*

class Cart : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)


//        for add to cart
        btn_placeorder.setOnClickListener{
            showToast("opening add to cart page")
            val intent = Intent(this, checkout::class.java)
            startActivity(intent)
        }
    }
}