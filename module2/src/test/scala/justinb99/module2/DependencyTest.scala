package justinb99.module2

import org.scalatest.{FunSpec, Matchers}

class DependencyTest extends FunSpec with Matchers {
  describe("Dependency") {
    it("should work") {
      Dependency.invokeDependency("500") should include("500")
    }
  }

}
