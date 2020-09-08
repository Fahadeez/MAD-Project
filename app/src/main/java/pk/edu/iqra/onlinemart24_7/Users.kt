package pk.edu.iqra.onlinemart24_7

import com.google.firebase.database.IgnoreExtraProperties

// only class with parameters for the firebase database.
@IgnoreExtraProperties
data class Users(var uid: String?="", var username: String? = "", var phone: String?="", var cnic: String?="") {
}