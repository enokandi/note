package FolderCreater

import android.app.Application
import android.content.Context
import android.os.Environment
import android.util.Log
import android.widget.Toast
import com.oniktech.mixnote.R
import java.io.File

const val MAIN_FOLDER_NAME = "mixnote"

fun createFolder (name:String): Boolean{
    var dir = Environment.getExternalStorageDirectory().absolutePath + "/$MAIN_FOLDER_NAME/$name"
    var file = File(dir)
    if (!file.exists()){
        if (!file.mkdirs()){
            Log.i("mytag" , "folder can not be created")
            return false
        } else return true
    }
    else return true
}

fun createMainFolder():Boolean{
    var dir = Environment.getExternalStorageDirectory().absolutePath + "/" + MAIN_FOLDER_NAME
    var file = File(dir)
    if (!file.exists()){
        if (!file.mkdirs()){
            Log.i("mylog" , "main folder can not be created")
            return false
        }
        else return true
    }
    else return true
}