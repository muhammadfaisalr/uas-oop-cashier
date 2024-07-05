package id.muhammadfaisal.parkeecashier.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewTransactionRequest extends BaseRequest {
    public int vehicleType;
    public int status;

    public NewTransactionRequest(String vehicleNumber, int vehicleType, int status) {
        super(vehicleNumber);
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.status = status;
    }
}
