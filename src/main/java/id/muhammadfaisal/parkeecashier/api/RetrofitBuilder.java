package id.muhammadfaisal.parkeecashier.api;

import id.muhammadfaisal.parkeecashier.util.Constant;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {
    public static Retrofit getInstance() {
        return new Retrofit.Builder().baseUrl(Constant.URL.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

}
