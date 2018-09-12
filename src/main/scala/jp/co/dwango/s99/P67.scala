package jp.co.dwango.s99
import jp.co.dwango.s99.binary_trees._

object P67 {
  implicit class RichTree[T](tree: Tree[T]) {
    def toString2: String = ???
  }
  object Tree {
    def fromString(str: String): Node[Char] = ???
  }

  sealed trait Result[+T]
  case class Failure(msg: String) extends Result[Nothing]
  case class Success[+T](result: T, rest: String) extends Result[T]

  object ~ {
    def unapply[T, U](arg: (T, U)): Option[(T, U)] = Some(arg)
  }

  class Parser[+T](fn: String => Result[T]) extends (String => Result[T]) {
    override def apply(v1: String): Result[T] = fn(v1)
    def ~[U](that: => Parser[U]): Parser[(T, U)] =
      new Parser(input =>
        this.apply(input) match {
          case f @ Failure(_) => f
          case Success(result, rest) =>
            that(rest) match {
              case f @ Failure(_)          => f
              case Success(result2, rest2) => Success(result -> result2, rest2)
            }
      })
    def ? : Parser[Option[T]] =
      new Parser(input =>
        this.apply(input) match {
          case Failure(_)            => Success(None, input)
          case Success(result, rest) => Success(Some(result), rest)
      })
    def |[U >: T](that: => Parser[U]): Parser[U] =
      new Parser(input =>
        this.apply(input) match {
          case Failure(_) =>
            that(input) match {
              case Success(result2, rest2) => Success(result2, rest2)
            }
          case Success(result, rest) => Success(result, rest)
      })
    def ^^[U](fn: T => U): Parser[U] =
      new Parser(input =>
        this.apply(input) match {
          case f @ Failure(_)        => f
          case Success(result, rest) => Success(fn(result), rest)
      })
  }
  lazy val letter: Parser[Char] = new Parser(input => {
    if (input.length == 0) Failure("no rest input")
    else {
      val c0 = input.charAt(0)
      if (c0 >= 'A' && c0 <= 'Z' || c0 >= 'a' && c0 <= 'z')
        Success(c0, input.substring(1))
      else Failure(c0 + " is not a letter")
    }
  })
  def char(c: Char): Parser[Char] =
    new Parser(input => {
      if (input.length == 0) Failure("no rest input")
      else {
        val c0 = input.charAt(0)
        if (c0 == c)
          Success(c0, input.substring(1))
        else Failure(c0 + " is not " + c)
      }
    })
  lazy val node
    : Parser[Node[Char]] = letter ~ (char('(') ~ node.? ~ char(',') ~ node.? ~ char(
    ')')).? ^^ {
    case c ~ None => Node(c, End, End)
    case c ~ Some(_ ~ leftOption ~ _ ~ rightOption ~ _) =>
      Node(c, leftOption.getOrElse(End), rightOption.getOrElse(End))
  }
}
