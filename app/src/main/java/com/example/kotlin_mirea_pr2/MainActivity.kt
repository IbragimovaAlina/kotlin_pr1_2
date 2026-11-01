package com.example.kotlin_mirea_pr2
import androidx.compose.foundation.layout.size
import androidx.compose.ui.unit.dp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.kotlin_mirea_pr2.ui.theme.Kotlin_mirea_pr2Theme







import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp




import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Kotlin_mirea_pr2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    //Task6CircleTopRightBlack(modifier = Modifier.padding(innerPadding))

                    // Task6CircleCenterBluePurple()
                    Column(modifier = Modifier.padding(innerPadding)) {
                        WaterTracker()
                    }
                }
            }
        }
    }
}

// Задание 6
@Composable
fun Task6CircleTopRightBlack(modifier: Modifier = Modifier) {
    // Внешний Box принимает отступы от Scaffold, защищая контент от залезания под статус-бар
    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .size(240.dp, 120.dp)
                .background(Color.Black)
        ) {
            Image(
                painter = painterResource(id = R.drawable.circle),
                contentDescription = null,
                modifier = Modifier.align(Alignment.TopEnd)
            )
        }
    }
}


@Composable
fun Task6CircleCenterBluePurple() {
    Box(
        modifier = Modifier
            .size(240.dp, 120.dp)
            .background(Color.Blue)
    ) {
        Image(
            painter = painterResource(id = R.drawable.circle),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.Center,
            contentScale = ContentScale.FillBounds,
            colorFilter = ColorFilter.tint(Color(0xFF800080))
        )
    }
}

// Задание 7

data class Contact(
    val firstName: String, val middleName: String, val lastName: String,
    val phone: String, val address: String
) {
    fun format() =
        "Имя: $firstName\nОтчество: $middleName\nФамилия: $lastName\n" +
                "Мобильный телефон: $phone\nАдрес: $address"
}

private val contacts = listOf(
    Contact("Евгений","Андреевич","Лукашин","+7 495 495 95 95","г. Москва, 3-я улица Строителей, д. 25, кв. 12"),
    Contact("Василий","Егорович","Егоров","+7 495 788 78 78","Ивановская область, дер. Кузово, д. 4"),
    Contact("Людмила","Прокофьевна","Калугина","+7 495 788 78 78","Московская область, г. Люберцы, ул. Хлебникова, д. 43, кв. 290")
)
// Скрин 1
@Composable
fun ContactColumn(contact: Contact, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth().padding(16.dp)) {
        Text(text = contact.format())
    }
}
// Скрин 2/3: карточка — серый фон, текст по центру
@Composable
fun ContactCard(contact: Contact, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth().background(Color.LightGray).padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = contact.format(), textAlign = TextAlign.Center)
    }
}

// Скрин 2: список карточек
@Composable
fun ContactList(items: List<Contact>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth().padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) { items.forEach { ContactCard(it) } }
}

// Скрин 3: Row = текст по центру + произвольная картинка справа
@Composable
fun ContactRow(contact: Contact, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth().background(Color.LightGray).padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = contact.format(), textAlign = TextAlign.Center, modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = android.R.drawable.star_big_on),
            contentDescription = "star",
            modifier = Modifier.size(32.dp)
        )
    }
}


// ---- Превью как на скриншотах ----
@Preview(name = "ContactColumnPreview", showSystemUi = true)
@Composable
fun ContactColumnPreview() {
    ContactColumn(contacts.first())
}

@Preview(name = "ListPreview", showSystemUi = true)
@Composable
fun ListPreview() {
    ContactList(contacts)
}

@Preview(name = "ContactRowPreview", showSystemUi = true)
@Composable
fun ContactRowPreview() {
    ContactRow(contacts.first())
}
// Задание 8
private val primaryColor = Color(0xFF3F51B5)
private val secondaryColor = Color.Gray
private val buttonTextColor = Color.White

@Composable
fun WaterTracker() {
    var waterCount by remember { mutableStateOf(100) }
    var daysCount by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Трекер воды",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = primaryColor
        )

        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = "$waterCount мл",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = secondaryColor
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            // ИСПРАВЛЕНО: было `ButtonDefaults(backgroundColor = ...)`
            // В Material3 правильный вызов — buttonColors(containerColor = ...)
            colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
            // ИСПРАВЛЕНО: было `waterCount = 250` — теперь прибавляем 250
            onClick = { waterCount += 250 }
        ) {
            Text(
                text = "+250 мл",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = buttonTextColor
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            colors = ButtonDefaults.buttonColors(containerColor = secondaryColor),
            onClick = {
                if (waterCount >= 1500) daysCount++ else daysCount = 0
                waterCount = 0
            }
        ) {
            Text(
                text = "Завершить день",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = buttonTextColor
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Дней подряд (>= 1500 мл): $daysCount",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = primaryColor
        )
    }
}

// Задание 9

