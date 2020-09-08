package pk.edu.iqra.onlinemart24_7

import android.content.Context
import android.widget.Toast

//to show toast message
fun Context.showToast(msg: String) {
    Toast.makeText(this, msg,Toast.LENGTH_SHORT).show()
}