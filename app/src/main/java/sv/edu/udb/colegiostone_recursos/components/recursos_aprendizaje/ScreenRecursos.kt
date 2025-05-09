package sv.edu.udb.colegiostone_recursos.components.recursos_aprendizaje

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.snapshots
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sv.edu.udb.colegiostone_recursos.models.RecursoAprendizaje
import sv.edu.udb.colegiostone_recursos.service.RecursoApi
import sv.edu.udb.colegiostone_recursos.utils.NavigationStrings
import sv.edu.udb.colegiostone_recursos.utils.Strings
import java.io.Console

@Composable
fun ScreenRecursos(
    modifier: Modifier,
    navHostController: NavHostController
) {
    Column(
        modifier = modifier
    ) {
        val context : Context = LocalContext.current

        // Variables de estado en formulario
        val (titulo, setTitulo) = remember { mutableStateOf("") }

        val recursos = remember { mutableStateListOf<RecursoAprendizaje>() }

        val retrofit = Retrofit.Builder()
            .baseUrl(NavigationStrings.EndPointApi)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(RecursoApi::class.java)

        val call = api.ObtenerRecursos()

        call.enqueue(object : Callback<List<RecursoAprendizaje>>{
            override fun onResponse(
                call: Call<List<RecursoAprendizaje>>,
                response: Response<List<RecursoAprendizaje>>
            ) {
                if(response.isSuccessful){
                    val list = response.body()

                    if(list != null){
                        recursos.clear()

                        if(titulo.isNotEmpty() && titulo.isNotBlank()){
                            recursos.addAll(list.filter { it.Titulo.contains(titulo) })
                        }else{
                            recursos.addAll(list)
                        }
                    }
                }/*else{
                    Toast.makeText(
                        context,
                        Strings.MsgReadAllIncompleto,
                        Toast.LENGTH_SHORT
                    ).show()
                }*/
            }

            override fun onFailure(call: Call<List<RecursoAprendizaje>>, t: Throwable) {
                Toast.makeText(
                    context,
                    Strings.MsgReadAllError,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        Button(
            onClick = {
                navHostController.navigate(NavigationStrings.ItemMenuRouteRecursosForm)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = Strings.BtnAgregar
            )
        }

        OutlinedTextField(
            value = titulo,
            onValueChange = { setTitulo(it) },
            label = {
                Text(Strings.LabelBuscarPorTitulo)
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        if(recursos.count() > 0){
            LazyColumn {
                items(recursos){ recurso ->
                    RecursoCard(
                        recurso = recurso,
                        navHostController = navHostController,
                        api = api,
                        context = context
                    )
                }
            }
        }else{
            Text(
                text = Strings.TextNoRecursos
            )
        }
    }
}