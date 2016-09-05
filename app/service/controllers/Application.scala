package service.controllers

import logic.CubeSummationLogic
import play.api.libs.json.{JsError, JsResult, JsSuccess, Json}
import play.api.mvc._
import service.request.CubeSummationRequest
import service.request.CubeSummationRequest._

import scala.util.{Failure, Success}

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
      case JsSuccess(res, a) =>
        val logic = CubeSummationLogic()
        val result = logic.calculateCubeOperations(res)
        result match {
          case Success(resOp) => Ok(Json.toJson(resOp))
          case Failure(err) => InternalServerError("Operaciones Erroneas")
        }
      case JsError(err) => println("Errors: " + JsError.toJson(err).toString())
        BadRequest("Error en el mapeo del request")
    }
  }
}