package sv.edu.udb.colegiostone_recursos.components.recursos_aprendizaje

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import sv.edu.udb.colegiostone_recursos.utils.NavigationStrings
import sv.edu.udb.colegiostone_recursos.utils.Strings

@Composable
fun ScreenVerRecurso(
    modifier: Modifier,
    navHostController: NavHostController
) {
    // Parametros de route
    var keyToUpdate : String? = ""
    var tituloDefault : String = ""
    var descripcionDefault : String = ""
    var tipoDefault : String = ""
    var enlaceDefault : String = ""
    var imagenDefault : String = ""

    if(navHostController.currentBackStackEntry != null && navHostController.currentBackStackEntry?.arguments != null){
        keyToUpdate = navHostController.currentBackStackEntry!!.arguments!!.getString("key")
        tituloDefault = navHostController.currentBackStackEntry!!.arguments!!.getString("titulo") ?: ""
        descripcionDefault = navHostController.currentBackStackEntry!!.arguments!!.getString("descripcion") ?: ""
        tipoDefault = navHostController.currentBackStackEntry!!.arguments!!.getString("tipo") ?: ""
        enlaceDefault = navHostController.currentBackStackEntry!!.arguments!!.getString("enlace") ?: ""
        imagenDefault = navHostController.currentBackStackEntry!!.arguments!!.getString("imagen") ?: ""
    }

    Column(
        modifier = modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        Text(
            text = tituloDefault,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = descripcionDefault
        )

        Text(
            text = "Tipo: ${tipoDefault}",
            fontWeight = FontWeight.Light
        )

        Text(
            text = enlaceDefault,
            color = Color.Blue
        )

        Text(
            text = imagenDefault,
            color = Color.Blue
        )

        Button({
            navHostController.navigateUp()
        }) {
            Text(
                text = Strings.TextRegresar
            )
        }
    }
}