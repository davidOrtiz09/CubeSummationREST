package service.request

/**
  * Clase que representa el request de todo un conjunto de
  * escenarios de pruebas dados por el usuario.
  * @param t: Numero de casos de prueba
  * @param cases: Escenarios que contienen operaciones
  */
case class CubeSummationRequest(t: Int, cases: List[OperationRequest])
