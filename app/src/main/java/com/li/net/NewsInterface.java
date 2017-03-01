package com.li.net;

import com.li.base.HttpResult;
import com.li.entity.NewsEntity;

import java.util.List;

import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Li on 2017/3/1.
 * 新闻接口
 */

public interface NewsInterface {
    @POST("{type}")
    Observable<HttpResult<List<NewsEntity>>> getContent(
            @Path("type") String type, @Query("key") String key, @Query("num") int num,
            @Query("page") int page);
}
