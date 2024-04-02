package com.websarva.wings.android.gohandoko.searchScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun DistanceDropDownMenu(
    viewModel: SearchViewModel,
    //距離が選択されたときに呼び出される関数
    onRangeChange: (Int) -> Unit
) {

    //距離のリスト
    //Firstがドロップダウンに表示する距離
    //Secondがかかる時間
    //ThirdがAPIに渡すときの値
    val distances = listOf(
        Triple("300m", "歩いて約5分圏内のお店を検索します", 1),
        Triple("500m", "歩いて約10分圏内のお店を検索します", 2),
        Triple("1km", "歩いて約15分程度のお店を検索します", 3),
        Triple("2km", "歩いて約30分程度のお店を検索します", 4),
        Triple("3km", "歩いて約45分程度のお店を検索します", 5),
    )

    //選択された距離を保持する状態を定義する
    var selectedDistance by viewModel.selectedDistance

    //ドロップダウンの状態を保持する
    var expanded by viewModel.expanded

    //ドロップダウンメニューのアイコンを設定
    val icon = if (expanded) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown

    //これが無いとエラー
    @OptIn(ExperimentalMaterialApi::class)
    (ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { viewModel.expanded.value=!viewModel.expanded.value }
    ) {

        //テキストフィールドを利用して、ドロップダウンメニューの開閉を制御する
        @OptIn(ExperimentalMaterial3Api::class)
        TextField(
            //書き込み不可
            readOnly = true,
            //選択された距離を表示する
            value = selectedDistance.first,
            //編集不可なので空
            onValueChange = {},
            //ドロップダウンメニューのアイコン
            trailingIcon = { Icon(imageVector = icon, contentDescription = null) },
            modifier = Modifier
                //幅を最大に
                .fillMaxWidth()

                //クリックでドロップダウンメニュー開閉をする
                .clickable { viewModel.expanded.value=!viewModel.expanded.value }
        )

        //ドロップダウンメニューの中にあるアイテム
        ExposedDropdownMenu(
            expanded = expanded,
            //ドロップダウンメニューを閉じる
            onDismissRequest = { viewModel.expanded.value = false }) {

            //リストの各要素に対して処理を行う
            distances.forEach { distance ->

                //ドロップダウンメニューがクリックされたとき
                DropdownMenuItem(onClick = {
                    //選択された距離に更新
                    viewModel.selectedDistance.value = distance

                    //ドロップダウンメニューを閉じる
                    viewModel.expanded.value = false

                    //APIに渡す値を設定
                    onRangeChange(distance.third)
                }) {
                    //ドロップダウンメニューの表示
                    Text(text = distance.first)
                }
            }
        }
    })

    Spacer(modifier = Modifier.height(15.dp))

    //説明文を表示
    Text(text = selectedDistance.second)

    Spacer(modifier = Modifier.height(10.dp))
}