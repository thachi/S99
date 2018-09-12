package jp.co.dwango.s99

import jp.co.dwango.s99.binary_trees._

object P68 {
  implicit class RichTree[T](tree: Tree[T]) {
    def preorder: List[T] = ???
    def inorder: List[T] = ???
  }
  object Tree {
    def string2Tree(str: String): Node[Char] = ???
  }
}
