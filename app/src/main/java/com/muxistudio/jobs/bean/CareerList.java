package com.muxistudio.jobs.bean;

import java.util.List;

/**
 * Created by ybao on 16/11/9.
 */

public class CareerList extends JobsBase {

    public List<CareerData> data;

    public class CareerData {
        public int id;
        public String title;
        public String holdtime;
        public String universityShortName;
        public String address;
        public String logoUrl;
        public int totalClicks;
    }
}
