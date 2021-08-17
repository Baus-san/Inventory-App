package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * [ItemRoomDatabase] class defines the list of entities and data access objects.
 * It is also the main access point for the underlying connection.
 * This class has one method that either creates an instance of the [RoomDatabase] if it doesn't exist,
 * or returns the existing instance of the RoomDatabase.
 *
 */
@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class ItemRoomDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao

    // The companion object allows access to the methods for creating
    // or getting the database using the class name as the qualifier.
    companion object{
        // The value of a volatile variable will never be cached, and all writes and reads will be done to and from the main memory.
        // This helps make sure the value of INSTANCE is always up-to-date and the same for all execution threads.
        // It means that changes made by one thread to INSTANCE are visible to all other threads immediately.
        @Volatile
        private var INSTANCE: ItemRoomDatabase? = null

        fun getDatabase(context: Context): ItemRoomDatabase {
            //  Wrapping the code to get the database inside a synchronized block means that only one thread of execution at a time can enter this block of code,
            //  which makes sure the database only gets initialized once.
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItemRoomDatabase::class.java,
                    "item_database"
                )
                    // A migration object is an object that defines how you take all rows with the old schema
                    // and convert them to rows in the new schema, so that no data is lost.
                    // A simple solution is to destroy and rebuild the database, which means that the data is lost.
                    .fallbackToDestructiveMigration()
                    // Create the database instance
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}