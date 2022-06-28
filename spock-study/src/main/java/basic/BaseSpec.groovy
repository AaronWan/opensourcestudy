package basic


import spock.lang.Specification

abstract class BaseSpec extends Specification {
  def x = { println 'base field initializer' }()
/**
 * 开始时执行一次
 * @return
 */
  def setupSpec() { println 'base setupSpec()' }
  /**
   * 结束时执行一次
   * @return
   */
  def cleanupSpec() { println 'base cleanupSpec()' }
/**
 * 每次都会执行
 * @return
 */
  def setup() { println 'base setup()' }
  /**
   * 每次都会执行
   * @return
   */
  def cleanup() { println 'base cleanup()' }

}

