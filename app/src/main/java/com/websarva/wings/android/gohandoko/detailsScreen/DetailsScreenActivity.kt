package com.websarva.wings.android.gohandoko.detailsScreen

import android.util.Log
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.websarva.wings.android.gohandoko.hotPepperAPI.SearchResultsData
import com.websarva.wings.android.gohandoko.ui.theme.GoHandokoTheme

@Composable
fun DetealsScreen(navController: NavController, data: SearchResultsData) {

    GoHandokoTheme {
        Text(text = "検索詳細画面")

        Button(onClick = { navController.popBackStack() }) {

            Text(text = "戻る")
        }
    }

    Log.d("Deteal", "launchサービス：${data.access}")
}