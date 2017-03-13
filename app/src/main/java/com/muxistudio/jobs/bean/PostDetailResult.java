package com.muxistudio.jobs.bean;

import java.util.List;

/**
 * Created by ybao on 17/1/2.
 */

public class PostDetailResult extends BaseData {

    public DataBean data;

    public static class DataBean {
        public PostData topic;
        public List<ReplyData> replys;

    }
}
