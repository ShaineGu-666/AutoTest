package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.addUserCase;
import com.course.utils.databaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class addUserTest {


    @Test(dependsOnGroups = "loginTrue", description = "添加用户接口测试")
    public void addUser() throws IOException {
        SqlSession sqlSession = databaseUtil.getSqlSession();
        addUserCase addUserCase = sqlSession.selectOne("addUserCase", 1);
        System.out.println(addUserCase.toString());
        System.out.println(TestConfig.addUserUrl);
        System.out.println("addUserCase.getExpected():" + addUserCase.getExpected());
        int result = getResult(addUserCase);

        Assert.assertEquals(addUserCase.getExpected(), result);

    }

    private int getResult(addUserCase addUserCase) throws IOException {

        HttpPost post = new HttpPost(TestConfig.addUserUrl);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", addUserCase.getUsername());
        jsonObject.put("password", addUserCase.getPassword());
        jsonObject.put("name", addUserCase.getName());
        jsonObject.put("sex", addUserCase.getSex());
        jsonObject.put("age", addUserCase.getAge());
        jsonObject.put("permission", addUserCase.getPermission());
        jsonObject.put("isDelete", addUserCase.getIsDelete());
        StringEntity entity = new StringEntity(jsonObject.toString(), "utf-8");
        post.setHeader("Content-Type", "application/json");
        post.setEntity(entity);
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        return Integer.parseInt(EntityUtils.toString(response.getEntity(), "utf-8"));


    }
}
