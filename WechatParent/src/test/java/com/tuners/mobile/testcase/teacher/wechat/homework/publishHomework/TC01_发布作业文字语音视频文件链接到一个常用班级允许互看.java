package com.tuners.mobile.testcase.teacher.wechat.homework.publishHomework;
import com.tuners.mobile.BasePages.Teacher.ConfigureClassesPage;
import com.tuners.mobile.BasePages.Teacher.HomePage;
import com.tuners.mobile.BasePages.Teacher.HomeWorkDetailsPage;
import com.tuners.mobile.BasePages.Teacher.HomeworkManagementPage;
import com.tuners.mobile.BasePages.Teacher.PublishHomeWorkPage;
import com.tuners.mobile.BasePages.WechatNativePage;
import com.tuners.mobile.util.Bases.WechatBase;
import com.tuners.mobile.testdata.CommonTestData;
import com.tuners.mobile.util.Functions.FunctionUtil;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;


/**
 * @Author Shaine
 * @Description
 * @Date 2020/6/16 17:57
 **/

/**
 * //微信教师端：发布作业文字语音视频文件链接到一个常用班级允许互看
 *
 */
public class TC01_发布作业文字语音视频文件链接到一个常用班级允许互看 extends WechatBase {
    public int voicecount=5;
    public String homeworkSubject="语文";
    public String homeworkTitle;
    public String homeworkContent;
    public LinkedHashMap<String,Object> homeworkInfo;
    public List<String> listOfUploaedImageAndVideoSrcs;
    public LinkedHashMap<String,String> hashMapOfLink;
    public String[] desiredImageNameArray={"1.jpg","2.jpg"};
    public String[] desiredVideoNameArray={"1.mp4","2.mp4"};
    public String[] desiredFileNameArray={"1.xlsx","2.docx","3.pptx","4.pdf"};
    public String[] desiredClasses={"五年级(1)班"};


