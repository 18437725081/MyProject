package nyist.jdbc.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * ��Ԫ����
 * @author Administrator
 * ֻ��@Testע��ķ������Խ��е�Ԫ���ԣ�
 * @Before @After ע��ķ����Լ����������У����еĽ����@Test�������֮ǰ ��֮��
 */
public class TestJunit {
	
	@Test
	public void testJunit() {
		System.out.println("hello world");
	}
	
	/**
	 * �����Լ����ܹ����У�������@Test����֮ǰ
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
