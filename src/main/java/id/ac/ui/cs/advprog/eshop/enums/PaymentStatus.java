package id.ac.ui.cs.advprog.eshop.enums;

import lombok.Getter;

@Getter
public enum PaymentStatus {
    NONE,
    SUCCESS,
    REJECTED;

    public static PaymentStatus getPaymentStatus(String status){
        if (status.equals("SUCCESS")){
            return PaymentStatus.SUCCESS;
        }
        else if (status.equals("REJECTED")){
            return PaymentStatus.REJECTED;
        }

        return PaymentStatus.NONE;
    }
}
