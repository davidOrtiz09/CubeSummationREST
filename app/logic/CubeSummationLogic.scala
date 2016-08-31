package logic

import domain.{UpdateOperation, QueryOperation, Operation, Cube}
import service.request.{OperationRequest, CubeSummationRequest}

import scala.util.Try

/**
  * Created by davidorto on 30/08/16.
  */
case class CubeSummationLogic() {

  def calculateCubeOperations(request:CubeSummationRequest):List[String] = {
   val cubeCreation=  request.cases.map( op => convertCubesAndOprations(op))
    List()
  }

  private def convertCubesAndOprations(request:OperationRequest):Try[(Cube,List[Option[Operation]])] = {

    Try {
      val patternUpdate = "(UPDATE)(.*)".r
      val patternQuery = "(QUERY)(.*)".r
      val cube = Cube(request.m)
      val operations: List[Option[Operation]] = request.operations.map { op =>
        op match {
          case patternUpdate(res, param) => println("update " + param.split(" ").toList.tail.map(_.toInt))
            Some(UpdateOperation(1, 2, 3, 4))
          case patternQuery(res, param) => println("query " + param.split(" ").toList.tail.map(_.toInt))
            Some(QueryOperation(1, 2, 3, 4, 5, 6))
          case _ => None
        }
      }
      (cube, operations)
    }
  }

}
