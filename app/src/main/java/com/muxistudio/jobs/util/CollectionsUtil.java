package com.muxistudio.jobs.util;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ybao (ybaovv@gmail.com)
 * Date: 17/3/14
 */

public class CollectionsUtil {

    public static List<Integer> getCollectionList() {
        String collectionStr = PreferenceUtil.getString(PreferenceUtil.COLLECTION_IDS);
        List<Integer> collectionList = new ArrayList<>();
        String[] strs = collectionStr.split(",");
        for (String s : strs) {
            collectionList.add(Integer.parseInt(s));
        }
        return collectionList;
    }

    public static void putCollectionList(List<Integer> collectionList) {
        PreferenceUtil.putString(PreferenceUtil.COLLECTION_IDS,
                TextUtils.join(",", collectionList));
    }

    public static void addCollectionId(String id) {
        String s = PreferenceUtil.getString(PreferenceUtil.COLLECTION_IDS);
        if (s.length() == 0) {
            PreferenceUtil.putString(PreferenceUtil.COLLECTION_IDS, id);
        } else {
            PreferenceUtil.putString(PreferenceUtil.COLLECTION_IDS, s + "," + id);
        }
    }

    public static void removeCollectionId(String id) {
        List<Integer> collectionList = getCollectionList();
        if (collectionList.contains(Integer.parseInt(id))) {
            collectionList.remove((Object) Integer.parseInt(id));
            putCollectionList(collectionList);
        }
    }
}
