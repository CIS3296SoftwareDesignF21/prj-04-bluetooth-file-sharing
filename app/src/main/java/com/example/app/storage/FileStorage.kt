package com.example.app.storage

import java.io.*
import android.content.Context
import android.os.Build
import android.os.Environment
import androidx.annotation.RequiresApi

/* References:
* https://stackoverflow.com/questions/5092591/what-are-the-differences-among-internal-storage-external-storage-sd-card-and-r
* https://stuff.mit.edu/afs/sipb/project/android/docs/training/basics/data-storage/files.html
* https://www.javatpoint.com/kotlin-android-read-and-write-external-storage
*/

/*
 * TODO:
 *  Discuss file upload strategy (i.e. should we use a file picker, specify a path, etc.)
 */

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class FileStorage(private val context: Context) { // Stores context as a class variable (Kotlin feature)
    /* saveFile(fileName: String, fileContent: ByteArray)
     * - Saves the specified byte array to the downloads folder using the file name given
     * - Should automatically handle duplicate filenames
     * TODO:
      * Fix 2gb size limit (readBytes) (we will totally fix this before the project is due)
     */

    fun saveFile(fileName: String, fileContent: ByteArray) {
        val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

        var myExternalFile: File = File(path, "/test.txt");
        try {
            val fileOutPutStream = FileOutputStream(myExternalFile)
            fileOutPutStream.write(fileContent)
            fileOutPutStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        /* Example Invocation:
            // Test file storage
            val fileName = "test.txt"
            val fileContents = "testing123".toByteArray()
            val fs: FileStorage = FileStorage(requireContext())
            fs.saveFile(fileName, fileContents)
         */
    }
}