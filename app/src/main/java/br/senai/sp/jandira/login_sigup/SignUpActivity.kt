package br.senai.sp.jandira.login_sigup

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.login_sigup.components.BottomShape
import br.senai.sp.jandira.login_sigup.components.TopShape
import br.senai.sp.jandira.login_sigup.model.User
import br.senai.sp.jandira.login_sigup.repository.UserRepository
import br.senai.sp.jandira.login_sigup.ui.theme.LoginSigUpTheme
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginSigUpTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    signUpScreen()
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun signUpScreen() {

    var nameState by remember {
        mutableStateOf("")
    }

    var emailState by remember {
        mutableStateOf("")
    }

    var phoneState by remember {
        mutableStateOf("")
    }

    var passwordState by remember {
        mutableStateOf("")
    }

    var over18State by remember {
        mutableStateOf(false)
    }

    var photoURI by remember {
        mutableStateOf<Uri?>(null)
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
    ){
        photoURI = it
    }

    var painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current).data(photoURI).build()
    )

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TopShape()
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .weight(weight = 1f)
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.sign_up),
                    color = Color(206, 6, 240),
                    fontSize = 32.sp,
                    fontWeight = FontWeight(700),
                )
                Text(
                    text = stringResource(id = R.string.new_account),
                    color = Color(160, 156, 156),
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400)
                )
                Spacer(modifier = Modifier.height(50.dp))
                Box(
                    modifier = Modifier.size(100.dp),
                ) {
                    Card(
                        modifier = Modifier
                            .size(100.dp)
                            .align(alignment = Alignment.Center),
                        shape = CircleShape,
                        border = BorderStroke(
                            width = 2.dp,
                            brush = Brush
                                .horizontalGradient(
                                    colors = listOf(Color.Magenta, Color.White)
                                )
                        )
                    ) {
                        Image(
                            painter = painter,
                            contentDescription = null,
                            modifier = Modifier
                                .size(64.dp)
                                .align(Alignment.Center),
                            contentScale = ContentScale.None,

                        )
                    }
                    Image(
                        painter = painterResource(
                            id = R.drawable.photo_add
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                            .align(Alignment.BottomEnd)
                            .clickable {
                                       launcher.launch("image/*")
                            },
                    )
                }
            }
            Spacer(modifier = Modifier.height(25.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(380.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedTextField(
                    value = nameState,
                    onValueChange = {
                        nameState = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight(400)
                    ),
                    label = { Text(text = stringResource(id = R.string.username)) },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_person_24),
                            contentDescription = "",
                            tint = Color(206, 6, 240)
                        )
                    },
                    shape = RoundedCornerShape(16.dp),
                )
                OutlinedTextField(
                    value = phoneState,
                    onValueChange = {
                        phoneState = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    textStyle = TextStyle(color = Color.Black, fontWeight = FontWeight(400)),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = { Text(text = stringResource(id = R.string.phone)) },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_phone_android_24),
                            contentDescription = "",
                            tint = Color(206, 6, 240)
                        )
                    },
                    shape = RoundedCornerShape(16.dp),
                )
                OutlinedTextField(
                    value = emailState,
                    onValueChange = { emailState = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    textStyle = TextStyle(color = Color.Black, fontWeight = FontWeight(400)),
                    label = { Text(text = stringResource(id = R.string.email)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_email_24),
                            contentDescription = "",
                            tint = Color(206, 6, 240)
                        )
                    },
                    shape = RoundedCornerShape(16.dp),
                )
                OutlinedTextField(
                    value = passwordState,
                    onValueChange = {
                        passwordState = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    textStyle = TextStyle(color = Color.Black, fontWeight = FontWeight(400)),
                    label = { Text(text = stringResource(id = R.string.password)) },
                    visualTransformation = PasswordVisualTransformation(),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_lock_24),
                            contentDescription = "",
                            tint = Color(206, 6, 240)
                        )
                    },
                    shape = RoundedCornerShape(16.dp),
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = over18State,
                        onCheckedChange = {
                            over18State = it
                        }
                    )
                    Text(
                        text = stringResource(id = R.string.over18)
                    )
                }
                Button(
                    onClick = {
                              userSave(
                                  context = context,
                                  email = emailState,
                                  userName = nameState,
                                  phone = phoneState,
                                  password = passwordState,
                                  isOver = over18State
                              )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(Color(206, 6, 240)),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.create_account),
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight(700),
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = stringResource(id = R.string.already_have_an_account),
                    color = Color(160, 156, 156),
                    fontSize = 12.sp,
                    fontWeight = FontWeight(400),
                )
                Spacer(
                    modifier = Modifier.width(5.dp)
                )
                Text(
                    text = stringResource(id = R.string.sign_in),
                    color = Color(206, 6, 240),
                    fontSize = 12.sp,
                    fontWeight = FontWeight(700),
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            BottomShape()
        }
    }
}

fun userSave(
    context: Context,
    email :String,
    userName: String,
    phone: String,
    password: String,
    isOver: Boolean
) {
    val userRepository = UserRepository(context)

    //recuperando no banco um usuario que
    //tenha o email informado
    var user = userRepository.findUserByEmail(email)

    //Se user for null, gravamos
    //o novo usuario, senão, avisamos que o
    //usuario ja existe
    if(user == null){
        val newUser = User(
            userName = userName,
            phone = phone,
            email = email,
            password = password,
            isOver18 = isOver
        )
        val id = userRepository.save(newUser)
        Toast.makeText(context, "User created #$id", Toast.LENGTH_LONG).show()
    }else{
        Toast.makeText(context, "User alaways exist", Toast.LENGTH_LONG).show()
    }

}
