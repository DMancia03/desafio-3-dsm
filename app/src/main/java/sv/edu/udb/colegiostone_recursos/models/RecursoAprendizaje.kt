package sv.edu.udb.colegiostone_recursos.models

class RecursoAprendizaje {
    //var Key : String = ""
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
}