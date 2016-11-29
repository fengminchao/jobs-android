package com.muxistudio.jobs.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ybao on 16/11/28.
 */

public class CareerDetail {

  public String status;
  public DataBean data;

  public static CareerDetail objectFromData(String str) {

    return new Gson().fromJson(str, CareerDetail.class);
  }

  public static CareerDetail objectFromData(String str, String key) {

    try {
      JSONObject jsonObject = new JSONObject(str);

      return new Gson().fromJson(jsonObject.getString(str), CareerDetail.class);
    } catch (JSONException e) {
      e.printStackTrace();
    }

    return null;
  }

  public static List<CareerDetail> arrayCareerDetailFromData(String str) {

    Type listType = new TypeToken<ArrayList<CareerDetail>>() {
    }.getType();

    return new Gson().fromJson(str, listType);
  }

  public static List<CareerDetail> arrayCareerDetailFromData(String str, String key) {

    try {
      JSONObject jsonObject = new JSONObject(str);
      Type listType = new TypeToken<ArrayList<CareerDetail>>() {
      }.getType();

      return new Gson().fromJson(jsonObject.getString(str), listType);
    } catch (JSONException e) {
      e.printStackTrace();
    }

    return new ArrayList();
  }

  public static class DataBean {
    /**
     * id : 338606
     * company : 四川航空股份有限公司
     * title : 四川航空股份有限公司
     * holdtime : 2016-09-18 08:30:00
     * univ_id : 69
     * universityName : 中南民族大学
     * address : 11教学楼105招聘厅
     * logoUrl : http://cdn6.haitou.cc/university/69.png
     * applyUrl : http://m.haitou.cc/xz-41504
     * is_cancel : 0
     * is_official : 0
     * web : 中南民族大学招聘信息服务系统
     * totalClicks : 2740
     * content : <div> <div><span>单位信息</span></div> <table border="0"><tr><td>单位名称</td> <td colspan="3"> <a href="http://job.scuec.edu.cn/eweb/jygl/zpfw.so?entityId=SCFW_JYGL_DWXX&subsyscode=zpfw&modcode=jygl_scfwyrdw&type=viewDwxx&id=2D14oxDUEMVF7xPRm3RkhF"> 四川航空股份有限公司 </a> </td> </tr><tr><td>所在区域省市</td> <td> 西南地区  四川省  成都市  双流县 </td> <td>单位性质</td> <td> 国有企业    </td> </tr><tr><td>单位地址</td> <td>成都双流国际机场四川航空大厦</td> <td>邮编</td> <td></td> </tr><tr><td>点击数</td> <td colspan="3"> 691 </td> </tr></table></div> <div> <div><span>举办场地和时间</span></div> <table border="0"><tr><td> 转入学校  11教学楼105招聘厅  2016-09-18 08：30-11：30 </td> </tr></table></div> <div> <div><span>招聘信息</span></div> <table border="0"><tr><td>主题</td> <td> 飞行学员（公费）招聘 </td> <td>招聘截止日期</td> <td> 2016-09-23 </td> </tr><tr><td>应聘网址</td> <td> http://www.sichuanair.com:8888/Scal.PilotInvite/index.htm </td> <td>简历投递邮箱</td> <td> duyanlin@scal.com.cn </td> </tr><tr><td colspan="4">招聘说明：</td> </tr><tr><td colspan="4">    <p align="center"> <span><span><b><span></span></b></span></span> </p> <p align="center"> <span><span><b><span><span> </span><span> </span></span></b></span></span><span><span><b><span>四川航空股份有限公司</span></b></span></span><span><span><b><span>2017</span></b></span></span><span><span><b><span>年度飞行学员招聘简章</span></b></span></span><span><span><b><span></span></b></span></span> </p> <p align="left"> <span><span><span><span>  </span></span></span></span><span><span><span>四川航空股份有限公司成立于</span></span></span><span><span><span>2002</span></span></span><span><span><span>年</span></span></span><span><span><span>8</span></span></span><span><span><span>月</span></span></span><span><span><span>29</span></span></span><span><span><span>日，四川航空集团有限责任公司持有四川航空股份有限公司</span></span></span><span><span><span>40</span></span></span><span><span><span>％的股份，为第一大股东。其他股东分别为中国南方航空股份有限公司、中国东方航空股份有限公司、山东航空股份有限公司、成都银杏金阁投资有限公司。四川航空股份有限公司的前身是四川航空公司，该公司成立于</span></span></span><span><span><span>1986</span></span></span><span><span><span>年</span></span></span><span><span><span>9</span></span></span><span><span><span>月</span></span></span><span><span><span>19</span></span></span><span><span><span>日，</span></span></span><span><span><span>1988</span></span></span><span><span><span>年</span></span></span><span><span><span>7</span></span></span><span><span><span>月</span></span></span><span><span><span>14</span></span></span><span><span><span>日正式开航营运。</span></span></span><span><span><span> <span></span></span></span></span> </p> <p align="left"> <span><span><span><span>  </span></span></span></span><span><span><span>四川航空股份有限公司总部位于成都，在重庆、昆明设有分公司，在杭州、三亚、北京、西安、哈尔滨等地设有运行基地。现有全空中客车机队</span></span></span><span><span><span>108</span></span></span><span><span><span>架，到</span></span></span><span><span><span>2020</span></span></span><span><span><span>年机队规模将超过</span></span></span><span><span><span>180</span></span></span><span><span><span>架。现有航线超过</span></span></span><span><span><span>240</span></span></span><span><span><span>条，其中国际地区航线</span></span></span><span><span><span>30</span></span></span><span><span><span>余条。开航至今，川航保持良好安全记录，已实现安全飞行</span></span></span><span><span><span>27</span></span></span><span><span><span>年，持续盈利</span></span></span><span><span><span>19</span></span></span><span><span><span>年；年运输旅客超过</span></span></span><span><span><span>2000</span></span></span><span><span><span>万人次，航班正常率、旅客满意度均位居中国民航前列；曾获中国民航局颁发的安全飞行“二星奖”、首届中国质量奖提名奖等荣誉。</span></span></span><span><span><span> <span></span></span></span></span> </p> <p align="left"> <span><span><span><span>  </span></span></span></span><span><span><span>转型网络型航企，拓展国际市场。随着机队规模壮大，川航网络化转型也随之加速。在做强做实做优成、渝、昆三个主基地的基础上，升级北京、三亚、杭州、哈尔滨、西安为运行基地。“十二五”期间，川航开通了成都</span></span></span><span><span><span>-</span></span></span><span><span><span>沈阳</span></span></span><span><span><span>-</span></span></span><span><span><span>温哥华航线，涉足北美航线；开通成都</span></span></span><span><span><span>-</span></span></span><span><span><span>墨尔本、重庆</span></span></span><span><span><span>-</span></span></span><span><span><span>悉尼航线，打造西部赴澳旅游的最佳空中通道；仅</span></span></span><span><span><span>2015</span></span></span><span><span><span>年，又相继开通了加德满都、莫斯科、迪拜等</span></span></span><span><span><span>13</span></span></span><span><span><span>条国际地区航线。截至</span></span></span><span><span><span>2015</span></span></span><span><span><span>年底，川航执行国内、国际、地区航线超过</span></span></span><span><span><span>240</span></span></span><span><span><span>条。</span></span></span><span><span><span>2016</span></span></span><span><span><span>年</span></span></span><span><span><span>1</span></span></span><span><span><span>月，川航相继新开成都至东京、成都至大阪、成都至新加坡</span></span></span><span><span><span>3</span></span></span><span><span><span>条国际航线。“国际化”日渐成为川航企业品牌的新标签。</span></span></span><span><span><span> <span></span></span></span></span> </p> <p align="left"> <span><span><span><span>  </span></span></span></span><span><span><span>“十三五”实现“三个五”，打造西部第一。川航将落实创新、协调、开放、绿色、共享的发展理念，进一步走向规模化、国际化。按照企业发展“十三五”规划，到</span></span></span><span><span><span>2020</span></span></span><span><span><span>年底，川航机队规模将超过</span></span></span><span><span><span>180</span></span></span><span><span><span>架，在西部地区综合实力排名第一，并实现“三个五”的目标——即“飞行安全五星奖、</span></span></span><span><span><span>500</span></span></span><span><span><span>亿资产规模、</span></span></span><span><span><span>5</span></span></span><span><span><span>个分公司以上规模的基地”，以优异的营运水平、成熟的发展平台和卓越的品牌价值，成为一家独具特色优势的中型规模航企。</span></span></span><span><span><span> </span></span></span> </p> <p align="left"> <span><span><span> </span></span></span><span><span><b><span> </span></b></span></span><span><span><b><span>一、</span></b></span></span><span><span><b><span>招聘信息</span></b></span></span><span><span><b><span></span></b></span></span> </p> <p align="left"> <span><span><span><span>  </span></span></span></span><span><span><span>（一）招聘岗位：飞行学员</span></span></span><span><span><span></span></span></span> </p> <p align="left"> <span><span><span><span>  </span></span></span></span><span><span><span>（二）招聘对象：男性，</span></span></span><span><span><span>2016</span></span></span><span><span><span>年往届大学毕业生、</span></span></span><span><span><span>2017</span></span></span><span><span><span>年应届大学毕业生</span></span></span><span><span><span></span></span></span> </p> <p align="left"> <span><span><span><span>  </span></span></span></span><span><span><span>（三）应聘条件</span></span></span><span><span><span></span></span></span> </p> <p align="left"> <span><span><span><span>   </span><span> </span>1</span></span></span><span><span><span>、年龄：</span></span></span><span><span><span>2016</span></span></span><span><span><span>年往届大学毕业生（本科生</span></span></span><span><span><span>1992</span></span></span><span><span><span>年</span></span></span><span><span><span>1</span></span></span><span><span><span>月</span></span></span><span><span><span>1</span></span></span><span><span><span>日后出生，研究生</span></span></span><span><span><span>1990</span></span></span><span><span><span>年</span></span></span><span><span><span>1</span></span></span><span><span><span>月</span></span></span><span><span><span>1</span></span></span><span><span><span>日后出生）；</span></span></span><span><span><span>2017</span></span></span><span><span><span>年应届毕业学生（本科生</span></span></span><span><span><span>1993</span></span></span><span><span><span>年</span></span></span><span><span><span>1</span></span></span><span><span><span>月</span></span></span><span><span><span>1</span></span></span><span><span><span>日后出生，研究生</span></span></span><span><span><span>1991</span></span></span><span><span><span>年</span></span></span><span><span><span>1</span></span></span><span><span><span>月</span></span></span><span><span><span>1</span></span></span><span><span><span>日后出生）</span></span></span><span><span><span>；</span></span></span><span><span><span></span></span></span> </p> <p align="left"> <span><span><span><span>   </span><span> </span>2</span></span></span><span><span><span>、学历：国家统招公办全日制本科及以上（二本及以上院校），专业不限。不接收独立院校（原三本学历）学历学生；不接收成人高等教育（脱产、半脱产学习形式）、高等教育自学考试的本科学历；不接收函授学习形式的成人高等教育、电大开放教育、网络大学等形式的成人继续教育学历；不接收专升本教育学历；</span></span></span><span><span><span></span></span></span> </p> <p align="left"> <span><span><span><span>   </span><span> </span>3</span></span></span><span><span><span>、英语：毕业时需取得</span></span></span><span><span><span>CET4</span></span></span><span><span><span>或</span></span></span><span><span><span>CET6</span></span></span><span><span><span>合格证书（</span></span></span><span><span><span>425</span></span></span><span><span><span>分以上），<span>或取得雅思</span></span></span></span><span><span><span>A</span></span></span><span><span><span>类</span></span></span><span><span><span>5.0</span></span></span><span><span><span>以上，或新托福</span></span></span><span><span><span>75</span></span></span><span><span><span>分以上；具有良好的英文口头表达能力；</span></span></span><span><span><span></span></span></span> </p> <p align="left"> <span><span><span><span>  </span><span> </span><span> </span>4</span></span></span><span><span><span>、个人：性格乐观开朗、积极向上，热爱飞行事业并具有较强的团队协作意识及良好的沟通能力。</span></span></span><span><span><span></span></span></span> </p> <p align="left"> <span><span><span><span>   </span><span> </span>5</span></span></span><span><span><span>、体检和背景：身体符合《民用航空招收飞行学生体格检查鉴定规范》要求，政审符合《民用航空背景调查规定》相关要求。</span></span></span><span><span><span></span></span></span> </p> <p align="left"> <span><span><span><span>   </span>★</span></span></span><span><span><span>自荐标准：具有下列情况之一者，不适合学习飞行：</span></span></span><span><span><span><br /><span>   </span>1</span></span></span><span><span><span>、男性身高不足</span></span></span><span><span><span>170</span></span></span><span><span><span>厘米；</span></span></span><span><span><span><br /><span>   </span>2</span></span></span><span><span><span>、</span></span></span><span><span><span>BMI</span></span></span><span><span><span>（</span></span></span><span><span><b><span>身体质量指数</span></b></span></span><span><span><span>）</span></span></span><span><span><span>=</span></span></span><span><span><span>体重</span></span></span><span><span><span>KG/</span></span></span><span><span><span>身高</span></span></span><span><span><span>M</span></span></span><span><span><span>²，指数不应大于</span></span></span><span><span><span>24</span></span></span><span><span><span>或小于</span></span></span><span><span><span>18.5</span></span></span><span><span><span>；<span><br /></span></span></span></span><span><span><span><span>  </span><span> </span>3</span></span></span><span><span><span>、视力低于</span></span></span><span><span><span>0.3</span></span></span><span><span><span>（</span></span></span><span><span><span>C</span></span></span><span><span><span>字表）；斜眼、色盲、色弱；角膜屈光手术者；</span></span></span><span><span><span><br /><span>  </span><span> </span>4</span></span></span><span><span><span>、先天性心脏病；血压、心电图不正常；</span></span></span><span><span><span><br /><span>  </span><span> </span>5</span></span></span><span><span><span>、肝炎或肝脾肿大：</span></span></span><span><span><span>HBSAG</span></span></span><span><span><span>阳性；</span></span></span><span><span><span><br /><span>  </span><span> </span>6</span></span></span><span><span><span>、肾炎或血尿、蛋白尿；</span></span></span><span><span><span><br /><span>  </span><span> </span>7</span></span></span><span><span><span>、精神病家庭史、癫痫病史；</span></span></span><span><span><span><br /><span>  </span><span> </span>8</span></span></span><span><span><span>、口吃。</span></span></span><span><span><span></span></span></span> </p> <p align="left"> <span><span><b><span><span> </span></span></b></span></span><span><span><b><span>二、报名方式及面试须携带材料及证件</span></b></span></span><span><span><b><span></span></b></span></span> </p> <p align="left"> <span><span><span><span>  </span></span></span></span><span><span><span>（一）登陆四川航空官网（</span></span></span><span><span></span></span><a href="http://www.scal.com.xn--cn),2017-6p3dz687bembjy5qlu4d"><span><span><span>http://www.sichuanair.com/</span></span></span><span><span><span>），点击进入“</span></span></span><span><span><span>2017</span></span></span></a><span><span><span>年度飞行学员招聘”专栏进行报名，并将《四川航空股份有限公司飞行学员报名表》填写完毕后下载打印；</span></span></span><span><span><span></span></span></span> </p> <p align="left"> <span><span><span><span>  </span></span></span></span><span><span><span>（二）携带报名表及应聘材料在规定时间内到达指定地点参加首轮考核。</span></span></span><span><span><span></span></span></span> </p> <p align="left"> <span><span><span><span>   </span></span></span></span><span><span><span>★面试须携带材料及证件：</span></span></span><span><span><span></span></span></span> </p> <p align="left"> <span><span><span><span>  </span><span> </span>1</span></span></span><span><span><span>、《四川航空股份有限公司飞行学员报名表》（按要求贴好近期</span></span></span><span><span><span>1</span></span></span><span><span><span>寸免冠照片</span></span></span><span><span><span>1</span></span></span><span><span><span>张，背面贴好本人近期全身立姿</span></span></span><span><span><span>6</span></span></span><span><span><span>寸生活照</span></span></span><span><span><span>1</span></span></span><span><span><span>张）；</span></span></span><span><span><span></span></span></span> </p> <p align="left"> <span><span><span><span>  </span><span> </span>2</span></span></span><span><span><span>、应聘人员身份证原件及复印件（在校生需提供学生证原件或在校证明，已毕业的学生需提供毕业证书原件及复印件）；</span></span></span><span><span><span></span></span></span> </p> <p align="left"> <span><span><span><span>   </span>3</span></span></span><span><span><span>、</span></span></span><span><span><span>CET</span></span></span><span><span><span>证书或雅思等英语等级证书原件及复印件（如具备）。</span></span></span><span><span><span></span></span></span> </p> <p align="left"> <span><span><b><span><span>   </span></span></b></span></span><span><span><b><span>三、招聘程序</span></b></span></span><span><span><b><span></span></b></span></span> </p> <p align="left"> <span><span><span>登陆四川航空官网</span></span></span><span><span><span>（</span></span></span><span><span><span>http://www.sichuanair.com/</span></span></span><span><span><span>）</span></span></span><span><span><span>→填写报名信息→首轮考核（材料审核、身体初检、面试、英语测试）→公司复试→招飞体检→背景调查→签订三方就业协议及劳动合同→实施培训。<span></span></span></span></span> </p> <p align="left"> <span><span><b><span><span>    </span></span></b></span></span><span><span><b><span>四、就业安排</span></b></span></span><span><span><b><span></span></b></span></span> </p> <p align="left"> <span><span><span>通过终审的飞行学员送培前，与公司签订无固定期限劳动合同，工作地点根据公司安排统一分配。</span></span></span><span><span><span></span></span></span> </p> <p align="left"> <span><span><b><span><span>    </span></span></b></span></span><span><span><b><span>五、职业规划</span></b></span></span><span><span><b><span></span></b></span></span> </p> <p align="left"> <span><span><span>飞行学员→</span></span></span><span><span><span>E</span></span></span><span><span><span>类副驾驶→</span></span></span><span><span><span>D</span></span></span><span><span><span>类副驾驶→</span></span></span><span><span><span>C</span></span></span><span><span><span>类副驾驶→</span></span></span><span><span><span>B</span></span></span><span><span><span>类副驾驶→</span></span></span><span><span><span>A</span></span></span><span><span><span>类副驾驶→机长→教员。</span></span></span><span><span><span></span></span></span> </p> <p align="left"> <span><span><span>飞行学员培训时间约为</span></span></span><span><span><span>2</span></span></span><span><span><span>年，由</span></span></span><span><span><span>E</span></span></span><span><span><span>类副驾驶成长为机长约需</span></span></span><span><span><span>5~7</span></span></span><span><span><span>年。</span></span></span><span><span><span></span></span></span> </p> <p align="left"> <span><span><b><span><span>    </span></span></b></span></span><span><span><b><span>六、相关费用说明</span></b></span></span><span><span><b><span></span></b></span></span> </p> <p align="left"> <span><span><span>（一）首轮考核环节，应聘者自行承担食宿、交通费用；招飞体检和公司复试环节在成都总部举行，<b>川航针对外地考生提供共计</b></span></span></span><span><span><b><span>500RMB</span></b></span></span><span><span><b><span>上限的交通补贴</span></b></span></span><span><span><span>，实报实销（具体报销方式将在公司复试入围通知中说明），其他食宿交通由应聘者自行承担；</span></span></span><span><span><span></span></span></span> </p> <p align="left"> <span><span><span>（二）招飞体检鉴定费及辅助检查费用由川航承担；背景调查费用由公司承担；</span></span></span><span><span><span> </span></span></span> </p> <p align="left"> <span><span><span>（三）川航将对终审合格人员进行为期近两年的国内外飞行理论和飞行技术培训，培训费用约</span></span></span><span><span><span>70</span></span></span><span><span><span>万左右，费用由公司承担。培训期间按照公司薪酬福利政策享受飞行学员福利待遇。</span></span></span><span><span><span></span></span></span> </p> <p align="left"> <span><span><b><span>七、特别声明</span></b></span></span><span><span><b><span></span></b></span></span> </p> <p align="left"> <span><span><span><span> </span></span></span></span><span><span><span>（一）被录用人员将被安排在成都、重庆、昆明、西安、杭州、哈尔滨、三亚、北京等地工作，工作地点须服从公司安排，不服从安排者不予以录用；</span></span></span><span><span><span></span></span></span> </p> <p align="left"> <span><span><span><span> </span></span></span></span><span><span><span>（二）应聘人员在年龄、身体、学历等方面弄虚作假，若经查实，将立即取消其应聘、录用资格，川航有权保留追偿经济赔偿的权利；</span></span></span><span><span><span></span></span></span> </p> <p align="left"> <span><span><span><span> </span></span></span></span><span><span><span>（三）应聘人员保持手机畅通，川航将通过短信或电话发送各类通知；</span></span></span><span><span><span></span></span></span> </p> <p align="left"> <span><span><span><span> </span></span></span></span><span><span><span>（四）除本启事指定的面试地点外，本次招聘不在其他地方设立招聘处，请应聘者谨防上当；</span></span></span><span><span><span><br /><span>    </span></span></span></span><span><span><span>（五）川航从未委托或授权任何中介机构招收飞行学员（不包括委托飞院、南航大及民航大招收的飞行学员），应聘人员也无需向任何中介机构缴纳中介费用；</span></span></span><span><span><span></span></span></span> </p> <p align="left"> <span><span><span><span>  </span></span></span></span><span><span><span>（六）</span></span></span><span><span><span>本简章内容由四川航空股份有限公司人力资源部负责解释，招聘咨询电话： <span>028-65391642</span></span></span></span><span><span><span></span></span></span> </p> <p align="left"> <span><span><span><span>  </span></span></span></span><span><span><span>（七）本次招聘由川航纪检监察室负责监督，举报电话：<span>028—65391238</span>、<span><span>  </span></span></span></span></span> </p> <p align="left"> <span><span><span>传真：<span>028—65391239</span>；举报投诉电子邮箱：</span></span></span><a href="mailto:chjj@scal.com.cn%EF%BC%9B%E4%B8%BE%E6%8A%A5%E4%BF%A1%E5%87%BD"><span><span><span>chjj@scal.com.cn</span></span></span><span><span><span>；举报信函</span></span></span></a><span><span><span><span>  </span></span></span></span> </p> <p align="left"> <span><span><span>邮寄地址：成都双流国际机场川航大厦<span>6</span>楼<span>A</span>区<span>609</span>室。</span></span></span><span></span> </p> </td> </tr><tr><td>附件</td> <td colspan="3"> <table><tr><td valign="top"><div></div></td> </tr></table></td> </tr><tr><td>备注</td> <td colspan="3"> </td> </tr><tr><td>发布人</td> <td colspan="3"> 就业指导服务中心 </td> </tr><tr><td>审核部门</td> <td colspan="3"> 就业指导中心 </td> </tr></table></div> <div><span>职位(1): 飞行学员</span></div> <table border="0"><tr><td>需求人数</td> <td> 51-100人(76) </td> <td>工作类型</td> <td> 全职 </td> <td>工作所在省份</td> <td> 四川省 </td> <td>工作所在市</td> <td> 成都市 </td> </tr><tr><td>职位类别1</td> <td> 生产和运输设备操作人员 </td> <td>职位类别2</td> <td> </td> <td>年薪(万元)</td> <td colspan="3"> </td> </tr><tr><td>职位描述</td> <td colspan="7"> </td> </tr><tr><td colspan="8"><b>职位要求:</b></td> </tr><tr><td>生源地要求</td> <td> 不限 </td> <td>性别要求</td> <td> 男 </td> <td>外语语种要求</td> <td> 英语 </td> <td colspan="2"> </td> </tr><tr><td>学历专业要求</td> <td colspan="7"> <table border="0"><tr><td>学历</td> <td>专业</td> </tr><tr><td colspan="2"> 不限 </td> </tr></table></td> </tr><tr><td>其他要求</td> <td colspan="7"> </td> </tr></table><br />
     * isSaved : false
     * chatGroup : {"id":70705,"name":"[2017-湖北]四川航空股份有限公司","avatar":"http://cdn1.haitou.cc/university/7.png","version":35,"userCnt":19}
     * positions : [{"id":71099,"name":"飞行学员","resume":"http://www.sichuanair.com/","cities":"成都","type":1,"regulation_id":15530,"company_id":3877,"season":20161,"created_at":"2016-08-26 17:50:31","updated_at":"2016-08-26 17:50:31","is_email":1,"sort":0,"is_official":0,"is_deleted":0,"is_hidden":0,"status":0}]
     */

