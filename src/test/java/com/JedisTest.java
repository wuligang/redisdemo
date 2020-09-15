package com;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/**
 * @program: MyWebProject
 * @description:
 * @author: wlg
 * @create: 2020-09-14 09:38:00
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JedisTest {
    @Resource
    private JedisPool jedisPool;
    @Resource
    private JedisCluster jedisCluster;

    @Test
    public void test01() {
        //创建一个Jedis的连接
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.select(1);//设置数据库
        //执行redis命令
        jedis.set("mytest", "hello world, this is jedis client!");
        //从redis中取值
        String result = jedis.get("mytest");
        //打印结果
        System.out.println(result);
        //关闭连接
        jedis.close();

    }

    @Test
    public void test02() {
        JedisPool jedisPool = new JedisPool("localhost", 6379);
        Jedis jedis = jedisPool.getResource();
        String name = jedis.get("name");
        System.out.println(name);
        jedis.close();
        jedisPool.close();
    }

    @Test
    public void test03() {
        Jedis jedis = jedisPool.getResource();
        String name = jedis.get("name");
        System.out.println(name);
        jedis.close();
        jedisPool.close();
    }

    @Test
    public void test04() {
        jedisCluster.set("name", "zhangsan");
        String value = jedisCluster.get("name");
        System.out.println(value);
    }

}
