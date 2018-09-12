package jp.co.dwango.s99

object P47 {
  implicit class RichBoolean(val self: Boolean) extends AnyVal {
    def and(y: Boolean): Boolean = ???
    def or(y: Boolean): Boolean = ???
    def xor(y: Boolean): Boolean = ???
  }
  def not(x: Boolean): Boolean = ???
  def table2(f: (Boolean, Boolean) => Boolean): Unit = ???
}
