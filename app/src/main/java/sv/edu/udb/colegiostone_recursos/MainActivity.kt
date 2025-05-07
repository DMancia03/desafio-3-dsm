package sv.edu.udb.colegiostone_recursos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import sv.edu.udb.colegiostone_recursos.components.login.ScreenLogin
import sv.edu.udb.colegiostone_recursos.components.recursos_aprendizaje.ScreenRecursos
import sv.edu.udb.colegiostone_recursos.components.signup.ScreenSignup
import sv.edu.udb.colegiostone_recursos.navigation.NavigationStrings
import sv.edu.udb.colegiostone_recursos.ui.theme.ColegioStoneRecursosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ColegioStoneRecursosTheme {
                Surface {
                    MainComponent()
                }
            }
        }
    }
}

@Composable
fun MainComponent() {
    val navHostController : NavHostController = rememberNavController()

    Scaffold (
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        NavHost (
            navController = navHostController,
            startDestination = NavigationStrings.ItemMenuRouteLogin,
            modifier = Modifier.padding(innerPadding).padding(all = 10.dp)
        ) {
            composable(
                NavigationStrings.ItemMenuRouteLogin
            ) {
                ScreenLogin(
                    modifier = Modifier,
                    navHostController = navHostController
                )
            }

            composable(
                NavigationStrings.ItemMenuRouteSignup
            ) {
                ScreenSignup(
                    modifier = Modifier,
                    navHostController = navHostController
                )
            }

            composable(
                NavigationStrings.ItemMenuRouteRecursos
            ) {
                ScreenRecursos(
                    modifier = Modifier,
                    navHostController = navHostController
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainComponentPreview() {
    ColegioStoneRecursosTheme {
        Surface {
            MainComponent()
        }
    }
}