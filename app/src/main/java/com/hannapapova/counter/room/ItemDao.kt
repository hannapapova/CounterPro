package com.hannapapova.counter.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addItem(item: Item)

    @Query("SELECT * FROM counters_table")
    fun readAllData(): LiveData<List<Item>>

    @Update
    suspend fun updateItem(item: Item)

    @Delete
    suspend fun deleteItem(item: Item)

    @Query("DELETE FROM counters_table")
    suspend fun deleteAllItems()
}