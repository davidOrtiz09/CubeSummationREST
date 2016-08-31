package service.request


/**
  * Clase que representa un caso de prueba
  * @param n: Define el tamano de la matriz
  * @param m: Cantidad de oepraciones a ejecutar
  * @param operations: Oepracion a ejecutar.
  */
case class OperationRequest (n: Int, m: Int, operations: List[String])
