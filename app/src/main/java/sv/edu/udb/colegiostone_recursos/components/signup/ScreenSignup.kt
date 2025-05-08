package sv.edu.udb.colegiostone_recursos.components.signup

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import sv.edu.udb.colegiostone_recursos.utils.NavigationStrings
import sv.edu.udb.colegiostone_recursos.utils.Strings

@Composable
fun ScreenSignup (
    modifier: Modifier,
    navHostController: NavHostController
) {
    Column(
        modifier = modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Variables
        val (username, setUsername) = remember { mutableStateOf("") }
        val (pass, setPass) = remember { mutableStateOf("") }
        val (passVisible, setPassVisible) = remember { mutableStateOf(false) }

        // Contexto
        val context = LocalContext.current

        Text(
            text = Strings.NombreColegio,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        OutlinedTextField(
            value = username,
            onValueChange = { setUsername(it) },
            label = {
                Text(Strings.LabelUsername)
            },
            singleLine = true
        )

        OutlinedTextField(
            value = pass,
            onValueChange = { setPass(it) },
            label = {
                Text(Strings.LabelPass)
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if(passVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                // Please provide localized description for accessibility services
                val description = if (passVisible) "Hide password" else "Show password"

                IconButton(
                    onClick = {setPassVisible(!passVisible)}
                ){
                    Icon(imageVector  = image, description)
                }
            }
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button({
                if(username.isNullOrEmpty() || username.isNullOrBlank()){
                    Toast.makeText(
                        context,
                        Strings.ErrorUsername,
                        Toast.LENGTH_SHORT
                    ).show()

                    return@Button
                }

                if(pass.isNullOrEmpty() || pass.isNullOrBlank()){
                    Toast.makeText(
                        context,
                        Strings.ErrorPass,
                        Toast.LENGTH_SHORT
                    ).show()

                    return@Button
                }

                navHostController.navigate(NavigationStrings.ItemMenuRouteRecursos)
            }) {
                Text(
                    text = Strings.TextSignUp
                )
            }

            Button({
                navHostController.navigate(NavigationStrings.ItemMenuRouteLogin)
            }) {
                Text(
                    text = Strings.TextSiCuenta
                )
            }
        }
    }
}