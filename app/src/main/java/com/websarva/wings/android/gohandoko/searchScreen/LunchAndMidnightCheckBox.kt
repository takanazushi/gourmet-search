package com.websarva.wings.android.gohandoko.searchScreen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LunchAndMidnightCheckBox(
    onLunchCheckedChange: (Boolean) -> Unit,
    onMidnightCheckedChange: (Boolean) -> Unit,
    viewModel: SearchViewModel
) {
    val isLaunchChecked by viewModel.lunch
    val isMidnightChecked by viewModel.midnight


    Row {
        //ランチ有無チェックボックス
        CheckBoxAndTitleText(
            TitleName = "ランチあり",
            checked = isLaunchChecked,
            onCheckedChange = {
                viewModel.lunch.value = !isLaunchChecked
                onLunchCheckedChange(isLaunchChecked)
            }
        )
        Spacer(modifier = Modifier.width(60.dp))

        //23時以降も営業してるところがいいかどうかのチェックボックス
        CheckBoxAndTitleText(
            TitleName = "23時以降も営業",
            checked = isMidnightChecked,
            onCheckedChange = {
                viewModel.midnight.value = !isMidnightChecked
                onMidnightCheckedChange(isMidnightChecked)
            }
        )
    }
}
