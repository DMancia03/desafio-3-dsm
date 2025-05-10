package sv.edu.udb.colegiostone_recursos.components.recursos_aprendizaje

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import sv.edu.udb.colegiostone_recursos.utils.NavigationStrings
import sv.edu.udb.colegiostone_recursos.utils.Strings

@Composable
fun ScreenVerRecurso(
    modifier: Modifier,
    navHostController: NavHostController
) {
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
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = tituloDefault,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Justify,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = descripcionDefault,
            textAlign = TextAlign.Justify,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "Tipo: ${tipoDefault}",
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Justify,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = enlaceDefault,
            color = Color.Blue,
            textAlign = TextAlign.Justify,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = imagenDefault,
            color = Color.Blue,
            textAlign = TextAlign.Justify,
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                navHostController.navigateUp()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF00796B)
            ),
            modifier = Modifier.fillMaxWidth(0.6f)
        ) {
            Text(
                text = Strings.TextRegresar,
                color = Color.White
            )
        }
    }
}
