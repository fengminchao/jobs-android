package com.muxistudio.jobs.api.jobs;

import com.muxistudio.jobs.bean.CareerList;
import com.muxistudio.jobs.bean.EmployList;
import com.muxistudio.jobs.bean.FulltimeList;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ybao on 16/10/18.
 */

public interface JobsService {

  @GET("xjh/list?city=wuhan&client=m&kind=after&source=xjh&venue_id=0&zone=wh")
  Observable<CareerList> getCareerList(@Query("page") int page);

  @GET("zph/list?city=wuhan&client=m&kind=after&source=zph&venue_id=0&zone=wh")
  Observable<EmployList> getEmployList(@Query("page") int page);

  @GET("http://api.haitou.cc/xz/list?client=m&kind=after&page=1&type=fulltime")
  Observable<FulltimeList> getFulltimeList(@Query("page") int page);

}
