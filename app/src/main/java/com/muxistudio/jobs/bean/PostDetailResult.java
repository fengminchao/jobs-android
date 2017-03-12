package com.muxistudio.jobs.bean;

import java.util.List;

/**
 * Created by ybao on 17/1/2.
 */

public class PostDetailResult extends BaseData {

    public PostData postData;

    public class PostDetailData {

        public PostData topic;
        public List<ReplyData> replys;
    }
}
