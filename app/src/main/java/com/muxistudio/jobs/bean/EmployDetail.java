package com.muxistudio.jobs.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ybao on 16/11/28.
 */

public class EmployDetail {

    /**
     * status : success
     * data : {"id":34518,"title":"纳杰人才千企万岗万人才市特大型招聘会","shorttitle":"纳杰人才千企万岗万人才市特大型招聘会",
     * "holdtime":"2016-10-22","detailtime":"09:15-13:00","venue_id":20,"venueName":"武汉纳杰人才广场",
     * "venueAddress":"武汉市武昌区武珞路519号丁字桥纳杰人才广场（湖北省农业厅旁）","is_official":1,"totalClicks":1324,
     * "isSaved":false,"content":"<p>&nbsp;
     * <\/p><p>大会主题：<\/p><p><span><b>纳杰人才第117届\u201c千企万岗、万人才市\u201d特大型综合招聘会<\/b><\/span><\/p><p
     * ><span><b>2016届湖北高校毕业生（含研究生）大型供需见面会<\/b><\/span><\/p><p><span><b>精英行业\u2014汽车专场招聘会<\/b
     * ><\/span><\/p><p><span><b>精英行业\u2014零售、贸易、商超专场招聘会<\/b><\/span><\/p><p>&nbsp;
     * <\/p><p>大会时间：<b>2016年10月22日（周六）9:15\u201413:00<\/b><\/p><p>大会地址：武汉纳杰人才市场（<b
     * >武昌武珞路丁字桥，省农业厅旁）<\/b><\/p><p>大会规模：预留1500个展位，提供近5万个职位<\/p><p>&nbsp;<\/p><p>&nbsp;
     * <\/p><p>各位求职者及用人单位：<\/p><p>十月金秋，\u201c金九银十\u201d
     * 求职招聘高峰全面来袭，为积极贯彻落实党中央国务院有关促进就业的政策精神，更好地服务于我省经济社会发展，积极促进我省人力资源合理流动与配置，以发挥我省作为\u201c人才之都\u201d
     * 的优势，武汉纳杰人才市场每周二三五六定期举办大型综合招聘会，每周六举办\u201c千企万岗\u201d特大型招聘会、应届毕业生供需见面会及热门行业专场招聘会。<span><b
     * >特定于10月22日（周六）举办【纳杰人才第117届\u201c千企万岗、万人才市\u201d特大型综合招聘会】，届时将开设内场一二三楼及外场施洋洪山公园，预留1500
     * 个展位以供企业招聘使用。<\/b><\/span>盛情邀请社会各界求职人士前来参会。<\/p><p>&nbsp;
     * <\/p><p><br/><\/p><p><br/><\/p><p><span><b><span>10月22日（周六）参会企业名单已更新<\/span><\/b><\/span
     * ><\/p><p>查看请点击<span><b>http://images.333job
     * .com/specailPage/wuhan/wuhanzph/system/main1.html#c<\/b><\/span><\/p><p><span><b
     * >关注【纳杰人才】官方微信，点击\u201c求职→参会企业名单\u201d也可<\/b><\/span><\/p><p><br/><\/p><p>&nbsp;
     * <\/p><p><span>纳杰人才场馆介绍<\/span><\/p><p><span>武汉纳杰人才市场（以下简称纳杰人才）位于武昌武珞路519
     * 号（湖北省农业厅旁），实际使用面积达10000平方米以上，是可容纳千家企业进场招聘、3
     * 万求职者进场求职的专业招聘场地。纳杰人才毗邻武昌繁华商务区，东接武汉大学、华中师范大学等高校聚集区，是武汉地区设施先进、规模宏大、交通便利的固定招聘基地。纳杰人才招聘会每周参会企业累计达2500余家，名企云集，规模宏大，是江城规模最大、人气最旺、最具影响力和吸引力的人才市场。场馆内按不同行业或专业划分招聘时段，增强了招聘针对性，是单位展示企业形象、招聘觅才，求职者求职就业的理想场所。<\/span><\/p><p>&nbsp;<\/p><p>&nbsp;<\/p><p>联系方式<\/p><p>电话：027-86788855转招聘会<\/p><p>地址：武汉武昌区武珞路519号纳杰人才市场<\/p><p>乘车路线：乘坐地铁2号线到【宝通寺站D出口】前行200米即到/乘公交至【武珞路丁字桥站】下车即到。<\/p><p>纳杰人才网：www.333job.com<\/p><p>纳杰人才微信：纳杰人才（微信账号najierencai2013）<\/p><p>纳杰人才求职QQ群：群一277920809、群二277921922、群三27792200<\/p><p><br/><\/p>"}
     */

    public String status;
    public DataBean data;

    public static EmployDetail objectFromData(String str) {

        return new Gson().fromJson(str, EmployDetail.class);
    }

