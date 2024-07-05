package id.muhammadfaisal.parkeecashier.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PaymentMethod {
    CASH(1),
    QRIS(2);

    public int code;
}
