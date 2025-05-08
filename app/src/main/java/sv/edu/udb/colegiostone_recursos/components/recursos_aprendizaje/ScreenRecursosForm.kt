package sv.edu.udb.colegiostone_recursos.components.recursos_aprendizaje

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.database.FirebaseDatabase
import sv.edu.udb.colegiostone_recursos.models.RecursoAprendizaje
import sv.edu.udb.colegiostone_recursos.utils.Strings
import sv.edu.udb.colegiostone_recursos.utils.NavigationStrings

@Composable
fun ScreenRecursosForm(
    modifier: Modifier,
    navHostController: NavHostController
) {
    Column(
        modifier = modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Parametros de route
        var action : String? = NavigationStrings.ActionCreate
        var keyToUpdate : String? = ""
        var tituloDefault : String = ""
        var descripcionDefault : String = ""
        var tipoDefault : String = ""
        var enlaceDefault : String = ""
        var imagenDefault : String = ""

        if(navHostController.currentBackStackEntry != null && navHostController.currentBackStackEntry?.arguments != null){
            action = navHostController.currentBackStackEntry!!.arguments!!.getString("action")
            keyToUpdate = navHostController.currentBackStackEntry!!.arguments!!.getString("key")
            tituloDefault = navHostController.currentBackStackEntry!!.arguments!!.getString("titulo") ?: ""
            descripcionDefault = navHostController.currentBackStackEntry!!.arguments!!.getString("descripcion") ?: ""
            tipoDefault = navHostController.currentBackStackEntry!!.arguments!!.getString("tipo") ?: ""
            enlaceDefault = navHostController.currentBackStackEntry!!.arguments!!.getString("enlace") ?: ""
            imagenDefault = navHostController.currentBackStackEntry!!.arguments!!.getString("imagen") ?: ""
        }

        // Context
        val context : Context = LocalContext.current

        // Db
        val db = FirebaseDatabase.getInstance().getReference(NavigationStrings.DatabaseReference)

        // Variables de estado en formulario
        val (titulo, setTitulo) = remember { mutableStateOf(tituloDefault) }
        val (descripcion, setDescripcion) = remember { mutableStateOf(descripcionDefault) }
        val (tipo, setTipo) = remember { mutableStateOf(tipoDefault) }
        val (enlace, setEnlace) = remember { mutableStateOf(enlaceDefault) }
        val (imagen, setImagen) = remember { mutableStateOf(imagenDefault) }

        Text(
            text = if (action == NavigationStrings.ActionCreate) Strings.TituloCrearRecurso else Strings.TituloEditarRecurso,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        OutlinedTextField(
            value = titulo,
            onValueChange = { setTitulo(it) },
            label = {
                Text(Strings.LabelTitulo)
            },
            singleLine = true
        )

        OutlinedTextField(
            value = descripcion,
            onValueChange = { setDescripcion(it) },
            label = {
                Text(Strings.LabelDescripcion)
            },
            singleLine = true
        )

        OutlinedTextField(
            value = tipo,
            onValueChange = { setTipo(it) },
            label = {
                Text(Strings.LabelTipo)
            },
            singleLine = true
        )

        OutlinedTextField(
            value = enlace,
            onValueChange = { setEnlace(it) },
            label = {
                Text(Strings.LabelEnlace)
            },
            singleLine = true
        )

        OutlinedTextField(
            value = imagen,
            onValueChange = { setImagen(it) },
            label = {
                Text(Strings.LabelImagen)
            },
            singleLine = true
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button({
                if(titulo.isNullOrEmpty() || titulo.isNullOrBlank()){
                    Toast.makeText(
                        context,
                        Strings.ErrorTitulo,
                        Toast.LENGTH_SHORT
                    ).show()

                    return@Button
                }

                if(descripcion.isNullOrEmpty() || descripcion.isNullOrBlank()){
                    Toast.makeText(
                        context,
                        Strings.ErrorDescripcion,
                        Toast.LENGTH_SHORT
                    ).show()

                    return@Button
                }

                if(tipo.isNullOrEmpty() || tipo.isNullOrBlank()){
                    Toast.makeText(
                        context,
                        Strings.ErrorTipo,
                        Toast.LENGTH_SHORT
                    ).show()

                    return@Button
                }

                if(enlace.isNullOrEmpty() || enlace.isNullOrBlank()){
                    Toast.makeText(
                        context,
                        Strings.ErrorEnlace,
                        Toast.LENGTH_SHORT
                    ).show()

                    return@Button
                }

                if(imagen.isNullOrEmpty() || imagen.isNullOrBlank()){
                    Toast.makeText(
                        context,
                        Strings.ErrorImagen,
                        Toast.LENGTH_SHORT
                    ).show()

                    return@Button
                }

                val recurso = RecursoAprendizaje(titulo, descripcion, tipo, enlace, imagen)

                if(action == NavigationStrings.ActionCreate){
                    val newKey = db.push().key

                    if(newKey != null){
                        db.child(newKey).setValue(recurso).addOnSuccessListener {
                            Toast.makeText(
                                context,
                                Strings.RecursoGuardado,
                                Toast.LENGTH_SHORT
                            ).show()
                        }.addOnFailureListener {
                            Toast.makeText(
                                context,
                                Strings.RecursoIncompleto,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }else{
                    val recursoValue = recurso.toMap()
                    val childUpdate = hashMapOf<String, Any>(
                        keyToUpdate.toString() to recursoValue
                    )

                    db.updateChildren(childUpdate).addOnSuccessListener {
                        Toast.makeText(
                            context,
                            Strings.RecursoGuardado,
                            Toast.LENGTH_SHORT
                        ).show()
                    }.addOnFailureListener {
                        Toast.makeText(
                            context,
                            Strings.RecursoIncompleto,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                navHostController.navigateUp()
            }) {
                Text(
                    text = Strings.TextGuardar
                )
            }

            Button({
                navHostController.navigateUp()
            }) {
                Text(
                    text = Strings.TextCancelar
                )
            }
        }
    }
}