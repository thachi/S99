package jp.co.dwango.s99

import jp.co.dwango.s99.binary_trees.{Node, Tree}

object P57 {
  implicit class RichTree[T](self: Tree[T]) {
    def addValue[U >: T](newValue: U)(
        implicit view: T => Ordered[U]): Node[U] = ???
  }
  object Tree {
    def fromList[T](elements: List[T])(
      implicit view: T => Ordered[T]): Tree[T] = ???
  }
}
