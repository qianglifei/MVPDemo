package com.moible.qlf.mvpdemo.api;

import io.reactivex.Flowable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitApiService {
    // 手机验证注册登录
//    @FormUrlEncoded
//    @POST("index.php/mobile/main/reg_customer")
//    Flowable<HttpResult<LoginInfo>> sms_verification(@Field("mobile") String mobile, @Field("smscode") String smscode, @Field("deviceid") String deviceid);

    @POST("dlzc/dlzc/grdlCx")
    Flowable<ResponseBody> loginData(@Body RequestBody requestBody);


}
