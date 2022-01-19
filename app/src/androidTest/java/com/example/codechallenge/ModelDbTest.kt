package com.example.codechallenge

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.codechallenge.room.AppDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.*

@ExperimentalCoroutinesApi
abstract class ModelDbTest<T> {
    protected lateinit var db: AppDatabase
    private val testDispatcher = TestCoroutineDispatcher()
    val testScope = TestCoroutineScope(testDispatcher)

    @Before
    open fun setup(){
        initModel()
        createDb()
    }

    private fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
                .setTransactionExecutor(testDispatcher.asExecutor())
                .setQueryExecutor(testDispatcher.asExecutor())
                .build()
    }

    @Test
    abstract fun testCreate()

    @Test
   abstract fun testRead()

    @Test
   abstract fun testUpdate()

    @Test
    abstract fun testDelete()

    abstract fun initModel()

    abstract fun checkPOJO(retrieved: T)

    @After
    fun closeDb(){
        db.close()
    }
}
