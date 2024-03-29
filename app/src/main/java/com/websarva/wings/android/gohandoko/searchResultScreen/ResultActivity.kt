package com.websarva.wings.android.gohandoko.searchResultScreen

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.websarva.wings.android.gohandoko.MainActivity
import com.websarva.wings.android.gohandoko.searchScreen.SearchScreen
import com.websarva.wings.android.gohandoko.ui.theme.GoHandokoTheme

@Composable
fun ResultActivity(navController: NavController){

    Button(onClick = { navController.popBackStack() }){

        Text(text = "戻る")
    }

    Text(text = "検索結果画面やで")
}