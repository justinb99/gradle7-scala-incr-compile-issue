package justinb99.module1

object StringUtil {
  implicit class StringExtension(string: String) {
    def toInteger: Int =
      string.toInt
  }
}
