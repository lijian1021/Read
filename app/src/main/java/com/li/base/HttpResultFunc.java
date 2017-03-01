package com.li.base;

import rx.functions.Func1;

/**
 * Created by Administrator on 2017/3/1.
 */

public class HttpResultFunc<T> implements Func1<HttpResult<T> ,T> {
    @Override
    public T call(HttpResult<T> tHttpResult) {
        return tHttpResult.getNewslist();
    }
}
