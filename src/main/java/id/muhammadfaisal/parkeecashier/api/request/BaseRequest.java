package id.muhammadfaisal.parkeecashier.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseRequest {
    public String vehicleNumber;
}
