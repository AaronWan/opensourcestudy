package basic;

class DerivedSpec extends BaseSpec {
  def y = { println 'derived field initializer' }()

  def setupSpec() { println 'derived setupSpec()' }
  def cleanupSpec() { println 'derived cleanupSpec()' }

  def setup() { println 'derived setup()' }
  def cleanup() { println 'derived cleanup()' }

  def baseSpecMethod() {
    given: println '-'*3+'base spec method'
    when: println '-'*3+'base spec method'
    then: true
  }

  def derivedSpecMethod() {
    setup:
      println '_'*10+'derived spec method'+a
    when:
      println '_'*10+'given'+a
    then:
    true
    where:
    a||b
    1||1
    2||2
  }
}