    @Test
    public LinkedHashMap<String,Object> verifyTC01_TeacherPublishHomework() throws Exception {
        WechatNativePage wechatHomePage=new WechatNativePage(androidDriver);
        HomeworkManagementPage homeworkManagementPage=new HomeworkManagementPage(androidDriver);
        HomePage homePage=new HomePage(androidDriver);
        PublishHomeWorkPage publishHomeWorkPage=new PublishHomeWorkPage(androidDriver);
        HomeWorkDetailsPage homeWorkDetailsPage=new HomeWorkDetailsPage(androidDriver);
        ConfigureClassesPage configureClassesPage=new ConfigureClassesPage(androidDriver);


        //pre-condition
        //进入公众号
        wechatHomePage.searchAndEnterForPublicAccountForTeacher(ENVFLAG);


        //点击更多作业进入作业管理
        homePage.enterHomeWorkManagementPage();
        homeworkManagementPage.verifyDisplayed();
        //点击发布作业按钮到发布作业界面
        homeworkManagementPage.clickPublishButton();

        //step1:选择科目

        publishHomeWorkPage.selectDesiredSubject(homeworkSubject);

        //step2:填写作业标题
        homeworkTitle="自动化"+homeworkSubject+System.currentTimeMillis();
        publishHomeWorkPage.fillInHomeworkTitle(homeworkTitle);


        //step3:输入作业内容
      homeworkContent=homeworkTitle+"</textarea><textarea><h1>啊哦</h1>123!@#$%^^</textarea>!@#$%^&()))AKSHKAHSkajsk$%&就是看到凯撒的思考阿夜色如墨，月华如练，流泻千里。月光下，树木枝叶婆娑，手电的灯光在草地上和空中交叉射出一个又一个扇面，光线交织成网。这夏日捕蝉的盛景，始终萦绕在我的心头，挥之不去，念之不忘。我对蝉的印象并不算好，一方面是因为它在故事书上那好逸恶劳的形象，另一方面是因为它那似乎从不间断停歇的聒噪的鸣叫声。初次认识蝉，是在彩绘的故事书上，讲的是蝉在整个夏日都只是终日唱歌，冬天时不得不到它的邻居蚂蚁那儿借粮食充饥的故事。似乎，我在刚会说话后就磕磕绊绊地背诵这个耳熟能详的故事，因此在心底对这“自私自利”的蝉总有些鄙夷，很难对它喜欢起来。在古老的寓言故事中，蝉是好逸恶劳，自私自利的，而与它相对的蚂蚁总是勤劳勇敢，任劳任怨。二者的形象一直根深蒂固在人们的心中。然而，在读了法布尔的《昆虫记》后，我才知道原来这是一个弥天大错！事实上蝉的生命早结束在短暂的夏日，并且蝉只需要一点树木的汁液就可以生存。那些说蝉向蚂蚁乞求食物的故事，根本就是无稽之谈，人们对蝉误解太深了！真相是：蝉在树枝上凿出的甘泉被蚂蚁这帮无赖强盗抢走。蝉是真正温厚老实的受害者，而蚂蚁才是贪婪功利的掠夺者。时至今日，蝉仍被迫背负着这虚假的丑恶形象，不少人仍蒙骗在这一错误中，令人叹惋。虽然我整个夏日都被蝉鸣乱的心烦意燥，但在冤枉它后心中些许愧疚的影响下，似乎那震耳欲聋的蝉鸣也稍微不那么讨厌了。更何况，还有夏夜捕蝉的快乐以及鲜美的蝉肉呢？说实话，比起享受蝉肉的美妙滋味，我还是更喜欢捕蝉的过程。过程，往往是比结果更具意义的。当天色擦黑，且看吧，男女老少都已“全副武装”，准备出门捕蝉。果然，捕蝉真是一个老少皆宜的活动。是夜，天空如墨浸染般，深邃在那茫无际涯的纯粹的黑色中，一轮明月静静的悬挂着，半掩在轻薄的云层下，神秘幽静。皎月旁的夜空中嵌着几颗忽隐忽现的星辰。疏淡的星辉在明月的映衬下，黯然得似有似无。我右手握着手电，左手提着装蝉的塑料瓶，开始寻找今夜的主角：蝉。我紧握着手电，放慢脚步，只听见风吹过树叶的轻响，小虫呓语呢喃的声音，人踩在枯枝老叶上发出的沙沙声，交织在一起，像一曲悠悠的小令。我仔细聆听着，终于如愿以偿的在各种声音中听到那极轻极细的沙沙声――那时蝉爬过草丛时发出的声音。我循着声音找去，定睛一看，在一丛杂草上，一只蝉正艰难地爬着，我轻轻将这个孱弱的小生命抓起，再小心翼翼地将它放入塑料瓶中，心中不禁充满自豪感。我握着手电，顺着成排的树木向前走去，在这无边无际的黑暗中，只能看到星星点点的光芒，我的心头不禁泛起原始的孤寂与恐惧。好在手电那耀眼的灯光，始终为我照亮，指明着前进的方向，我才能继续投入进捕蝉工作中。借着灯光，我仔细的扫过每一寸土地与树干，在这漫长时光中的唯一安慰就是那突然出现在树干上的蝉。当我惊喜地把它抓在手中，见它并不配合地挣扎时；当我轻松愉悦地把蝉投入已接近半满的瓶中时，心头不禁涌上一阵阵的喜悦与自豪。\n" +
                "当我满载而归的回家时，我才可以毫无顾忌地把它笼在手中，静静地仔细观察这个小生命。当它还没有蜕壳时，许是刚从土里钻出来的缘故，身体表面挂着些许湿润的土屑。它有着黑亮的外壳，强劲有力的腿，可以看到，上面长着一排小小的细刺，每次抓它时都会被这尖刺给狠狠地刺上一下，那滋味可不好受。我把它放在纱窗上，许久后，只见它背上裂开一个小缝，慢慢的缝隙越来越大，然后它整个身体都拼命的往缝在挤。渐渐地头钻了出来";
        publishHomeWorkPage.fillInHomeworkContent(homeworkContent);


        //step4:添加语音、图片、视频、附件、链接

        //语音
     publishHomeWorkPage.addVoice(5,voicecount);

        //图片
       publishHomeWorkPage.uploadImageOrVideo(CommonTestData.PICTURESOURCEFROMPC,desiredImageNameArray,0);


        //视频
       publishHomeWorkPage.uploadImageOrVideo(CommonTestData.VIDEOSOURCEFROMPC,desiredVideoNameArray,1);


       //收集视频与图片的存放地址
      listOfUploaedImageAndVideoSrcs=FunctionUtil.getAttributesFromWebElementList("data-src",publishHomeWorkPage.listOfUploadedBlockes);



        //文件
    //publishHomeWorkPage.uploadAttachmentNew(Arrays.asList(desiredFileNameArray));



       //链接
       hashMapOfLink=new LinkedHashMap<>();
        hashMapOfLink.put("链接1","链接1内容");
        hashMapOfLink.put("链接2","链接2内容");
        hashMapOfLink.put("链接3","链接3内容");
        hashMapOfLink.put("链接4","链接4内容");
        hashMapOfLink.put("链接5","链接5内容");

        publishHomeWorkPage.addLink(hashMapOfLink);


        //选择发布对象
        Set<String> gradeList=configureClassesPage.collectGradesToSet(desiredClasses);
        List<String> gdList=new ArrayList(gradeList);
        publishHomeWorkPage.selectPublishObjectToClass(desiredClasses);



        //选择是否需要在线提交
        publishHomeWorkPage.selectSubmitOnlineOrOffline(true);



        //选择截止日期
        String dateStr=FunctionUtil.getAfterDayDate("7", "M月d日 HH:mm");

       //选择一周后的日期
    String date=dateStr.split(" ")[0];
    System.out.println("date is:"+date);
    String homeworkEndTime=publishHomeWorkPage.selectEndDate(date);


       //是否允许学生互看作业
        publishHomeWorkPage.selectAllowViewHomeWork(true);

        //布置作业
        Thread.sleep(2000);
        publishHomeWorkPage.btnOfArrangeHomework.click();
        homeworkManagementPage.verifyDisplayed();


        homeworkInfo=new LinkedHashMap<String,Object>();
        homeworkInfo.put("subject",homeworkSubject);
        homeworkInfo.put("title",homeworkTitle);
        homeworkInfo.put("content",homeworkContent);
        homeworkInfo.put("endTime",homeworkEndTime);
        homeworkInfo.put("voiceCount",voicecount);
       homeworkInfo.put("imageAndVideoSize",desiredImageNameArray.length+desiredVideoNameArray.length);
       homeworkInfo.put("imageAndVideoSrc",listOfUploaedImageAndVideoSrcs);
       // homeworkInfo.put("attachments",Arrays.asList(desiredFileNameArray));
        homeworkInfo.put("linkMap",hashMapOfLink);
        homeworkInfo.put("publishObject",Arrays.asList(desiredClasses));
        homeworkInfo.put("publishToGrade",gdList);



        //布置完作业到作业管理验证基础的个性信息
        int i = FunctionUtil.getIndexOfDesiredElementInList(homeworkManagementPage.listOfPublishedHomeworkList,homeworkTitle);
        System.out.println("i is:"+ i);

       homeworkManagementPage.verifyDetailedPublishedHomeworkContent(homeworkInfo,true);


       //点击作业进入作业详情页面
        homeworkManagementPage.listOfPublishedHomeworkList.get(i).click();
        homeWorkDetailsPage.verifyDisplayed();
        homeWorkDetailsPage.verifyHomeWorkDetailsInDetailsPage(homeworkInfo,true);


       return homeworkInfo;

    }



}
