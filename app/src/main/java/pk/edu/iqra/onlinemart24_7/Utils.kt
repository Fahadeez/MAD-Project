package pk.edu.iqra.onlinemart24_7

import android.widget.EditText

object Utils {


    const val ORDER_FILE = "orderFile"
    const val ORDER_SET = "orderSet"
    const val AUTH_FILE = "authFile"
    const val USERNAME = "userName"


//    to get text

    fun getText(editText: EditText) = editText.text.toString().trim()

//to check the null fields
    fun validate(vararg inputs:String):Boolean {
        for(str in inputs){
           if (str.isEmpty()) {
               return false
           }
        }
        return true
    }
}