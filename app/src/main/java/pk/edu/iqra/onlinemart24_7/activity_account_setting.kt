package pk.edu.iqra.onlinemart24_7

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageTask
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.activity_account_setting.*
import kotlinx.android.synthetic.main.activity_category.homebutton
import kotlinx.android.synthetic.main.menu_header.*
import java.util.*

class activity_account_setting : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var ref: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var storage: FirebaseStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_setting)

//        for home page button
        homebutton.setOnClickListener{
            showToast("Home Page")
            val intent = Intent(this, HomepageActivity::class.java)
            startActivity(intent)
        }


//        for user image
//        btnuser_profile_pic
        userimage_acc_setting.setOnClickListener{
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
            showToast("Opening Images")
        }


//        save user image to firebase storage
        btnuserimageedit_acc.setOnClickListener{
//            SaveUserImageToFirebaseStorage(it.toString())
            uploadphoto()
            showToast("Photo Successfully Uploaded")
        }

//        to display the image
        auth = Firebase.auth
        storage = FirebaseStorage.getInstance()
        database = Firebase.database

//        for acc settings
        ed_accsett_username.isEnabled = false
        ed_accsett_city.isEnabled = false
        ed_accsett_country.isEnabled = false
        ed_address.isEnabled = false


        btnusernameedit_acc.setOnClickListener{
            ed_accsett_username.isEnabled = true
        }

        btncityedit_acc.setOnClickListener{
            ed_accsett_city.isEnabled = true
        }

        btncountryedit_acc.setOnClickListener{
            ed_accsett_country.isEnabled = true
        }

        btnaddressedit_acc.setOnClickListener{
            ed_address.isEnabled = true
        }

//        for update
        btnusernamesave_acc.setOnClickListener{
            val uid = FirebaseAuth.getInstance().uid ?: ""
            val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")

            val usr = Utils.getText(ed_accsett_username)

            ref.child(uid).child("username").setValue(usr).toString()

//            after update disabled the edit text
            ed_accsett_username.isEnabled = false
        }

        btncitysave_acc.setOnClickListener{
            val uid = FirebaseAuth.getInstance().uid ?: ""
            val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")
            val city = Utils.getText(ed_accsett_city)

            ref.child(uid).child("city").setValue(city).toString()

//            after update disabled the edit text
            ed_accsett_city.isEnabled = false
        }

        btncountrysave_acc.setOnClickListener{
            val uid = FirebaseAuth.getInstance().uid ?: ""
            val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")
            val country = Utils.getText(ed_accsett_country)

            ref.child(uid).child("country").setValue(country).toString()

//            after update disabled the edit text
            ed_accsett_country.isEnabled = false
        }

        btnaddresssave_acc.setOnClickListener{
            val uid = FirebaseAuth.getInstance().uid ?: ""
            val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")
            val address = Utils.getText(ed_address)

            ref.child(uid).child("address").setValue(address).toString()

//            after update disabled the edit text
            ed_address.isEnabled = false
        }

        database = Firebase.database
    }

        var selectedPhotoUri: Uri? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            selectedPhotoUri = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)

            round_user_image.setImageBitmap(bitmap)
            userimage_acc_setting.alpha = 0f
        }
    }



    private fun uploadphoto() {
        if (selectedPhotoUri != null) {

            val filename = UUID.randomUUID().toString()
            val ref = FirebaseStorage.getInstance().getReference("/usersimages/$filename")

            val task: StorageTask<*>
            task = ref.putFile(selectedPhotoUri!!)


            task.continueWithTask(Continuation <UploadTask.TaskSnapshot, Task<Uri>> { task ->
                if(!task.isSuccessful){
                    task.exception?.let {
                        throw it
                    }
                }
                return@Continuation ref.downloadUrl
            }).addOnCompleteListener{ task ->
                if(task.isSuccessful){
                    val downloadUrl = task.result
                    val url = downloadUrl.toString()
                    Log.d("url", url)

                    Glide.with(baseContext)
                        .load(downloadUrl)
                        .into(userimage_acc_setting)
                }
            }
        }
    }

//    to display user info in edit text
    override fun onStart() {
        super.onStart()
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val currentUser = auth.currentUser
        ref = database.getReference("users/$uid")
        if (currentUser != null) {
            ref.child(currentUser.uid).addValueEventListener(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val user = snapshot.getValue(Users::class.java)
                    val username = user?.username
                    val city = user?.city
                    val country = user?.country
                    val address = user?.address

//                    to show in navigation menu
                    ed_accsett_username.setText(username)
                    ed_accsett_city.setText(city)
                    ed_accsett_country.setText(country)
                    ed_address.setText(address)

                }
                override fun onCancelled(snapshot: DatabaseError) {
//                    show error here
                }
            })
        }
    }
}
