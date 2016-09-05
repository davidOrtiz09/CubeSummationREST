package domain

import scala.util.Try

/**
  * Clase que representa el cubo
  * @param size: Tamano del cubo
  */
case class Cube(size:Int) {

 private var cubeMatrix =  Array.ofDim[Int](size,size,size)

  /**
    * Constructor del cubo
    *
    * @param size: Tamano del cubo
    */
  def apply(size:Int) = {
    cubeMatrix = Array.tabulate(size,size,size)((x,y,z) => (x + y +z)*0)
  }


  /**
    * Consulta el valor que se encuentra dentro
    * de la cordenada dada dentro del cubo.
    *
    * @param x1: cordenada x1
    * @param y1: cordenada y1
    * @param z1: cordeanda z1
    * @param x2: cordenada x2
    * @param y2: cordenada y2
    * @param z2: cordenada z2
    * @return
    */
  def query(x1: Int, y1: Int, z1: Int, x2: Int, y2: Int, z2: Int): Try[String] = {
    Try {
      var resultado = 0
      for {
        i <- x1 to x2
        j <- y1 to y2
        k <- z1 to z2
      } resultado = resultado + cubeMatrix(i)(j)(k)
      resultado.toString
    }
  }

  /**
    * actualiza el valor de las cordenadas
    * dadas en el cubo
    *
    * @param position: Cordenadas que se queiren actualizar en el cubo
    * @param value: Valor que se va a insertar en la matriz
    * @return
    */
  def update(position: (Int, Int, Int), value: Int): Try[String] = {
    Try {
      cubeMatrix(position._1)(position._2)(position._3) = value
      ""
    }
  }

}
