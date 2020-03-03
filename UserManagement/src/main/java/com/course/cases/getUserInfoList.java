package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.User;
import com.course.model.getUserInfoCase;
import com.course.model.getUserListCase;
import com.course.utils.JsonSameUtil;
import com.course.utils.databaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class getUserInfoList {
    @Test(dependsOnGroups = "loginTrue", description = "获取uid为3的用户信息")
    public void getUserInfo() throws IOException {

        SqlSession sqlSession = databaseUtil.getSqlSession();
        getUserInfoCase getUserInfoCase = sqlSession.selectOne("getUserInfoCase", 1);
        System.out.println(getUserInfoCase.toString());
        System.out.println(TestConfig.getUserInfoUrl);
        //actual
        JSONArray result = getResult(getUserInfoCase);

        //getUserInfoCase.getExpected()为要执行的sql语句的id
        User u = sqlSession.selectOne(getUserInfoCase.getExpected(), getUserInfoCase);
        List<User> list = new ArrayList<User>();
        list.add(u);
        JSONArray usersArray = new JSONArray(list);
        //expected
        Assert.assertTrue(JsonSameUtil.same(usersArray, result));


    }

    private JSONArray getResult(getUserInfoCase getUserInfoCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.getUserInfoUrl);
        post.setHeader("Content-Type", "application/json");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("uid", getUserInfoCase.getUid());
        StringEntity entity = new StringEntity(jsonObject.toString(), "utf-8");
        post.setEntity(entity);
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        return new JSONArray(EntityUtils.toString(response.getEntity(), "utf-8"));

    }

    private JSONArray getResult(getUserListCase getUserListCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.getUserListUrl);
        post.setHeader("Content-Type", "application/json");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sex", getUserListCase.getSex());
        StringEntity entity = new StringEntity(jsonObject.toString(), "utf-8");
        post.setEntity(entity);
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);

        //
        return new JSONArray(EntityUtils.toString(response.getEntity(), "utf-8"));


    }


    @Test(dependsOnGroups = "loginTrue", description = "获取性别为男的用户列表")
    public void getUserList() throws IOException {

        SqlSession sqlSession = databaseUtil.getSqlSession();
        getUserListCase getUserListCase = sqlSession.selectOne("getUserListCase", 1);
        System.out.println(getUserListCase.toString());
        System.out.println(TestConfig.getUserListUrl);
        //actual
        JSONArray result = getResult(getUserListCase);

        //getUserInfoCase.getExpected()为要执行的sql语句的id
        List<User> users = sqlSession.selectList(getUserListCase.getExpected(), getUserListCase);

        JSONArray usersArray = new JSONArray(users);

        //expected
        Assert.assertTrue(JsonSameUtil.same(usersArray, result));
    }

}
