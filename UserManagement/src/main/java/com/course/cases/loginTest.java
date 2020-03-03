package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.model.loginCase;
import com.course.utils.config;
import com.course.utils.databaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class loginTest {
    @BeforeTest
    public void beforeTest() {
        TestConfig.loginUrl = config.getUrl(InterfaceName.LOGIN);
        TestConfig.updateUserInfoUrl = config.getUrl(InterfaceName.UPDATEUSERINFO);
        TestConfig.addUserUrl = config.getUrl(InterfaceName.ADDUSER);
        TestConfig.getUserInfoUrl = config.getUrl(InterfaceName.GETUSERINFO);
        TestConfig.getUserListUrl = config.getUrl(InterfaceName.GETUSERLIST);
        TestConfig.defaultHttpClient = new DefaultHttpClient();

    }

    @Test(groups = "loginTrue", description = "login successfully")
    public void loginTrue() throws IOException {
        SqlSession sqlSession = databaseUtil.getSqlSession();
        loginCase loginCase = sqlSession.selectOne("loginCase", 1);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);
        //"true"
        //actualResult
        String result = getResult(loginCase);
        //ExpectedResult
        Assert.assertEquals(loginCase.getExpected(), result);

    }

    private String getResult(loginCase loginCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.loginUrl);
        post.setHeader("Content-Type", "application/json");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", loginCase.getUsername());
        jsonObject.put("password", loginCase.getPassword());
        StringEntity entity = new StringEntity(jsonObject.toString(), "utf-8");
        post.setEntity(entity);
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        TestConfig.store = TestConfig.defaultHttpClient.getCookieStore();
        return EntityUtils.toString(response.getEntity(), "utf-8");

    }

    @Test(groups = "loginFalse", description = "login unSuccessfully")
    public void loginFalse() throws IOException {
        SqlSession sqlSession = databaseUtil.getSqlSession();
        loginCase loginCase = sqlSession.selectOne("loginCase", 2);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);
        //"false"
        //actualResult
        String result = getResult(loginCase);
        //ExpectedResult
        Assert.assertEquals(loginCase.getExpected(), result);
    }
}
