package org.d3if0113.pokepedia.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import org.d3if0113.pokepedia.database.getDatabase
import org.d3if0113.pokepedia.repository.RegionRepository
import retrofit2.HttpException

class RefreshDataWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = RegionRepository(database)

        try {
            repository.refreshPokedex()
            repository.refreshRegion()
        } catch (e: HttpException) {
            return Result.retry()
        }

        return Result.success()
    }
}