//sign in activity main activity
package pk.edu.iqra.onlinemart24_7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signin.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        // to launch signup activity
        btnNotRegistered.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }


//to validate all the null fields in signin form and after signin Intent to homepage activity

        btnsignin.setOnClickListener{
//          calling SignInAuth
            SignInAuth()
        }

    }

//    fun for SignIn Authentication using firebase Authentication
    private fun SignInAuth() {
    //        to get text
    val eml = Utils.getText(ed_signin_Email)
    val psw = Utils.getText(ed_signin_Password)

    Log.d("SignIn_Activity", "Email: $eml")
    Log.d("SignIn_Activity", "Password: $psw")


    //  to validate null fields
    if (Utils.validate(eml, psw)) {
//        showToast("SignIn Successfully!")
//                val intent2 = Intent(this, HomepageActivity::class.java)
//                startActivity(intent2)

//        firebase authentication using email and password-sign In activity

        FirebaseAuth.getInstance().signInWithEmailAndPassword(eml, psw)
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener

//                else if successful
                Log.d("SignIn", "Successfully SignIn with uid: ${it.result?.user?.uid}")
                showToast("SignIn Successfully!")



//                after signup intent to sign in form
                val to_homepage = Intent(this, HomepageActivity::class.java)
//                this helps us when we go back in our activity it exit from current activity(e.g: user signIn account) and also to clear back stack
                to_homepage.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(to_homepage)

                onStart()
            }

//                  message for  the unsuccessful SignIn
//                  lambda expression
            .addOnFailureListener {
                Log.d("SignIn", "Failed To SignIn To Account, ${it.message}")
                showToast("Failed To SignIn To Account, ${it.message}")
            }
    }

        else {
            showToast("Please fill all the empty fields!")
        }
    }
}