package com.test.sandartdelivery.Helper;

import com.test.sandartdelivery.Model.DeliveryPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiHelper {

    @GET("get")
    Call<List<DeliveryPojo>> getDeliveries();
}
