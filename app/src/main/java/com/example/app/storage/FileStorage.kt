package com.example.app.storage

import java.io.*
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi


/* References:
* https://stackoverflow.com/questions/5092591/what-are-the-differences-among-internal-storage-external-storage-sd-card-and-r
* https://stuff.mit.edu/afs/sipb/project/android/docs/training/basics/data-storage/files.html
* https://www.javatpoint.com/kotlin-android-read-and-write-external-storage
*/

/*
* TODO:
*  Testing
*  Update manifest to ensure permissions are correct
*  Does not currently compile! (still figuring out some Android app intricacies)
* */

const val STORAGE_PATH: String = "Bridgefy";

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class FileStorage(private val context: Context) { // Stores context as a class variable (Kotlin feature)
    init {
        // Do Nothing
    }

    /* TODO:
     * Fix 2gb size limit (readBytes)
     */
    fun saveFile(fileName: String, fileContent: File) {
        var myExternalFile: File = File(context.getExternalFilesDir(STORAGE_PATH), fileName);

        try {
            val fileOutPutStream = FileOutputStream(myExternalFile)
            fileOutPutStream.write(fileContent.readBytes())
            fileOutPutStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

//    fun getFileFromStorage(fileName: String, fileContent: File) {
//        var myExternalFile: File = File(context.getExternalFilesDir(STORAGE_PATH), fileName)
//
//        var fileInputStream: FileInputStream = FileInputStream(myExternalFile)
//        var inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
//        val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
//        val stringBuilder: StringBuilder = StringBuilder()
//        var text: String? = null
//
//        while (run {
//                text = bufferedReader.readLine()
//                text
//            } != null) {
//            stringBuilder.append(text)
//        }
//
//        fileInputStream.close()
//    }
}

/*
const val STORAGE_PATH: String = "Bridgefy"

fun getFileFromStorage(fileName: String, fileContent: File) {
    var myExternalFile:File = File(context.getExternalFilesDir(STORAGE_PATH), fileName)

    myExternalFile = File(context.getExternalFilesDir(STORAGE_PATH),fileName)

    var fileInputStream = FileInputStream(myExternalFile)
    var inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
    val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
    val stringBuilder: StringBuilder = StringBuilder()
    var text: String? = null

    while (run {
            text = bufferedReader.readLine()
            text
        } != null) {
        stringBuilder.append(text)
    }

    fileInputStream.close()
}

fun saveFile(fileName: String, fileContent: File) {
    var myExternalFile: File = File(context.getExternalFilesDir(STORAGE_PATH), fileName);
    try {
        val fileOutPutStream = FileOutputStream(myExternalFile)
        fileOutPutStream.write(fileContent.toByteArray())
        fileOutPutStream.close()
    } catch (e: IOException) {
        e.printStackTrace()
    }
}
*/