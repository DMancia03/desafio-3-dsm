package sv.edu.udb.colegiostone_recursos.components.recursos_aprendizaje

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.database.DatabaseReference
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sv.edu.udb.colegiostone_recursos.models.RecursoAprendizaje
import sv.edu.udb.colegiostone_recursos.service.RecursoApi
import sv.edu.udb.colegiostone_recursos.utils.NavigationStrings
import sv.edu.udb.colegiostone_recursos.utils.Strings

@Composable
fun RecursoCard(
    recurso : RecursoAprendizaje,
    navHostController: NavHostController,
    api : RecursoApi,
    context: Context
) {
    Column(
        modifier = Modifier.padding(
            top = 10.dp,
            bottom = 10.dp
        )
    ) {
        Text(
            text = recurso.Titulo,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp)
        )

        Text(
            text = recurso.Descripcion,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp)
        )

        Text(
            text = "Tipo: ${recurso.Tipo}",
            fontWeight = FontWeight.Light,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    navHostController.navigate(
                        "${NavigationStrings.ItemMenuRouteRecursosVer}?id=${recurso.Id}&titulo=${recurso.Titulo}&descripcion=${recurso.Descripcion}&tipo=${recurso.Tipo}&enlace=${recurso.Enlace}&imagen=${recurso.Imagen}"
                    )
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF00796B)
                )
            ) {
                Text(
                    text = Strings.BtnVer,
                    color = Color.White
                )
            }

            Button(
                onClick = {
                    navHostController.navigate(
                        "${NavigationStrings.ItemMenuRouteRecursosForm}?action=${NavigationStrings.ActionUpdate}&id=${recurso.Id}&titulo=${recurso.Titulo}&descripcion=${recurso.Descripcion}&tipo=${recurso.Tipo}&enlace=${recurso.Enlace}&imagen=${recurso.Imagen}"
                    )
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF00796B)
                )
            ) {
                Text(
                    text = Strings.BtnEditar,
                    color = Color.White
                )
            }

            Button(
                onClick = {
                    if(recurso.Id != null){
                        val call = api.EliminarRecurso(recurso.Id!!)
                        call.enqueue(object : Callback<Void>{
                            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                                if(response.isSuccessful){
                                    Toast.makeText(
                                        context,
                                        Strings.MsgEliminarCompleto,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }else{
                                    Toast.makeText(
                                        context,
                                        Strings.MsgEliminarIncompleto,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                            override fun onFailure(call: Call<Void>, t: Throwable) {
                                Toast.makeText(
                                    context,
                                    Strings.MsgEliminarError,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        })
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF00796B)
                )
            ) {
                Text(
                    text = Strings.BtnEliminar,
                    color = Color.White
                )
            }
        }
    }
}
