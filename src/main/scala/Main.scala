import scala.io.Source
import scala.util.{Failure, Success, Using}

object Main {

  def main(args: Array[String]): Unit = {

      Using(Source.fromFile("data/memory.sort.txt")) {

        source => {
          val lines = source.getLines().toList
          println(s"number of services: ${lines.length}")
            lines
            .filter(!_.startsWith("NAME"))
            .map(_.split(""" """).filter(_.nonEmpty))
            .map(splitted => (splitted(1).filter(_.isDigit).toInt, splitted(2).filter(_.isDigit).toInt))
            .foldLeft(new Tuple2[Int,Int](0,0))((s,elem) => (s._1 + elem._1,s._2+elem._2))
        }
    } match {
      case Success(value) =>
        println(s"aggregate: $value")
      case Failure(exception) => println(exception)
    }
  }

}
