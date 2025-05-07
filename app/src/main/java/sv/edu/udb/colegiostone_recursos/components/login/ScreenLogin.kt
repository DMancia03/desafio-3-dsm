package sv.edu.udb.colegiostone_recursos.components.login

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun ScreenLogin(
    modifier: Modifier,
    navHostController: NavHostController
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Inicio de sesion..."
        )
    }
}