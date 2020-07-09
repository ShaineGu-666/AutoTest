package com.tuners.mobile.util.Functions;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Author Mason
 * @Description
 * @Date 2020/6/16 15:00
 **/
public class ReusableFunctions {

public static String getHomeworkTitleFromStorage(LinkedHashMap<String,Object> homeworkInfo){

    return String.valueOf(homeworkInfo.get("title"));
}

    public static String getHomeworkSubjectFromStorage(LinkedHashMap<String,Object> homeworkInfo){

        return  String.valueOf(homeworkInfo.get("subject"));
    }

    public static String getHomeworkContentFromStorage(LinkedHashMap<String,Object> homeworkInfo){

        return  String.valueOf(homeworkInfo.get("content"));
    }

    public static int getHomeworkVoiceTotalFromStorage(LinkedHashMap<String,Object> homeworkInfo){

        return  Integer.parseInt(String.valueOf(homeworkInfo.get("voiceCount")));
    }

    public static int getHomeworkImageAndVideoSizeFromStorage(LinkedHashMap<String,Object> homeworkInfo){

        return Integer.parseInt(String.valueOf(homeworkInfo.get("imageAndVideoSize")));
    }

    public static List<String> getHomeworkImageAndVideoSrcsFromStorage(LinkedHashMap<String,Object> homeworkInfo){

        return  FunctionUtil.castList(homeworkInfo.get("imageAndVideoSrc"), String.class);
    }


    public static String getHomeworkEndTimeFromStorage(LinkedHashMap<String,Object> homeworkInfo){

        return  String.valueOf(homeworkInfo.get("endTime"));
    }

    public static List<String> getHomeworkAttachmentsFromStorage(LinkedHashMap<String,Object> homeworkInfo){

        return FunctionUtil.castList(homeworkInfo.get("attachments"), String.class);
    }


    public static  LinkedHashMap<String,String> getHomeworkLinkMapFromStorage(LinkedHashMap<String,Object> homeworkInfo){

        return (LinkedHashMap<String, String>) homeworkInfo.get("linkMap");
    }

//发布对象到班级
    public static  List<String> getHomeworkPublishedObjectFromStorage(LinkedHashMap<String,Object> homeworkInfo){

        return FunctionUtil.castList(homeworkInfo.get("publishObject"), String.class);
    }


    public static  List<String> getHomeworkPublishedGradeFromStorage(LinkedHashMap<String,Object> homeworkInfo){

        return FunctionUtil.castList(homeworkInfo.get("publishToGrade"), String.class);
    }


    public static  List<String> getHomeworkPublishedStudentsFromStorage(LinkedHashMap<String,Object> homeworkInfo){

        return  FunctionUtil.castList(homeworkInfo.get("publishToStudentsList"), String.class);
    }






}
