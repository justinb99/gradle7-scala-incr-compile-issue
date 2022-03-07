package justinb99.module2

import org.scalatest.{FunSpec, Matchers}

class IndependentTest extends FunSpec with Matchers {

  describe("Independent") {
    it("should work") {
      val ind = Independent(1, 2)
      ind.p2 shouldBe 2
    }
  }
}