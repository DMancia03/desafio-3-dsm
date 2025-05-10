package sv.edu.udb.colegiostone_recursos.components.login

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
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
        modifier = modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        val (username, setUsername) = remember { mutableStateOf("") }
        val (pass, setPass) = remember { mutableStateOf("") }
        val (passVisible, setPassVisible) = remember { mutableStateOf(false) }

        val context = LocalContext.current
        val auth = FirebaseAuth.getInstance()

        Text(
            text = Strings.NombreColegio,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        OutlinedTextField(
            value = username,
            onValueChange = { setUsername(it) },
            label = { Text(Strings.LabelUsername) },
            singleLine = true
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
            }
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(onClick = {
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
            }) {
                Text(text = Strings.TextLogin)
            }

            Button(onClick = {
                navHostController.navigate(NavigationStrings.ItemMenuRouteSignup)
            }) {
                Text(text = Strings.TextNoCuenta)
            }
        }
    }
}
