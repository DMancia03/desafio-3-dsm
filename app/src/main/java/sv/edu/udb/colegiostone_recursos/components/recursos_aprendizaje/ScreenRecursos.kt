package sv.edu.udb.colegiostone_recursos.components.recursos_aprendizaje

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.snapshots
import sv.edu.udb.colegiostone_recursos.models.RecursoAprendizaje
import sv.edu.udb.colegiostone_recursos.utils.NavigationStrings
import java.io.Console

@Composable
fun ScreenRecursos(
    modifier: Modifier,
    navHostController: NavHostController
) {
    Column(
        modifier = modifier
    ) {


        val recursos = remember { mutableStateListOf<RecursoAprendizaje>() }

        val db : DatabaseReference = FirebaseDatabase.getInstance().getReference(NavigationStrings.DatabaseReference)

        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                recursos.clear()

                for (item in snapshot.children){
                    val recurso : RecursoAprendizaje? = item.getValue(RecursoAprendizaje::class.java)
                    if(recurso != null){
                        recurso.Key = item.key
                        recursos.add(recurso)
                    }
                }

                Log.i("FIREBASE", recursos.count().toString())
            }

            override fun onCancelled(error: DatabaseError) {
                //
            }
        })

        Button({
            navHostController.navigate(NavigationStrings.ItemMenuRouteRecursosForm)
        }) {
            Text(
                text = "Agregar recurso"
            )
        }

        if(recursos.count() > 0){
            LazyColumn {
                items(recursos){ recurso ->
                    RecursoCard(
                        recurso = recurso,
                        navHostController = navHostController,
                        db = db
                    )
                }
            }
        }else{
            Text(
                text = "No hay recursos agregados..."
            )
        }
    }
}