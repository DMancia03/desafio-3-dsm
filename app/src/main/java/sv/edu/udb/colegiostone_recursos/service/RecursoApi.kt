package sv.edu.udb.colegiostone_recursos.service

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import sv.edu.udb.colegiostone_recursos.models.RecursoAprendizaje

interface RecursoApi {
    @GET("colegio/recursos")
    fun ObtenerRecursos() : Call<List<RecursoAprendizaje>>

    @GET("colegio/recursos/{id}")
    fun ObtenerRecursoPorId(@Path("id") id : Int) : Call<RecursoAprendizaje>

    @POST("colegio/recursos")
    fun CrearRecurso(@Body recursoAprendizaje: RecursoAprendizaje) : Call<RecursoAprendizaje>

    @PUT("colegio/recursos/{id}")
    fun ActualizarRecurso(@Path("id") id : Int, @Body recursoAprendizaje: RecursoAprendizaje) : Call<RecursoAprendizaje>

    @DELETE("colegio/recursos/{id}")
    fun EliminarRecurso(@Path("id") id : Int) : Call<Void>
}