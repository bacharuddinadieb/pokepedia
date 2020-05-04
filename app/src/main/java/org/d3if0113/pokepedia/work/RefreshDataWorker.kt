package org.d3if0113.pokepedia.work

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import org.d3if0113.pokepedia.database.getDatabase
import org.d3if0113.pokepedia.repository.RegionRepository
import retrofit2.HttpException

class RefreshDataWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "org.d3if0113.pokepedia.work.RefreshDataWorker"
    }
    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = RegionRepository(database)

        try {
            repository.refreshPokedex()
            repository.refreshRegion()
            Log.i("refresh data", "Work refresh data")
        } catch (e: HttpException) {
            Log.i("refresh data", "Work Gagal refresh data")
            return Result.retry()
        }

        return Result.success()
    }
}