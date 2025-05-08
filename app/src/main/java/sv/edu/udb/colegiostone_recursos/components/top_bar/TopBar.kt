package sv.edu.udb.colegiostone_recursos.components.top_bar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import sv.edu.udb.colegiostone_recursos.utils.NavigationStrings

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar( ) {
    TopAppBar(
        title = {
            Text(NavigationStrings.NombreApp)
        }
    )
}