    public int id;
    public String company;
    public String title;
    public String holdtime;
    public int univ_id;
    public String universityName;
    public String address;
    public String logoUrl;
    public String applyUrl;
    public int is_cancel;
    public int is_official;
    public String web;
    public int totalClicks;
    public String content;
    public boolean isSaved;
    public ChatGroupBean chatGroup;
    public List<PositionsBean> positions;

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

    public static class ChatGroupBean {
      /**
       * id : 70705
       * name : [2017-湖北]四川航空股份有限公司
       * avatar : http://cdn1.haitou.cc/university/7.png
       * version : 35
       * userCnt : 19
       */

      public int id;
      public String name;
      public String avatar;
      public int version;
      public int userCnt;

      public static ChatGroupBean objectFromData(String str) {

        return new Gson().fromJson(str, ChatGroupBean.class);
      }

      public static ChatGroupBean objectFromData(String str, String key) {

        try {
          JSONObject jsonObject = new JSONObject(str);

          return new Gson().fromJson(jsonObject.getString(str), ChatGroupBean.class);
        } catch (JSONException e) {
          e.printStackTrace();
        }

        return null;
      }

      public static List<ChatGroupBean> arrayChatGroupBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<ChatGroupBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
      }

      public static List<ChatGroupBean> arrayChatGroupBeanFromData(String str, String key) {

        try {
          JSONObject jsonObject = new JSONObject(str);
          Type listType = new TypeToken<ArrayList<ChatGroupBean>>() {
          }.getType();

          return new Gson().fromJson(jsonObject.getString(str), listType);
        } catch (JSONException e) {
          e.printStackTrace();
        }

        return new ArrayList();
      }
    }

