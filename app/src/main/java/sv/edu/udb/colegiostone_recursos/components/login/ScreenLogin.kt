package sv.edu.udb.colegiostone_recursos.components.login

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import sv.edu.udb.colegiostone_recursos.utils.NavigationStrings
import sv.edu.udb.colegiostone_recursos.utils.Strings

@Composable
fun ScreenLogin(
    modifier: Modifier,
    navHostController: NavHostController
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val (username, setUsername) = remember { mutableStateOf("") }
        val (pass, setPass) = remember { mutableStateOf("") }
        val (passVisible, setPassVisible) = remember { mutableStateOf(false) }

        val context = LocalContext.current
        val auth = FirebaseAuth.getInstance()

        Text(
            text = Strings.NombreColegio,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = username,
            onValueChange = { setUsername(it) },
            label = { Text(Strings.LabelUsername) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        )

        OutlinedTextField(
            value = pass,
            onValueChange = { setPass(it) },
            label = { Text(Strings.LabelPass) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (passVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                val description = if (passVisible) "Ocultar contraseña" else "Mostrar contraseña"

                IconButton(onClick = { setPassVisible(!passVisible) }) {
                    Icon(imageVector = image, contentDescription = description)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    if (username.isBlank()) {
                        Toast.makeText(context, Strings.ErrorUsername, Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    if (pass.isBlank()) {
                        Toast.makeText(context, Strings.ErrorPass, Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    auth.signInWithEmailAndPassword(username, pass)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(context, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                                navHostController.navigate(NavigationStrings.ItemMenuRouteRecursos)
                            } else {
                                val rawMessage = task.exception?.message ?: ""
                                if (rawMessage.contains("")) {
                                    setUsername("")
                                    setPass("")
                                    Toast.makeText(
                                        context,
                                        "No existe una cuenta con este correo electrónico.",
                                        Toast.LENGTH_LONG
                                    ).show()
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Error al iniciar sesión. Verifica tus credenciales.",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF00796B)
                )
            ) {
                Text(text = Strings.TextLogin, color = Color.White)
            }

            Button(
                onClick = {
                    navHostController.navigate(NavigationStrings.ItemMenuRouteSignup)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF00796B)
                )
            ) {
                Text(text = Strings.TextNoCuenta, color = Color.White)
            }
        }
    }
}
