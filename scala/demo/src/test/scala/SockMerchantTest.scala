class SockMerchantTest extends org.scalatest.FunSuite {
  test("SockMerchant.sockMerchant") {
    assert(SockMerchant.sockMerchant(9, Array(10, 20, 20, 10, 10, 30, 50, 10, 20)) === 3)
  }
}