package id.muhammadfaisal.parkeecashier.api.response;

import lombok.Data;

@Data
public class CheckInResponse {
    public int id;
    public String parkingSlip;
    public String vehicleNumber;
    public int vehicleType;
    public String paymentMethod;
    public String checkInTime;
    public String checkOutTime;
    public String discount;
    public long discountAmount;
    public long baseAmount;
    public long totalAmount;
    public String status;
}
