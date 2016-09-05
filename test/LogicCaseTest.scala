import logic.CubeSummationLogic

import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}
import play.api.mvc.Results
import service.request.{CubeSummationRequest, OperationRequest}
import scala.util.{Failure, Success}

/**
  * Created by davidorto on 4/09/16.
  */
class LogicCaseTest extends PlaySpec with Results with OneAppPerSuite {

  "Dada las siguientes operaciones aplicadas al cubo" should {"deberia retornar querys exitosas"
    "caso 1" in {
      val cubeSum = CubeSummationLogic()
      val numCases = 2
      val oper1 = OperationRequest(4, 5, List("UPDATE 2 2 2 4", "QUERY 1 1 1 3 3 3", "UPDATE 1 1 1 23", "QUERY 2 2 2 4 4 4", "QUERY 1 1 1 3 3 3"))
      val oper2 = OperationRequest(2, 4, List("UPDATE 2 2 2 1", "QUERY 1 1 1 1 1 1", "QUERY 1 1 1 2 2 2", "QUERY 2 2 2 2 2 2"))
      val listOperations = List(oper1, oper2)

      val request = CubeSummationRequest(numCases, listOperations)


      val operationsResult = List("4", "4", "27", "0", "1", "1")

      val result = cubeSum.calculateCubeOperations(request)
      result match {
        case Success(res) => assert(res == operationsResult)
        case Failure(err) => val ans = false
          assert(ans)
      }
    }
  }

  "Dada las siguientes operaciones aplicadas al cubo" should {
    "deberia retornar querys exitosas"
    "caso 2" in {
      val cubeSum = CubeSummationLogic()
      val numCases = 1
      val oper1 = OperationRequest(4, 5, List("UPDATE 1 1 1 4", "UPDATE 2 2 2 5", "UPDATE 3 3 3 8", "QUERY 1 1 1 4 4 4", "QUERY 1 1 1 2 2 2"))
      val listOperations = List(oper1)

      val request = CubeSummationRequest(numCases, listOperations)


      val operationsResult = List("17", "9")

      val result = cubeSum.calculateCubeOperations(request)
      result match {
        case Success(res) => assert(res == operationsResult)
        case Failure(err) => val ans = false
          assert(ans)
      }
    }
  }


  "Dada las siguientes operaciones erroneas aplicadas al cubo" should {
    "deberia retornar una lista vacia" in {
      val cubeSum = CubeSummationLogic()
      val numCases = 1
      val oper1 = OperationRequest(4, 5, List("UPATE 1 1 1 4", "UPDE 2 2 2 5", "ATE 3 3 3 8", "QY 1 1 1 4 4 4", "QRY 1 1 1 2 2 2"))
      val listOperations = List(oper1)

      val request = CubeSummationRequest(numCases, listOperations)

      val result = cubeSum.calculateCubeOperations(request)
      result match {
        case Success(res) => assert(res == List())
        case Failure(err) => val ans = false
          assert(ans)
      }
    }
  }

}
