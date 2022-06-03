package com.example.app.data

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import com.example.app.view.DownloadNotification
import com.github.krottv.tmstemp.data.LibraryRemoteDataSourceRetrofit
import com.github.krottv.tmstemp.domain.Tracks
import kotlinx.coroutines.delay
import okhttp3.ResponseBody
import java.io.Console
import java.io.FileOutputStream
import java.io.InputStream

class UploadMusicWorker(
   // private val tracks: Tracks,
   // private val remoteDataSourceRetrofit: LibraryRemoteDataSourceRetrofit,
    private val appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params) {

    private val downloadNotification: DownloadNotification = DownloadNotification(appContext)
    private val remoteDataSourceRetrofit: RemoteDataSourceRetrofit = LibraryRemoteDataSourceRetrofit()


    override suspend fun doWork(): Result {

        setForeground(getForegroundInfo())
        val url = inputData.getString("1")
        Log.i("test", url!!)
        val fileName = url?.substring(url.lastIndexOf("/") + 1)
        val pathWhereYouWantToSaveFile = appContext.filesDir.absolutePath + fileName

        val responseBody = url?.let { remoteDataSourceRetrofit.downloadFile(it).body() }
        saveFile(responseBody, pathWhereYouWantToSaveFile)
        //delay(2000L)
        Log.i("test", appContext.filesDir.absolutePath.toString())

        //Log.i("Test", tracks.url + " " + pathWhereYouWantToSaveFile)

        return Result.success()
    }

    override suspend fun getForegroundInfo(): ForegroundInfo {
        return ForegroundInfo(9274, downloadNotification.downloadNotification())
    }

    fun saveFile(body: ResponseBody?, pathWhereYouWantToSaveFile: String): String {
        if (body == null)
            return ""
        var input: InputStream? = null
        try {
            input = body.byteStream()
            //val file = File(getCacheDir(), "cacheFileAppeal.srl")
            val fos = FileOutputStream(pathWhereYouWantToSaveFile)
            fos.use { output ->
                val buffer = ByteArray(4 * 1024) // or other buffer size
                var read: Int
                while (input.read(buffer).also { read = it } != -1) {
                    output.write(buffer, 0, read)
                }
                output.flush()
            }
            return pathWhereYouWantToSaveFile
        } catch (e: Exception) {
            Log.e("saveFile", e.toString())
        } finally {
            input?.close()
        }
        return ""
    }

}