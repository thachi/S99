package jp.co.dwango.s99

object P90 {
  def solve: List[List[Int]] = ???
  def getPatterns(boardSize: Int)(
      col: Int = 0,
      restRow: Set[Int] = (0 until boardSize).toSet,
      limitations: List[Equation] = Nil): List[List[Int]] = ???

  sealed trait Formula {
    def calc(x: Int, y: Int): Int
  }
  case object X extends Formula {
    override def calc(x: Int, y: Int): Int = x
  }
  case object Y extends Formula {
    override def calc(x: Int, y: Int): Int = y
  }
  case class Const(i: Int) extends Formula {
    override def calc(x: Int, y: Int): Int = i
  }
  case class Add(v1: Formula, v2: Formula) extends Formula {
    override def calc(x: Int, y: Int): Int = v1.calc(x, y) + v2.calc(x, y)
  }
  case class Equation(v1: Formula, v2: Formula) {
    def check(x: Int, y: Int): Boolean = v1.calc(x, y) == v2.calc(x, y)
  }
}
