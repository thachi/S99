package jp.co.dwango.s99
import jp.co.dwango.s99.binary_trees._

object P56 {
  implicit class RichTree[T](val tree: Tree[T]) extends AnyVal {
    def isMirrorOf(another: Tree[T]): Boolean = ???
    def isSymmetric: Boolean = ???
  }
}
