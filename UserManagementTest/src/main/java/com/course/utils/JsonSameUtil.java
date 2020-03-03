package com.course.utils;

import com.google.gson.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class JsonSameUtil {

    private static final Gson gson = new Gson();
    private static final JsonParser parser = new JsonParser();


    public static boolean same(Object a, Object b) {
        if (a == null) {
            return b == null;
        }
        //把对象转换成字符串
        return same(gson.toJson(a), gson.toJson(b));

    }

    //比较两个json字符串是否相等
    public static boolean same(String a, String b) {
        if (a == null) {
            return b == null;
        }
        if (a.equals(b)) {
            return true;
        }
        JsonElement aelement = parser.parse(a);
        JsonElement belement = parser.parse(b);

        if (gson.toJson(aelement).equals(gson.toJson(belement))) {
            return true;
        }

        return same(aelement, belement);


    }


    public static boolean same(JsonElement a, JsonElement b) {

        if (a.isJsonObject() && b.isJsonObject()) {
            return same((JsonObject) a, (JsonObject) b);
        } else if (a.isJsonArray() && b.isJsonArray()) {
            return same((JsonArray) a, (JsonArray) b);
        } else if (a.isJsonPrimitive() && b.isJsonPrimitive()) {
            return same((JsonPrimitive) a, (JsonPrimitive) b);
        } else if (a.isJsonNull() && b.isJsonNull()) {
            return same((JsonNull) a, (JsonNull) b);
        } else {
            return Boolean.FALSE;
        }


    }

    public static boolean same(JsonObject a, JsonObject b) {

        Set<String> aset = a.keySet();
        Set<String> bset = b.keySet();

        if (!aset.equals(bset)) {
            return false;
        }
        for (String akey : aset) {
            if (!same(a.get(akey), b.get(akey))) {
                return false;
            }
        }

        return true;

    }

    private static boolean same(JsonArray a, JsonArray b) {

        if (a.size() != b.size()) {
            return false;
        }

        List<JsonElement> alist = toSortedList(a);
        List<JsonElement> blist = toSortedList(b);
        for (int i = 0; i < alist.size(); i++) {

            if (!same(alist.get(i), blist.get(i))) {
                return false;
            }
        }

        return true;

    }

    private static boolean same(JsonPrimitive a, JsonPrimitive b) {
        return a.equals(b);

    }


    private static boolean same(JsonNull a, JsonNull b) {
        return true;

    }

    private static List<JsonElement> toSortedList(JsonArray a) {

        List<JsonElement> aList = new ArrayList<JsonElement>();
        a.forEach(aList::add);
        aList.sort(Comparator.comparing(gson::toJson));
        return aList;
    }


}
