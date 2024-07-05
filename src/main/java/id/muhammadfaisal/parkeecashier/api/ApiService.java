package id.muhammadfaisal.parkeecashier.api;

import id.muhammadfaisal.parkeecashier.api.request.BaseRequest;
import id.muhammadfaisal.parkeecashier.api.request.NewTransactionRequest;
import id.muhammadfaisal.parkeecashier.api.request.PaymentTransactionRequest;
import id.muhammadfaisal.parkeecashier.api.response.BaseResponse;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("new-transaction")
    Observable<BaseResponse> newTransaction(@Body NewTransactionRequest newTransactionRequest);

    @POST("inquiry-transaction")
    Observable<BaseResponse> inquiryTransaction(@Body BaseRequest newTransactionRequest);

    @POST("payment-transaction")
    Observable<BaseResponse> paymentTransaction(@Body PaymentTransactionRequest newTransactionRequest);
}
