package service.controllers

import play.api.mvc._

class Application extends Controller {

   /**
    * Verifica que el servidor esta funcionando
    */
  def index = Action { Ok("Todo esta funcionando") }

}