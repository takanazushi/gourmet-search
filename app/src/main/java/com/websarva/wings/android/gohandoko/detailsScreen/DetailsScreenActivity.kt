package com.websarva.wings.android.gohandoko.detailsScreen

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.websarva.wings.android.gohandoko.hotPepperAPI.SearchResultsData
import com.websarva.wings.android.gohandoko.ui.theme.GoHandokoTheme
import org.json.JSONObject

/**
 * 営業時間を分かりやすく改行するMethod
 *
 * @open open APIから受け取った営業時間
 * @return return 編集した営業時間
 */
fun formatOpenHours(open: String): String {

    //非数字（\\D）の後に、コロンと半角スペースがあり、そのあとに数字（\\d）が続くパターン
    //全角スペースに置き換える
    val changeColon = open.replace(Regex("(?<=\\D): (?=\\d)"), "　")

    //数字（\\d）の後に半角スペースと（があり、そのあとに非数字（\\D）が続くパターン
    //改行記号と半角の(に置き換える
    val changeOpen = changeColon.replace(Regex("(?<=\\d) （(?=\\D)"), "\n(")

    //数字（\\d）の後に）と半角スペースがあり、そのあとに非数字（\\D）が続くパターン
    //）と改行記号に置き換える
    val addNewLine = changeOpen.replace(Regex("(?<=\\d)）(?=\\D)"), ")\n")

    //全角の）を半角の)に置き換える
    val changeClose = addNewLine.replace(Regex("）"), ")")

    //文字の文末が数字で終わっている場合、数字に改行を加える
    val changeEndingNumber = changeClose.replace(Regex("(\\d+)$"), "$1\n")

    //全角のSpaceと、半角の)の後ろに改行記号を加える
    return changeEndingNumber.replace(Regex("[　)]"), "$0\n")
}

fun formatURL(jsonURLString: String): String {
    val jsonObject = JSONObject(jsonURLString)
    val url = jsonObject.getString("pc")
    return url
}

/**
 * Mapを開くためのMethod
 *　
 * @context context
 * @lat 緯度
 * @lng 経度
 * 　
 * @return return 編集した営業時間
 */
fun MapOpen(context: Context, lat: Double, lng: Double) {

    val geoStrUri = "geo:${lat},${lng}"
    val geoUri = Uri.parse(geoStrUri)
    val intent = Intent(Intent.ACTION_VIEW, geoUri)
    intent.setPackage("com.google.android.apps.maps")
    //GoogleMapのアプリがある時
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    } else {
        //無いときはブラウザのGoogleMapで
        val browserStrUri = "https://www.google.com/maps/search/?api=1&query=${lat},${lng}"
        val browserUri = Uri.parse(browserStrUri)
        val browserIntent = Intent(Intent.ACTION_VIEW, browserUri)
        context.startActivity(browserIntent)
    }

}

@Composable
fun DetealsScreen(navController: NavController, data: SearchResultsData) {

    GoHandokoTheme {

        val context = LocalContext.current

        Column {
            Button(onClick = { navController.popBackStack() }, modifier = Modifier.padding(16.dp)) {

                Text(text = "戻る")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                Image(
                    painter = rememberImagePainter(data = data.photo),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Divider()
                Spacer(modifier = Modifier.height(8.dp))

                Text(text = data.genre, modifier = Modifier.padding(start = 20.dp))

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = data.name,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 20.dp),
                    lineHeight = 30.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(text = data.access, modifier = Modifier.padding(start = 20.dp))

                Spacer(modifier = Modifier.height(8.dp))

                Divider()

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "住所",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 20.dp)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = data.address,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(start = 20.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "営業時間",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 20.dp)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = formatOpenHours(data.open),
                    fontSize = 15.sp,
                    modifier = Modifier.padding(start = 20.dp)
                )

                Text(
                    text = "定休日",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 20.dp)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = data.close,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(start = 20.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "ランチ営業有無",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 20.dp)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = data.lunch,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(start = 20.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "23時以降の営業有無",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 20.dp)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = data.midnight,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(start = 20.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row {

                    Button(onClick = {
                        val uri = Uri.parse(formatURL(data.url))
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        context.startActivity(intent)
                    }, modifier = Modifier.padding(16.dp)) {

                        Text(text = "お店のHPを見る")
                    }

                    Button(onClick = {
                        MapOpen(context, data.lat, data.lng)
                    }, modifier = Modifier.padding(16.dp)) {

                        Text(text = "Mapで位置を見る")
                    }
                }


            }
        }


    }

    Log.d("Deteal", "launchサービス：${data.lng}")
}

@Preview
@Composable
fun Preview() {
    val dummyData = SearchResultsData(
        id = "1",
        name = "カフェカラオケなんでもありますうきうきなお店★",
        address = "テスト県テスト市テストテストテスト111-1",
        lat = 134.55555,
        lng = 34.555555,
        lunch = "ランチなし",
        midnight = "営業無し",
        url = "hogehogehogehoge",
        access = "テスト駅から徒歩100分",
        thumbnail = "dami-dami-dami-dami",
        photo = "https://imgfp.hotp.jp/IMGH/97/41/P037659741/P037659741_238.jpg",
        genre = "居酒屋",
        open = "月～水、金、祝前日: 11:30～14:30 （料理L.O. 14:00 ドリンクL.O. 14:00）17:30～23:00 （料理L.O. 22:00 ドリンクL.O. 22:00）土: 11:30～14:30 （料理L.O. 14:00 ドリンクL.O. 14:00）17:00～23:00 （料理L.O. 22:00 ドリンクL.O. 22:00）",
        close = "火・木"
    )

    val navController = rememberNavController()

    DetealsScreen(navController = navController, data = dummyData)
}