package com.example.fooddelivery.ui.home.expandable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fooddelivery.R
import com.example.fooddelivery.navigation.Screen
import com.example.fooddelivery.component.ItemGrid

@Composable
fun ExpandableHomeScreen(
    name: String,
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Column(modifier = Modifier.background(color = MaterialTheme.colorScheme.tertiaryContainer)) {
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 42.dp, top = 64.dp, bottom = 36.dp)
        ) {
            Image(
                modifier = Modifier.clickable { navController.navigate(Screen.Home.route) },
                painter = painterResource(id = R.drawable.chevron_left),
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(start = 36.dp),
                text = "See more",
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 18.sp)
            )
        }
        ItemGrid(navController)
    }
}