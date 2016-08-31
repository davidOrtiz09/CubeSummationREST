import controllers.Application
import org.scalatestplus.play.PlaySpec

import scala.concurrent.Future
import play.api.mvc._
import play.api.test._
import play.api.test.Helpers._


/**
  * Unidad de Test donde se prueban las funcionalidades
  * del controlador de la aplicacion
  */
class ApplicationSpec extends PlaySpec with Results {

  "Para comprobar que el servidor esta funcionando" should {
    val respuesta = "Todo esta funcionando"
    "retirnar una respuesta valida como respuesta" in {

      val controller = new Application()
      val result: Future[Result] = controller.index().apply(FakeRequest(GET, "/"))
      val bodyText: String = contentAsString(result)
      bodyText mustBe respuesta
    }
  }


}
