package justinb99.module2

import justinb99.module1.StringUtil.StringExtension

object Dependency {
  def invokeDependency(string: String): String =
    s"string=$string, toInt=${string.toInt}, toInteger=${string.toInteger}"
}
