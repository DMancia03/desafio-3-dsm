package sv.edu.udb.colegiostone_recursos.components.recursos_aprendizaje

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import sv.edu.udb.colegiostone_recursos.models.RecursoAprendizaje

@Composable
fun RecursoCard(
    recurso : RecursoAprendizaje
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
                //
            }) {
                Text(
                    text = "Ver"
                )
            }

            Button({
                //
            }) {
                Text(
                    text = "Editar"
                )
            }

            Button({
                //
            }) {
                Text(
                    text = "Eliminar"
                )
            }
        }
    }
}