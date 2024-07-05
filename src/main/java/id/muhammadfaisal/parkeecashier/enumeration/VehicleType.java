package id.muhammadfaisal.parkeecashier.enumeration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum VehicleType {
    CAR(1),
    MOTORCYCLE(2);

    private int code;
}
