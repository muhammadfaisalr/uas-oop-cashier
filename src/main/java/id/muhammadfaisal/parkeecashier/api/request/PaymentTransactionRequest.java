package id.muhammadfaisal.parkeecashier.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentTransactionRequest extends BaseRequest {
    public int paymentMethod;

    public PaymentTransactionRequest(String vehicleNumber, int paymentMethod) {
        super(vehicleNumber);
        this.paymentMethod = paymentMethod;
    }
}
