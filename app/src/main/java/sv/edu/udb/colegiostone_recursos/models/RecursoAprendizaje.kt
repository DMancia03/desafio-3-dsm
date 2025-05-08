package sv.edu.udb.colegiostone_recursos.models

class RecursoAprendizaje {
    var Key : String? = null
    var Titulo : String = ""
    var Descripcion : String = ""
    var Tipo : String = ""
    var Enlace : String = ""
    var Imagen : String = ""

    constructor(){
        //
    }

    constructor(
        //_key : String,
        _titulo : String,
        _descripcion : String,
        _tipo : String,
        _enlace : String,
        _imagen : String
    ){
        //Key = _key
        Titulo = _titulo
        Descripcion = _descripcion
        Tipo = _tipo
        Enlace = _enlace
        Imagen = _imagen
    }

    fun toMap() : Map<String, Any>{
        return mapOf(
            "Titulo" to Titulo,
            "Descripcion" to Descripcion,
            "Tipo" to Tipo,
            "Enlace" to Enlace,
            "Imagen" to Imagen
        )
    }
}