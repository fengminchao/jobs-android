package com.muxistudio.jobs.bean;

import java.util.List;

/**
 * Created by ybao on 16/11/9.
 */

public class FulltimeList extends JobsBase {

    public List<FulltimeData> data;

    public class FulltimeData {

        public int id;
        public String title;
        public List<String> positionCities;
        public String logoUrl;
        public List<String> positionNames;

    }
}
