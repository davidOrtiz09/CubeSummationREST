## Synopsis
Proyecto web que muestra la solución al problema "Cube Summation" de #HackerRank. Este proyecto fue hecho
en Scala y para la exposición de servicios se uso Play Framework.

El enunciado del problema se puede encontrar aquí: 
https://www.hackerrank.com/challenges/cube-summation


## Librerias

* Scala 2.11.8
* SBT 0.13.5

|   libreria         |   Version         | 
|   -------------    |   -------------   |
|   (org.scalatestplus.play)                                                    |   1.5.0  |
|   (https://www.playframework.com/)                                            |   2.5.6  |

Para iniciar la aplicación es necesario tener instalado java y sbt(http://www.scala-sbt.org/). Una vez intalados puede clonar
el proyecto y dentro del directorio ejecutar el comando de consola "sbt run". La aplicación corre bajo  **localhost:9000**. Para correr los test unitarios ejecute el comando "sbt test".

La ruta habilitada para insertar los casos de prueba es java y sbt :
                              - POST  /cube/execute/summation
                              
                              La estructura del json del POST es como la del siguiente ejemplo:
                              
                              ```json 
                              { 
                              "t": 2 , "cases":[{"n":2 , "m":2 ,"operations":
                              ["UPDATE 2 2 2 4" , "QUERY 1 1 1 3 3 3"]} ,
                              {"n":2 , "m":2 ,"operations":["UPDATE 2 2 2 4" ,"QUERY 1 1 1 3 3 3"]}]
                              }
                              ```



