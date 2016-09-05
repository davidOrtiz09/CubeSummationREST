package logic

import domain._
import service.request.{CubeSummationRequest, OperationRequest}

import scala.util.{Success, Try}

/**
  * Clase donde se encanpazula toda la logica de negocio.
  */
case class CubeSummationLogic() {


  /**
    * Calculas todas las operaciones sobre los cubos
    *
    * @param request : Request http con toda la info de los casos
    * @return
    */
  def calculateCubeOperations(request: CubeSummationRequest): Try[List[String]] = {
    Try {
      val cubeCreation: List[(Cube, List[Option[Operation]])] = request.cases.map(op => convertCubesAndOprations(op))
      val result: List[String] = cubeCreation.flatMap(x => calculateEachOperation(x))
      result
    }

  }


  /**
    * Convierte el request http de operaciones y lo estrcutura a logica de negocio para extraer sus opraciones
    * y crear el cubo correspondeinte al caso
    *
    * @param request : Request http con toda la info de los casos
    * @return
    */
  private def convertCubesAndOprations(request: OperationRequest): (Cube, List[Option[Operation]]) = {

      val patternUpdate = "(UPDATE)(.*)".r
      val patternQuery = "(QUERY)(.*)".r
      val cube = Cube(request.m)
      val operations: List[Option[Operation]] = request.operations.map { op =>
        op match {
          case patternUpdate(res, param) => val numberList: Array[Int] = param.split(" ").toList.tail.map(_.toInt).toArray
            Some(UpdateOperation(numberList(0), numberList(1), numberList(2), numberList(3)))
          case patternQuery(res, param) => val numberList: Array[Int] = param.split(" ").toList.tail.map(_.toInt).toArray
            Some(QueryOperation(numberList(0), numberList(1), numberList(2), numberList(3), numberList(4), numberList(5)))
          case _ => None
        }
      }
      (cube, operations)
  }


  /**
    * Ejecuta cada oepracion existente sobre el cubo
    * y retorna su valor
    *
    * @param op : Lista de posibles operaciones y su correspondiente cubo
    * @return : Lista de resultados.
    */
  private def calculateEachOperation(op: (Cube, List[Option[Operation]])): List[String] = {
    val cube = op._1
    val posibleOeprations = op._2
    val operations: List[Operation] = posibleOeprations.collect { case Some(res) => res }

    val queryOperations = operations.map {
      case ope: QueryOperation =>
        cube.query(ope.x1, ope.y1, ope.z1, ope.x2, ope.y2, ope.z2)
      case ope: UpdateOperation =>
        val position = (ope.x, ope.y, ope.z)
        cube.update(position, ope.w)
    }

    queryOperations.collect { case Success(res) => res }.filter(x => x != "")
  }

}
