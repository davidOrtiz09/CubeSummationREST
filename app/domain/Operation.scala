package domain


sealed trait Operation

case class QueryOperation(x1:Int,y1:Int,z1:Int,x2:Int,y2:Int,z2:Int) extends Operation

case class UpdateOperation(x:Int,y:Int,z:Int,w:Int) extends Operation