    public static EmployDetail objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), EmployDetail.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<EmployDetail> arrayEmployDetailFromData(String str) {

        Type listType = new TypeToken<ArrayList<EmployDetail>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<EmployDetail> arrayEmployDetailFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<EmployDetail>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();
    }

    public static class DataBean {
        /**
         * id : 34518
         * title : 纳杰人才千企万岗万人才市特大型招聘会
         * shorttitle : 纳杰人才千企万岗万人才市特大型招聘会
         * holdtime : 2016-10-22
         * detailtime : 09:15-13:00
         * venue_id : 20
         * venueName : 武汉纳杰人才广场
         * venueAddress : 武汉市武昌区武珞路519号丁字桥纳杰人才广场（湖北省农业厅旁）
         * is_official : 1
         * totalClicks : 1324
         * isSaved : false
         * content : <p>&nbsp;</p><p>大会主题：</p><p><span><b>纳杰人才第117届“千企万岗、万人才市”特大型综合招聘会</b></span></p
         * ><p><span><b>2016届湖北高校毕业生（含研究生）大型供需见面会</b></span></p><p><span><b>精英行业—汽车专场招聘会</b></span
         * ></p><p><span><b>精英行业—零售、贸易、商超专场招聘会</b></span></p><p>&nbsp;
         * </p><p>大会时间：<b>2016年10月22日（周六）9:15—13:00</b></p><p>大会地址：武汉纳杰人才市场（<b>武昌武珞路丁字桥，省农业厅旁）</b
         * ></p
         * ><p>大会规模：预留1500个展位，提供近5万个职位</p><p>&nbsp;</p><p>&nbsp;
         * </p><p>各位求职者及用人单位：</p><p
         * >十月金秋，“金九银十”求职招聘高峰全面来袭，为积极贯彻落实党中央国务院有关促进就业的政策精神，更好地服务于我省经济社会发展，积极促进我省人力资源合理流动与配置，以发挥我省作为“人才之都”的优势，武汉纳杰人才市场每周二三五六定期举办大型综合招聘会，每周六举办“千企万岗”特大型招聘会、应届毕业生供需见面会及热门行业专场招聘会。<span><b>特定于10月22日（周六）举办【纳杰人才第117届“千企万岗、万人才市”特大型综合招聘会】，届时将开设内场一二三楼及外场施洋洪山公园，预留1500个展位以供企业招聘使用。</b></span>盛情邀请社会各界求职人士前来参会。</p><p>&nbsp;</p><p><br/></p><p><br/></p><p><span><b><span>10月22日（周六）参会企业名单已更新</span></b></span></p><p>查看请点击<span><b>http://images.333job.com/specailPage/wuhan/wuhanzph/system/main1.html#c</b></span></p><p><span><b>关注【纳杰人才】官方微信，点击“求职→参会企业名单”也可</b></span></p><p><br/></p><p>&nbsp;</p><p><span>纳杰人才场馆介绍</span></p><p><span>武汉纳杰人才市场（以下简称纳杰人才）位于武昌武珞路519号（湖北省农业厅旁），实际使用面积达10000平方米以上，是可容纳千家企业进场招聘、3万求职者进场求职的专业招聘场地。纳杰人才毗邻武昌繁华商务区，东接武汉大学、华中师范大学等高校聚集区，是武汉地区设施先进、规模宏大、交通便利的固定招聘基地。纳杰人才招聘会每周参会企业累计达2500余家，名企云集，规模宏大，是江城规模最大、人气最旺、最具影响力和吸引力的人才市场。场馆内按不同行业或专业划分招聘时段，增强了招聘针对性，是单位展示企业形象、招聘觅才，求职者求职就业的理想场所。</span></p><p>&nbsp;</p><p>&nbsp;</p><p>联系方式</p><p>电话：027-86788855转招聘会</p><p>地址：武汉武昌区武珞路519号纳杰人才市场</p><p>乘车路线：乘坐地铁2号线到【宝通寺站D出口】前行200米即到/乘公交至【武珞路丁字桥站】下车即到。</p><p>纳杰人才网：www.333job.com</p><p>纳杰人才微信：纳杰人才（微信账号najierencai2013）</p><p>纳杰人才求职QQ群：群一277920809、群二277921922、群三27792200</p><p><br/></p>
         */

        public int id;
        public String title;
        public String shorttitle;
        public String holdtime;
        public String detailtime;
        public int venue_id;
        public String venueName;
        public String venueAddress;
        public int is_official;
        public int totalClicks;
        public boolean isSaved;
        public String content;

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }

        public static DataBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), DataBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static List<DataBean> arrayDataBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<DataBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public static List<DataBean> arrayDataBeanFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);
                Type listType = new TypeToken<ArrayList<DataBean>>() {
                }.getType();

                return new Gson().fromJson(jsonObject.getString(str), listType);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new ArrayList();
        }
    }
}

