package nyist.jdbc.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * 单元测试
 * @author Administrator
 * 只用@Test注解的方法可以进行单元测试，
 * @Before @After 注解的方法自己不可以运行，运行的结果在@Test方法结果之前 。之后
 */
public class TestJunit {
	
	@Test
	public void testJunit() {
		System.out.println("hello world");
	}
	
	/**
	 * 方法自己不能够运行，运行在@Test方法之前
	 */
	@Before
	public void testbefore() {
		System.out.println("before");
	}
	
	@After
	public void testAfter() {
		System.out.println("After");
	}
}