    public static class PositionsBean {
      /**
       * id : 71099
       * name : 飞行学员
       * resume : http://www.sichuanair.com/
       * cities : 成都
       * type : 1
       * regulation_id : 15530
       * company_id : 3877
       * season : 20161
       * created_at : 2016-08-26 17:50:31
       * updated_at : 2016-08-26 17:50:31
       * is_email : 1
       * sort : 0
       * is_official : 0
       * is_deleted : 0
       * is_hidden : 0
       * status : 0
       */

      public int id;
      public String name;
      public String resume;
      public String cities;
      public int type;
      public int regulation_id;
      public int company_id;
      public int season;
      public String created_at;
      public String updated_at;
      public int is_email;
      public int sort;
      public int is_official;
      public int is_deleted;
      public int is_hidden;
      public int status;

      public static PositionsBean objectFromData(String str) {

        return new Gson().fromJson(str, PositionsBean.class);
      }

      public static PositionsBean objectFromData(String str, String key) {

        try {
          JSONObject jsonObject = new JSONObject(str);

          return new Gson().fromJson(jsonObject.getString(str), PositionsBean.class);
        } catch (JSONException e) {
          e.printStackTrace();
        }

        return null;
      }

      public static List<PositionsBean> arrayPositionsBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<PositionsBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
      }

      public static List<PositionsBean> arrayPositionsBeanFromData(String str, String key) {

        try {
          JSONObject jsonObject = new JSONObject(str);
          Type listType = new TypeToken<ArrayList<PositionsBean>>() {
          }.getType();

          return new Gson().fromJson(jsonObject.getString(str), listType);
        } catch (JSONException e) {
          e.printStackTrace();
        }

        return new ArrayList();
      }
    }
  }
}

