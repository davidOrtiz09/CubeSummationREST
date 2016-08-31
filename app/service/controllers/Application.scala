package service.controllers

import play.api.libs.json.{JsError, JsResult, JsSuccess}
import play.api.mvc._
import service.request.CubeSummationRequest
import service.request.CubeSummationRequest._

/**
  * Controlador donde estan todos los servicios del API.
  */
class Application extends Controller {

   /**
    * servicio que verifica que el servidor esta funcionando
    */
  def index = Action { Ok("Todo esta funcionando") }

  /**
    * Servicio que ejecuta los test case enviados
    */
  def executeCubeSummation =  Action(parse.json){ request =>
    val validation:JsResult[CubeSummationRequest] = request.body.validate[CubeSummationRequest]
    validation match{
      case JsSuccess(res, a) => Ok("mapeo de entidades exitoso")
      case JsError(err) => println("Errors: " + JsError.toJson(err).toString())
        InternalServerError("Error en el mapeo del request")
    }
  }
}