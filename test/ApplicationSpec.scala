import akka.stream.Materializer
import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}
import play.api.libs.json.Json
import play.api.mvc._
import play.api.test.Helpers._
import play.api.test._
import service.controllers.Application

import scala.concurrent.Future

/**
  * Unidad de Test donde se prueban las funcionalidades
  * del controlador de la aplicacion
  */
class ApplicationSpec extends PlaySpec with Results with OneAppPerSuite {

  implicit lazy val materializer: Materializer = app.materializer

  "Para comprobar que el servidor esta funcionando" should {
    val respuesta = "Todo esta funcionando"
    "retirnar una respuesta valida como respuesta" in {

      val controller = new Application()
      val result: Future[Result] = controller.index().apply(FakeRequest(GET, "/"))
      val bodyText: String = contentAsString(result)
      bodyText mustBe respuesta
    }
  }

  "Para comprobar que el cube summation funciona " should {
    val respuesta = "mapeo de entidades exitoso"

    "mostrar que los request se mapean correctamente" in {

      val body = Json.parse(
        """{ "t": 2 , "cases":[{"n":2 , "m":2 ,"operations":["UPDATE 2 2 2 4" , "QUERY 1 1 1 3 3 3"]} ,{"n":2 , "m":2 ,"operations":["UPDATE 2 2 2 4" , "QUERY 1 1 1 3 3 3"]}]}""")

      val controller = new Application()
      val route = "/cube/execute/summation"
      val result = controller.executeCubeSummation.apply(FakeRequest(POST, route).withBody(body))
      val serviceStatus: Port = status(result)
      serviceStatus mustBe 200
    }

    "mostrar error al enviar un request mal formado" in {

      val body = Json.parse(
        """{ "t": 2 , "cases":[{ "m":2 ,"operations":["UPDATE 2 4" , "QUERY 1 1 1 3 3 3"]} ,{"n":2 , "m":2 ,"operations":["UPDATE 2 2 2 4" , "QUERY 1 1 1 3 3 3"]}]}""")

      val controller = new Application()
      val route = "/cube/execute/summation"
      val result = controller.executeCubeSummation.apply(FakeRequest(POST, route).withBody(body))
      val serviceStatus: Port = status(result)
      serviceStatus mustBe 400
    }
  }



}
