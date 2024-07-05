package id.muhammadfaisal.parkeecashier.helper;

import id.muhammadfaisal.parkeecashier.api.ApiService;
import id.muhammadfaisal.parkeecashier.api.RetrofitBuilder;
import id.muhammadfaisal.parkeecashier.api.request.BaseRequest;
import id.muhammadfaisal.parkeecashier.api.request.NewTransactionRequest;
import id.muhammadfaisal.parkeecashier.api.request.PaymentTransactionRequest;
import id.muhammadfaisal.parkeecashier.api.response.BaseResponse;
import io.reactivex.rxjava3.core.Observable;

public class ApiHelper {
    private ApiService getServices() {
        return RetrofitBuilder.getInstance().create(ApiService.class);
    }

    public Observable<BaseResponse> newTransaction(String vehicleNumber, int vehicleType) {
        return getServices().newTransaction(new NewTransactionRequest(vehicleNumber, vehicleType, 1));
    }

    public Observable<BaseResponse> inquiryTransaction(String vehicleNumber) {
        return getServices().inquiryTransaction(new BaseRequest(vehicleNumber));
    }

    public Observable<BaseResponse> paymentTransaction(String vehicleNumber, int paymentMethod) {
        return getServices().paymentTransaction(new PaymentTransactionRequest(vehicleNumber, paymentMethod));
    }
}
