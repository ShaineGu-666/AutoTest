package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.updateUserInfoCase;
import com.course.utils.databaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.course.model.User;

import java.io.IOException;

public class updateUserInfoTest {
    @Test(dependsOnGroups = "loginTrue", description = " 更新uid为3的用户信息")
    public void updateUserInfo() throws IOException, InterruptedException {
        SqlSession sqlSession = databaseUtil.getSqlSession();
        updateUserInfoCase updateUserInfoCase = sqlSession.selectOne("updateUserInfoCase", 1);
        System.out.println(updateUserInfoCase.toString());
        System.out.println(TestConfig.updateUserInfoUrl);

        int result = getResult(updateUserInfoCase);
        System.out.println("~~~~~result:" + result);
        Thread.sleep(3000);

        User u = sqlSession.selectOne(updateUserInfoCase.getExpected(), updateUserInfoCase);
        System.out.println("~~~~~:" + u.toString());
        Assert.assertNotNull(u);
        Assert.assertNotNull(result);

    }

    private int getResult(updateUserInfoCase updateUserInfoCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.updateUserInfoUrl);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("uid", updateUserInfoCase.getUid());
        jsonObject.put("sex", updateUserInfoCase.getSex());
        jsonObject.put("age", updateUserInfoCase.getAge());
        jsonObject.put("permission", updateUserInfoCase.getPermission());
        jsonObject.put("isDelete", updateUserInfoCase.getIsDelete());
        jsonObject.put("username", updateUserInfoCase.getUsername());
        jsonObject.put("sex", updateUserInfoCase.getSex());
        jsonObject.put("age", updateUserInfoCase.getAge());
        jsonObject.put("permission", updateUserInfoCase.getPermission());
        jsonObject.put("isDelete", updateUserInfoCase.getIsDelete());
        StringEntity entity = new StringEntity(jsonObject.toString(), "utf-8");
        post.setHeader("Content-Type", "application/json");
        post.setEntity(entity);
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        return Integer.parseInt(EntityUtils.toString(response.getEntity(), "utf-8"));
    }


    @Test(dependsOnGroups = "loginTrue", description = " 删除uid为4的用户信息")
    public void deleteUserInfo() throws IOException, InterruptedException {
        SqlSession sqlSession = databaseUtil.getSqlSession();
        updateUserInfoCase updateUserInfoCase = sqlSession.selectOne("updateUserInfoCase", 2);
        System.out.println(updateUserInfoCase.toString());
        System.out.println(TestConfig.updateUserInfoUrl);
        int result = getResult(updateUserInfoCase);
        Thread.sleep(3000);
        User user = sqlSession.selectOne(updateUserInfoCase.getExpected(), updateUserInfoCase);
        System.out.println("~~~~~:" + user.toString());
        Assert.assertNotNull(result);
        Assert.assertNotNull(user);

    }
}
