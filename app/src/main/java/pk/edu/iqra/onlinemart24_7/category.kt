package pk.edu.iqra.onlinemart24_7

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_category.*
import android.widget.LinearLayout
import android.widget.ListView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.electronic_category_data.*
import java.lang.reflect.Array

class category : AppCompatActivity() {
    //Array of electronics names
//    var title = arrayOf("Samsung Evo", "Samsung Watch 3")

    //Array of electronics images
//    var image = intArrayOf(R.drawable.evo256gb_microsdxc, R.drawable.galaxywatch3)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)


//        for home page button
        homebutton.setOnClickListener{
            showToast("Home Page")
            val intent = Intent(this, HomepageActivity::class.java)
            startActivity(intent)
        }


//        ele_cat.setOnClickListener{
//            val intent = Intent(this, electronic_category_data::class.java)
//            startActivity(intent)
//            showToast("")
//        }

//        ssd_image.setOnClickListener{
//            showToast("Item Selected!")
//
//        }

    }

//    @SuppressLint("WrongConstant")
//    fun electronic_Cat() {
//        ele_cat.setOnClickListener{
//            //        finding view
//        val recyclerViewhello = findViewById<ListView>(R.id.RecyclerView)
//
////          item decorator to separate the items
//        DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL)
//
//        LinearLayoutManager(this, LinearLayout.HORIZONTAL,false)
//
//        initialize product list
//        var productList = ArrayList<String>()
//        for (i in 0 until title.sizes) {
//
//        }
//    }
//
//
//        }
}