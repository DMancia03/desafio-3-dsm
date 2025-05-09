package sv.edu.udb.colegiostone_recursos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import sv.edu.udb.colegiostone_recursos.components.login.ScreenLogin
import sv.edu.udb.colegiostone_recursos.components.recursos_aprendizaje.ScreenRecursos
import sv.edu.udb.colegiostone_recursos.components.recursos_aprendizaje.ScreenRecursosForm
import sv.edu.udb.colegiostone_recursos.components.recursos_aprendizaje.ScreenVerRecurso
import sv.edu.udb.colegiostone_recursos.components.signup.ScreenSignup
import sv.edu.udb.colegiostone_recursos.components.top_bar.TopBar
import sv.edu.udb.colegiostone_recursos.models.RecursoAprendizaje
import sv.edu.udb.colegiostone_recursos.utils.NavigationStrings
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
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar()
        }
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

            composable(
                "${NavigationStrings.ItemMenuRouteRecursosForm}?action={action}&id={id}&titulo={titulo}&descripcion={descripcion}&tipo={tipo}&enlace={enlace}&imagen={imagen}",
                arguments = listOf(
                    navArgument("action"){
                        type = NavType.StringType
                        defaultValue = NavigationStrings.ActionCreate
                    },
                    navArgument("id"){
                        type = NavType.IntType
                        defaultValue = 0
                    },
                    navArgument("titulo"){
                        type = NavType.StringType
                        defaultValue = ""
                    },
                    navArgument("descripcion"){
                        type = NavType.StringType
                        defaultValue = ""
                    },
                    navArgument("tipo"){
                        type = NavType.StringType
                        defaultValue = ""
                    },
                    navArgument("enlace"){
                        type = NavType.StringType
                        defaultValue = ""
                    },
                    navArgument("imagen"){
                        type = NavType.StringType
                        defaultValue = ""
                    },
                )
            ) {
                ScreenRecursosForm(
                    modifier = Modifier,
                    navHostController = navHostController
                )
            }

            composable(
                "${NavigationStrings.ItemMenuRouteRecursosVer}?id={id}&titulo={titulo}&descripcion={descripcion}&tipo={tipo}&enlace={enlace}&imagen={imagen}",
                arguments = listOf(
                    navArgument("id"){
                        type = NavType.IntType
                        defaultValue = 0
                    },
                    navArgument("titulo"){
                        type = NavType.StringType
                        defaultValue = ""
                    },
                    navArgument("descripcion"){
                        type = NavType.StringType
                        defaultValue = ""
                    },
                    navArgument("tipo"){
                        type = NavType.StringType
                        defaultValue = ""
                    },
                    navArgument("enlace"){
                        type = NavType.StringType
                        defaultValue = ""
                    },
                    navArgument("imagen"){
                        type = NavType.StringType
                        defaultValue = ""
                    },
                )
            ) {
                ScreenVerRecurso(
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