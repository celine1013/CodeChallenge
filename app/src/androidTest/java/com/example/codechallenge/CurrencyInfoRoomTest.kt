package com.example.codechallenge

import com.example.codechallenge.model.CurrencyInfo
import com.example.codechallenge.room.CurrencyDao
import junit.framework.Assert.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test

@ExperimentalCoroutinesApi
class CurrencyInfoRoomTest : ModelDbTest<CurrencyInfo?>() {
    private val currencyId = "ABC"
    private lateinit var currencyInfo: CurrencyInfo
    private lateinit var currencyDao: CurrencyDao

    override fun setup() {
        super.setup()
        currencyDao = db.currencyDao()
    }

    override fun testCreate() = testScope.runBlockingTest {
        assertTrue("Create currency", currencyDao.insertCurrency(currencyInfo) > 0)
    }

    override fun testRead() = testScope.runBlockingTest {
        testCreate()
        val retrieved = currencyDao.getCurrency(currencyId)
        assertNotNull(retrieved)
        checkPOJO(retrieved)
    }

    override fun testUpdate() = testScope.runBlockingTest {
        val orig = CurrencyInfo(
            id = currencyId,
            name = "A bad coin",
            symbol = "abc"
        )

        currencyDao.insertCurrency(orig)
        Assert.assertTrue("Update currency", currencyDao.insertCurrency(currencyInfo) > 0)
        val retrieved = currencyDao.getCurrency(currencyId)
        assertNotNull(retrieved)
        checkPOJO(retrieved)
    }

    override fun testDelete() = testScope.runBlockingTest {
        testCreate()
        assertTrue("Delete currency", currencyDao.delete(currencyId) > 0)
        assertNull("Delete - Retrieve currency", currencyDao.getCurrency(currencyId))
    }

    override fun initModel() {
        currencyInfo = CurrencyInfo(
            id = currencyId,
            name = "A special coin",
            symbol = "asc"
        )
    }

    override fun checkPOJO(retrieved: CurrencyInfo?) {
        assertNotNull(retrieved)
        checkCurrencyInfo(currencyInfo, retrieved!!)
    }

    companion object{
        fun checkCurrencyInfo(expected: CurrencyInfo, actual: CurrencyInfo){
            assertEquals(expected.id, actual.id)
            assertEquals(expected.name, actual.name)
            assertEquals(expected.symbol, actual.symbol)
        }
    }
}