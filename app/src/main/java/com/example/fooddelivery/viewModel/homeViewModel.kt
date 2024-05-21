package com.example.fooddelivery.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.fooddelivery.data.Delivery
import com.example.fooddelivery.data.Food
import com.example.fooddelivery.data.FoodFavorite
import com.example.fooddelivery.data.Order
import com.example.fooddelivery.data.OrderItem
import com.example.fooddelivery.data.PaymentMethod
import com.example.fooddelivery.data.dao.AddressDao
import com.example.fooddelivery.data.dao.FoodDao
import com.example.fooddelivery.data.dao.OrderDao
import com.example.fooddelivery.data.dao.OrderItemDao
import com.example.fooddelivery.data.dao.foodFavDao
import com.example.fooddelivery.data.orderStatus

class homeViewModel(
    private val foodDao: FoodDao,
    private val foodFavDao: foodFavDao,
    private val orderDao: OrderDao,
    private val email: String,
    private val orderItemDao: OrderItemDao,
    private val addressDao: AddressDao
) : ViewModel() {
    fun getDishes(): List<Food> {
        return foodDao.getAllFood()
    }

    fun getSomeDishes(number: Int): List<Food> {
        return foodDao.getSomeFood(number)
    }

    fun getFoodInfo(name: String): Food {
        return foodDao.getFoodByName(name)
    }

    fun getFavoriteFoods(): List<Food> {
        return foodFavDao.getAllFoodFavByUserID(email)
    }

    fun likeFood(name: String) {
        val food = foodFavDao.userLikeFood(email, name)
        if (food == null) {
            val FoodFavorite = FoodFavorite(name, email)
            foodFavDao.insertFoodFav(FoodFavorite)
        } else {
            Log.i("like_food", "food has been liked already")
        }
    }

    fun disLikeFood(name: String) {
        val food = foodFavDao.userLikeFood(email, name)
        if (food != null) {
            val FoodFavorite = FoodFavorite(name, email)
            foodFavDao.deleteFoodFav(FoodFavorite)
        } else {
            Log.i("like_food", "food hasn't been liked ")
        }
    }

    fun userHistory(): List<Order> {
        return orderDao.getAllOrdersByUser(email)
    }

    fun addToCart(name: String, number: Int = 1) {
        val orderList = orderDao.getUserTODOCart(email)
        val food = foodDao.getFoodByName(name)
        val address = addressDao.getUserAddress(name)
        if (orderList?.count() == 1) {
            val orderItem = orderItemDao.getOrderItemWithFoodIDAndOrderID(orderList[0].id, name)
            if (orderItem == null) {
                val newOrderItem = OrderItem(number, orderList[0].id, name)
                orderItemDao.upsertOrderItem(newOrderItem)
            } else {
                orderItem.quantity = orderItem.quantity + number
                orderItemDao.upsertOrderItem(orderItem)
            }

            val orderTODO = orderDao.getOrderById(orderList[0].id)
            orderTODO.totalPrice = orderTODO.totalPrice + number * food.price
            orderDao.upsertOrder(orderTODO)

        }
        if (orderList.isEmpty()) {
            val allOrders = orderDao.getAllOrders()
            if (allOrders.isEmpty()) {
                val order = Order(
                    1, food.price, PaymentMethod.DirectPay, Delivery.PickUp, address.id, email,
                    orderStatus.TODO
                )
                orderDao.upsertOrder(order)
            } else {
                val order = Order(
                    allOrders.last().id + 1,
                    food.price,
                    PaymentMethod.DirectPay,
                    Delivery.PickUp,
                    address.id,
                    email,
                    orderStatus.TODO
                )
                orderDao.upsertOrder(order)
            }

        } else {
            Log.e("custom_error", "error in orders: there is two TODO Cart")
        }
    }


}