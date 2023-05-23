package br.senai.sp.jandira.login_sigup

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.login_sigup.components.BottomShape
import br.senai.sp.jandira.login_sigup.components.TopShape
import br.senai.sp.jandira.login_sigup.repository.UserRepository
import br.senai.sp.jandira.login_sigup.ui.theme.LoginSigUpTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginSigUpTheme {
                loginScreen()
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun loginScreen() {

    var emailState by remember {
        mutableStateOf("")
    }

    var passwordState by remember{
        mutableStateOf("")
    }

    var passwordVisibilityState by remember {
        mutableStateOf(false)
    }



    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End

            ) {
                TopShape()
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)

            ) {
                Text(
                    text = stringResource(id = R.string.login),
                    color = Color(206, 6, 240),
                    fontSize = 60.sp,
                    fontWeight = FontWeight(700),
                )
                Text(
                    text = stringResource(id = R.string.login_text),
                    color = Color(160, 156, 156),
                    fontSize = 15.sp,
                    fontWeight = FontWeight(400),
                )
                Spacer(
                    modifier = Modifier.height(60.dp)
                )
                OutlinedTextField(
                    value = emailState,
                    onValueChange = {
                        emailState = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    textStyle = TextStyle(color = Color.Black, fontWeight = FontWeight(400)),
                    label = { Text(text = stringResource(id = R.string.email)) },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_email_24),
                            contentDescription = "",
                            tint = Color(206, 6, 240)
                        )
                    },
                    shape = RoundedCornerShape(16.dp),
                )
                Spacer(
                    modifier = Modifier.height(20.dp)
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
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_lock_24),
                            contentDescription = "",
                            tint = Color(206, 6, 240)
                        )
                    },
                    trailingIcon = {
                            IconButton(
                                onClick = {
                                passwordVisibilityState = !passwordVisibilityState
                            }
                            ) {
                                Icon(
                                    imageVector = if(passwordVisibilityState){
                                        Icons.Default.Visibility
                                    }else{
                                        Icons.Default.VisibilityOff
                                    },
                                    contentDescription = null
                                )
                            }
                    },
                    visualTransformation = if(passwordVisibilityState) VisualTransformation.None else PasswordVisualTransformation(),
                    shape = RoundedCornerShape(16.dp),
                )
                Spacer(
                    modifier = Modifier.height(30.dp)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.End
                ) {
                    Button(
                        onClick = {
                                  authenticate(emailState, passwordState, context)
                        },
                        modifier = Modifier
                            .width(135.dp)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(Color(206, 6, 240)),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = stringResource(id = R.string.sign_in).uppercase(),
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight(700),
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_arrow_forward_24),
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }
                    Spacer(
                        modifier = Modifier.height(30.dp)
                    )
                    Row(
                        modifier = Modifier
                            .wrapContentWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.account),
                            color = Color(160, 156, 156),
                            fontSize = 12.sp,
                            fontWeight = FontWeight(400),
                        )
                        Spacer(
                            modifier = Modifier.width(5.dp)
                        )
                        TextButton(
                            onClick = {
                                var openSignup = Intent(context, SignUpActivity::class.java)
                                context.startActivity(openSignup)
                            }
                        ) {
                            Text(
                                text = stringResource(id = R.string.sign_up),
                                color = Color(206, 6, 240),
                                fontSize = 12.sp,
                                fontWeight = FontWeight(700),
                            )
                        }
                    }
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
}

fun authenticate(
    email: String,
    password: String,
    context: Context
) {
    val userRepository = UserRepository(context)
    val user = userRepository.authenticate(email, password)

    if ( user != null){
        val openHomeActivity = Intent(context, HomeActivity::class.java)
        context.startActivity(openHomeActivity)
    }
}
