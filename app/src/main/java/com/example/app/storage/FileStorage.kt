package com.example.app.storage

import java.io.*
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import androidx.annotation.RequiresApi
import java.net.URI

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

    @Throws(IOException::class)
    fun readBytes(context: Context, uri: Uri): ByteArray? =
        context.contentResolver.openInputStream(uri)?.buffered()?.use { it.readBytes() }
}


//Instructions for adding the file picker to a button:
//
//<1. Add to MainActivity>
//
//override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//    super.onActivityResult(requestCode, resultCode, data)
//
//    if (requestCode == 111) {
//        getContentResolver().takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        val selectedFile = data?.data // The uri with the location of the file
//        // URI is given from selectedFile.toString()
//        // Pass the URI into the readBytes() function above to get it as a byte array
//    }
//}
//<2. Put in onclick listener in a fragment>
//val intent = Intent()
//  .setType("*/*")
//  .setAction(Intent.ACTION_GET_CONTENT)
//
//   activity?.startActivityForResult(Intent.createChooser(intent, "Select a file"), 111)