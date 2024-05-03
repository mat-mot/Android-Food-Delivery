package com.example.fooddelivery.component

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fooddelivery.R
import com.example.fooddelivery.data.Food

val foodItems = listOf(
    Food("Veggie tomato mix", "N1,900", R.drawable.food_1),
    Food("Egg and cucumber", "N1,900", R.drawable.food_2),
    Food("Egg and cucumber", "N1,900", R.drawable.food_3),
    Food("Egg and cucumber", "N1,900", R.drawable.food_4),
    Food("Egg and cucumber", "N1,900", R.drawable.food_1),
)

@Composable
fun ScrollableRowList() {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .padding(start = 33.dp, top = 50.dp, end = 0.dp, bottom = 40.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 40.dp, 40.dp)
        ) {
            BasicText(
                text = "see more",
                style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.primary),
            )
        }
        Row(
            modifier = Modifier
                .horizontalScroll(scrollState)
        ) {
            foodItems.forEach { foodItem ->
                FoodItem(foodItem = foodItem)
            }
        }
    }
}
