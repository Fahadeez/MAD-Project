package pk.edu.iqra.onlinemart24_7

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.menu_header.*
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList


class HomepageActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var ref: DatabaseReference
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var sharedPreferences: SharedPreferences;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        sharedPreferences = getSharedPreferences(Utils.AUTH_FILE, Context.MODE_PRIVATE)

//    fun for user logged validation
        User_Logged_Validation()


        btn_topdeal1.setOnClickListener {
            showToast("Pixal 4a")
        }

//        for toolbar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

<<<<<<< HEAD
        btn_electronics_home.setOnClickListener {
            Intent(this, electronic_category_data::class.java).apply {
                startActivity(this)
                finish()
            }
        }
        btn_health_ctg_home.setOnClickListener {
            Intent(this, HealthCategory::class.java).apply {
                startActivity(this)
                finish()
            }
        }
=======
>>>>>>> 41ce66772360b7fb44887c8fbde7372ba8a84cba

        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.nav_view)

        val Toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )

        drawerLayout.addDrawerListener(Toggle)
        Toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)

//        menu header - user image
//        btnuser_profile_navbar.setOnClickListener{
//            showToast("Add Image")
//        }

//        for username
        auth = Firebase.auth
        database = Firebase.database
    }

    //    fun for user logged validation
    private fun User_Logged_Validation() {
//    check to see the user is logged or not
        val uid = FirebaseAuth.getInstance().uid
        if (uid == null) {
            val intent = Intent(this, SignupActivity::class.java)
//     to clear the back stack
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        for menu
        when (item.itemId) {

            R.id.Cat_items -> {
//                sub categories
                val intent = Intent(this, category::class.java)
                showToast("Categories")
                startActivity(intent)
            }
        }

        when (item.itemId) {
            R.id.cat_cart -> {
                val intent = Intent(this, Cart::class.java)
                showToast("My Cart")
                startActivity(intent)
            }
        }

        when (item.itemId) {

            R.id.cat_order -> {
                val intent = Intent(this, ItemList::class.java)
                showToast("Your Orders!")
                startActivity(intent)
            }
        }

        when (item.itemId) {

            R.id.cat_Acc -> {
                val intent = Intent(this, activity_account_setting::class.java)
                showToast("Account Setting")
                startActivity(intent)
            }
        }

        when (item.itemId) {

            R.id.cat_E_Wallet -> {
                val intent = Intent(this, mywallet::class.java)
                showToast("My E-Wallet")
                startActivity(intent)
            }
        }

        when (item.itemId) {

            R.id.cat_About -> {
                val intent = Intent(this, aboutUs::class.java)
                showToast("About Us")
                startActivity(intent)
            }
        }

//            for the user logout
        when (item.itemId) {
            R.id.cat_Logout -> {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, MainActivity::class.java)
                showToast("Logout Successfully")
                startActivity(intent)
            }
        }
        return true
    }


    //    to display username
    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
<<<<<<< HEAD

=======
>>>>>>> 41ce66772360b7fb44887c8fbde7372ba8a84cba
        ref = database.getReference("users")
        if (currentUser != null) {
            ref.child(currentUser.uid).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val user = snapshot.getValue(Users::class.java)
                    val name: String? = user?.username

                    if(!name.isNullOrEmpty()){
                        showToast("Welcome ${name}")

//                    to show in navigation menu
                        main_username?.text = name

//                    to show in homepage footer and categories footer
                        txt_footer_homepage?.text = name
                    }


                }

                override fun onCancelled(snapshot: DatabaseError) {
//                    show error here
                }
            })
        }
    }
}
