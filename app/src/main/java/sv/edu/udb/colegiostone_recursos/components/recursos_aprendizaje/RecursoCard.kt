package sv.edu.udb.colegiostone_recursos.components.recursos_aprendizaje

import android.os.Bundle
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.database.DatabaseReference
import sv.edu.udb.colegiostone_recursos.models.RecursoAprendizaje
import sv.edu.udb.colegiostone_recursos.utils.NavigationStrings

@Composable
fun RecursoCard(
    recurso : RecursoAprendizaje,
    navHostController: NavHostController,
    db : DatabaseReference
) {
    Column(
        modifier = Modifier.padding(
            top = 10.dp,
            bottom = 10.dp
        )
    ) {
        Text(
            text = recurso.Titulo,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = recurso.Descripcion
        )

        Text(
            text = "Tipo: ${recurso.Tipo}",
            fontWeight = FontWeight.Light
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button({
                navHostController.navigate(
                    route = "${NavigationStrings.ItemMenuRouteRecursosVer}?key=${recurso.Key}&titulo=${recurso.Titulo}&descripcion=${recurso.Descripcion}&tipo=${recurso.Tipo}&enlace=${recurso.Enlace}&imagen=${recurso.Imagen}"
                )
            }) {
                Text(
                    text = "Ver"
                )
            }

            Button({
                navHostController.navigate(
                    route = "${NavigationStrings.ItemMenuRouteRecursosForm}?action=${NavigationStrings.ActionUpdate}&key=${recurso.Key}&titulo=${recurso.Titulo}&descripcion=${recurso.Descripcion}&tipo=${recurso.Tipo}&enlace=${recurso.Enlace}&imagen=${recurso.Imagen}"
                )
            }) {
                Text(
                    text = "Editar"
                )
            }

            Button({
                db.child(recurso.Key.toString()).removeValue()
            }) {
                Text(
                    text = "Eliminar"
                )
            }
        }
    }
}