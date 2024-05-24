package com.example.fooddelivery.repository

import androidx.annotation.WorkerThread
import com.example.fooddelivery.data.Food
import com.example.fooddelivery.data.Person
import com.example.fooddelivery.data.dao.FoodDao
import kotlinx.coroutines.flow.Flow

class FoodRepository(private val foodDao: FoodDao) {



    val allFoods: Flow<List<Food>> = foodDao.getAllFood()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun upsert(food: Food) {
        foodDao.upsertFood(food)
    }



    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getFoodInfo(name:String):Food? {
        return foodDao.getFoodByName(name)
    }

}