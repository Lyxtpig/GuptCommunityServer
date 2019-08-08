package net.gupt.community;

import net.gupt.community.entity.Student;
import net.gupt.community.service.StudentService;
import net.gupt.community.util.AesUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GuptCommunityApplicationTests {

    @Autowired
    private StudentService studentService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void TestRedis() {
        Jedis jedis = new Jedis("119.3.181.96", 6379);
        jedis.auth("guptcommunity");
        jedis.close();
    }

    @Test
    public void TestAes() {
        try {
            String openID = "o4RnF5Ps9ERD6e-qrSn41lgNTu1c";
            String unionID = "orKlqt7eCoLvCF3hZ08cmTdFolNc4";
            long time = System.currentTimeMillis();
            String uuid = UUID.randomUUID().toString();
            String enc = AesUtil.byteToHexString(AesUtil.encrypt(openID + "|" + unionID + "|" + time + "|" + uuid));
            System.out.println("新的Token：" + enc);
            String dec = new String(AesUtil.decrypt(enc), StandardCharsets.UTF_8);
            String timeResult = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .format(new Date(Long.parseLong(dec.split("\\|")[2])));
            System.out.println("token时间结果：" + timeResult);
            System.out.println("解密：" + dec);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void decryptToken() {
        try {
            String token = "39a11a3666b41625c9e63f73728a12126f56a447eafac470dffee1ad7bcebee1ce41f89264bb2ca6a6a202fd9a636b37c80a22938d1a2570d7bf66b9f81eca068018e88167a137a78b12b12365ee7b0e40c9ae428771320f56a5ee5f87a6b6cf794b61d3202c1b25e7d4b5a86567569b";
            String dec = new String(AesUtil.decrypt(token), StandardCharsets.UTF_8);
            System.out.println("解密：" + dec);
            String openID = dec.split("\\|")[0];
            System.out.println(openID);
            System.out.println(studentService.loginByOpenId(openID));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTokenTime() {
        long time = System.currentTimeMillis();
        String timeStartResult = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date(time));
        System.out.println("token时间结果：" + timeStartResult);
        long endTime = System.currentTimeMillis() + 1000 * 60 * 60 * 2;
        String timeEndResult = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date(endTime));
        System.out.println("token时间结果：" + timeEndResult);
    }

}