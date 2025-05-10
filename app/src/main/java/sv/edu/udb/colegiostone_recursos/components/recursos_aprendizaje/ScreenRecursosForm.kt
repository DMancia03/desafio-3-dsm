package sv.edu.udb.colegiostone_recursos.components.recursos_aprendizaje

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sv.edu.udb.colegiostone_recursos.models.RecursoAprendizaje
import sv.edu.udb.colegiostone_recursos.service.RecursoApi
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
        var action : String? = NavigationStrings.ActionCreate
        var idToUpdate : Int? = 0
        var tituloDefault : String = ""
        var descripcionDefault : String = ""
        var tipoDefault : String = ""
        var enlaceDefault : String = ""
        var imagenDefault : String = ""

        if(navHostController.currentBackStackEntry != null && navHostController.currentBackStackEntry?.arguments != null){
            action = navHostController.currentBackStackEntry!!.arguments!!.getString("action")
            idToUpdate = navHostController.currentBackStackEntry!!.arguments!!.getInt("id")
            tituloDefault = navHostController.currentBackStackEntry!!.arguments!!.getString("titulo") ?: ""
            descripcionDefault = navHostController.currentBackStackEntry!!.arguments!!.getString("descripcion") ?: ""
            tipoDefault = navHostController.currentBackStackEntry!!.arguments!!.getString("tipo") ?: ""
            enlaceDefault = navHostController.currentBackStackEntry!!.arguments!!.getString("enlace") ?: ""
            imagenDefault = navHostController.currentBackStackEntry!!.arguments!!.getString("imagen") ?: ""
        }

        val context : Context = LocalContext.current

        val retrofit = Retrofit.Builder()
            .baseUrl(NavigationStrings.EndPointApi)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(RecursoApi::class.java)

        val (titulo, setTitulo) = remember { mutableStateOf(tituloDefault) }
        val (descripcion, setDescripcion) = remember { mutableStateOf(descripcionDefault) }
        val (tipo, setTipo) = remember { mutableStateOf(tipoDefault) }
        val (enlace, setEnlace) = remember { mutableStateOf(enlaceDefault) }
        val (imagen, setImagen) = remember { mutableStateOf(imagenDefault) }

        Text(
            text = if (action == NavigationStrings.ActionCreate) Strings.TituloCrearRecurso else Strings.TituloEditarRecurso,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = titulo,
            onValueChange = { setTitulo(it) },
            label = {
                Text(Strings.LabelTitulo)
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = descripcion,
            onValueChange = { setDescripcion(it) },
            label = {
                Text(Strings.LabelDescripcion)
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = tipo,
            onValueChange = { setTipo(it) },
            label = {
                Text(Strings.LabelTipo)
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = enlace,
            onValueChange = { setEnlace(it) },
            label = {
                Text(Strings.LabelEnlace)
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = imagen,
            onValueChange = { setImagen(it) },
            label = {
                Text(Strings.LabelImagen)
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = {
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

                    var call : Call<RecursoAprendizaje>? = null

                    if(action == NavigationStrings.ActionCreate){
                        call = api.CrearRecurso(recurso)
                    }else{
                        call = api.ActualizarRecurso(idToUpdate!!, recurso)
                    }

                    call.enqueue(object : Callback<RecursoAprendizaje> {
                        override fun onResponse(
                            call: Call<RecursoAprendizaje>,
                            response: Response<RecursoAprendizaje>
                        ) {
                            if(response.isSuccessful){
                                Toast.makeText(
                                    context,
                                    Strings.RecursoGuardado,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }else{
                                Toast.makeText(
                                    context,
                                    Strings.RecursoIncompleto,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            navHostController.navigateUp()
                        }

                        override fun onFailure(call: Call<RecursoAprendizaje>, t: Throwable) {
                            Toast.makeText(
                                context,
                                Strings.RecursoErrorApi,
                                Toast.LENGTH_SHORT
                            ).show()

                            navHostController.navigateUp()
                        }
                    })
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF00796B)
                ),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = Strings.TextGuardar,
                    color = Color.White
                )
            }

            Button(
                onClick = {
                    navHostController.navigateUp()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF00796B)
                ),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = Strings.TextCancelar,
                    color = Color.White
                )
            }
        }
    }
}
