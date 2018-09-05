package nyist.jedis;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import nyist.e3.utils.redis.JedisClient;
import nyist.e3.utils.redis.JedisClientCluster;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

/**
 * 测试使用redis
 * 
 * @author Administrator
 *
 */
public class TestJedis {
	/**
	 * 测试单机版的redis 1.创建jedis对象，需要将ip地址和端口号作为参数，传递 2.使用创建的jedis对象操作redis的数据类型
	 * 3，关闭连接
	 */
	@Test
	public void testRedisOne() {
		// 创建jedis对象
		Jedis jedis = new Jedis("192.168.25.133", 6379);
		// 操作string数据类型
		jedis.set("username", "wangshuai_helloworld");
		// 根据key取出对应的value值
		String value = jedis.get("username");
		// 值输出
		System.out.println(value);
		// 关闭连接
		jedis.close();
	}

	/**
	 * 单机版的每次都要创建jedis对象，用完之后还需要关闭对象，比较耗费资源，因此使用jedis连接池
	 */
	@Test
	public void testRedisPool() {
		// 创建连接池
		JedisPool pool = new JedisPool("192.168.25.133", 6379);
		// 获得连接对象
		Jedis jedis = pool.getResource();
		// 操作hash类型
		jedis.hset("student", "name", "wangshuai");
		System.out.println(jedis.hget("student", "name"));
		Map<String, String> hash = new HashMap<>();
		hash.put("name", "tom");
		hash.put("age", "23");
		hash.put("address", "长江路");
		jedis.hmset("student", hash);
		Map<String, String> all = jedis.hgetAll("student");
		Set<Entry<String, String>> entrySet = all.entrySet();
		for (Entry<String, String> entry : entrySet) {
			String key = entry.getKey();
			String value = entry.getValue();
			System.out.println(key + ":" + value);
		}
		// 操作redis数据类型
		String value = jedis.get("username");
		// 返回结果
		System.out.println(value);
		// 关闭连接,连接池回收资源
		jedis.close();
		// 关闭连接池
		pool.close();
	}

	/**
	 * 连接redis集群版 1.创建集合用来存放每一个jiedis节点的ip和端口号 2.操作redis的数据类型即可 3.关闭连接
	 */
	@Test
	public void testRedis_cluster() {
		// 创建set集合
		Set<HostAndPort> nodes = new HashSet<>();
		// 将每一个节点的ip地址和端口号添加到集合中
		nodes.add(new HostAndPort("192.168.25.133", 7001));
		nodes.add(new HostAndPort("192.168.25.133", 7002));
		nodes.add(new HostAndPort("192.168.25.133", 7003));
		nodes.add(new HostAndPort("192.168.25.133", 7004));
		nodes.add(new HostAndPort("192.168.25.133", 7005));
		nodes.add(new HostAndPort("192.168.25.133", 7006));
		// 创建jedisCluster对象，需要将创建的redis集合列表作为参数传递
		JedisCluster jedisCluster = new JedisCluster(nodes);
		// 通过集群对象操作redis
		jedisCluster.set("address", "南阳市宛城区");
		String value = jedisCluster.get("address");
		// 打印结果
		System.out.println(value);
		// 系统关闭前，关闭jedisCluster对象
		jedisCluster.close();
	}

	/**
	 * 对jedis封装的代码进行测试
	 */
	@Test
	public void testUtils() {
		// 1.加载spring配置容器，初始化spring容器
		ApplicationContext content = new ClassPathXmlApplicationContext(
				"classpath:spring/applicationContext-redis.xml");
		JedisClient jedisClientCluster = content.getBean(JedisClientCluster.class);
		jedisClientCluster.set("username", "孙擦");
		String value = jedisClientCluster.get("username");
		System.out.println(value);

		
	}
	
	/*@Test
	public void testPool() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");
		JedisClientPool jedisClientPool = (JedisClientPool) context.getBean("jedisClientPool");
		jedisClientPool.set("school", "nyist");
		String value = jedisClientPool.get("school");
		System.out.println(value);
	}*/
	
	
}
