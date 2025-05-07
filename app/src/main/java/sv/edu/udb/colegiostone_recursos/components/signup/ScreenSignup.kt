package sv.edu.udb.colegiostone_recursos.components.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun ScreenSignup (
    modifier: Modifier,
    navHostController: NavHostController
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Registro..."
        )
    }
}