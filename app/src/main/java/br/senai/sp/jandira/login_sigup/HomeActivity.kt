package br.senai.sp.jandira.login_sigup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.login_sigup.model.Category
import br.senai.sp.jandira.login_sigup.repository.CategoryRepository
import br.senai.sp.jandira.login_sigup.ui.theme.LoginSigUpTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginSigUpTheme {
                Scaffold(floatingActionButton = {
                    FloatingActionButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = ""
                        )
                    }
                }
                ) {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it),
                    ) {
                        HomeScreen(CategoryRepository.getCategories())
                    }
                }
            }
        }
    }
}

@Composable
fun HomeScreen(categories: List<Category>) {
    Column() {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            shape = RectangleShape
        ) {
            Image(
                painter = painterResource(id = R.drawable.paris),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalAlignment = Alignment.End,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.susanna_profile),
                    contentDescription = "",
                    modifier = Modifier
                        .clip(RoundedCornerShape(200.dp))
                        .height(61.dp)
                        .border(
                            width = 2.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(200.dp)
                        ),
                )
                Text(
                    text = stringResource(id = R.string.susanna_hoffs),
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight(400)
                )
                Spacer(modifier = Modifier.height(30.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_location_on_24),
                            contentDescription = "",
                            tint = Color.White,
                            modifier = Modifier.height(15.dp)
                        )
                        Text(
                            text = stringResource(id = R.string.youre_in_paris),
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400)
                        )
                    }
                    Text(
                        text = stringResource(id = R.string.my_Trips),
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight(700),
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }
        Text(
            text = stringResource(id = R.string.categories),
            modifier = Modifier
                .padding(
                    start = 16.dp, top = 14.dp
                )
        )
        LazyRow(
            modifier = Modifier.padding(start = 12.dp)
        ) {
            items(categories) {
                Card(
                    modifier = Modifier
                        .size(120.dp, 80.dp)
                        .padding(4.dp),
                    backgroundColor = Color(206, 6, 240)
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = it.icon!!,
                            contentDescription = null,
                            tint = Color.White
                        )
                        Text(text = it.name, color = Color.White)
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DefaultPreview() {
    LoginSigUpTheme {
        Scaffold(floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = ""
                )
            }
        }
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
            ) {
                HomeScreen(CategoryRepository.getCategories())
            }
        }
    }
}



