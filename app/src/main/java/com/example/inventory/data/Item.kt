package com.example.inventory.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.NumberFormat

/*
* An Entity class that Define fields to store the following inventory information for each item.
* An Int to store the primary key.
* A String to store the item name.
* A double to store the item price.
* An Int to store the quantity in stock.
*/
@Entity(tableName = "item")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val itemName: String,
    @ColumnInfo(name = "price")
    val itemPrice: Double,
    @ColumnInfo(name = "quantity")
    val quantityInStock: Int
)
/**
 * Returns the passed in price in currency format.
 */
fun Item.getFormattedPrice(): String = NumberFormat.getCurrencyInstance().format(itemPrice)