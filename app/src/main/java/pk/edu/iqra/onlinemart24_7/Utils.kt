package pk.edu.iqra.onlinemart24_7

import android.widget.EditText

object Utils {
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