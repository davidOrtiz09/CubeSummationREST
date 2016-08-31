package service.request
import play.api.libs.json.Json

/**
  * Clase que representa el request de todo un conjunto de
  * escenarios de pruebas dados por el usuario.
  * @param t: Numero de casos de prueba
  * @param cases: Escenarios que contienen operaciones
  */
case class CubeSummationRequest(t: Int, cases: List[OperationRequest])


/**
  * Companion que contiene los marshallers de [[CubeSummationRequest]] y [[OperationRequest]]
  */
object CubeSummationRequest{

  implicit val operationRead = Json.reads[OperationRequest]
  implicit val cubeRead = Json.reads[CubeSummationRequest]

}