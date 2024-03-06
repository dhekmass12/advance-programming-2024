package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Payment {
    private String id;
    private String method;
    private PaymentStatus status;
    private Map<String, String> paymentData;

    private Payment(String id, String method, PaymentStatus status, Map<String, String> paymentData){
        this.id = id;
        this.method = method;
        if (paymentData.isEmpty()){
            throw new IllegalArgumentException();
        }
        this.paymentData = paymentData;

        if (method.equals("voucherCode")){
            status = getVoucherCodeStatus();
        }
        else if (method.equals("cashOnDelivery")){
            status = getCashOnDeliveryStatus();
        }

        this.status = status;
    }

    public Payment(String id, String method, Map<String, String> paymentData){
        this(id, method, PaymentStatus.NONE, paymentData);
    }

    private PaymentStatus getVoucherCodeStatus(){
        if (paymentData.size() != 1 || !paymentData.containsKey("voucherCode")){
            return PaymentStatus.REJECTED;
        }
        else{
            String voucherCode = paymentData.get("voucherCode");
            int charLong = voucherCode.length();
            String prefix = "";
            int numericalCharCount = 0;

            if (charLong >= 5){
                prefix = voucherCode.substring(0, 5);
            }
            for (int i = 0; i < voucherCode.length(); ++i){
                char character = voucherCode.charAt(i);
                if (Character.isDigit(character)){
                    numericalCharCount += 1;
                }
            }

            if ((charLong == 16) && (prefix.equals("ESHOP")) && (numericalCharCount == 8)){
                return PaymentStatus.SUCCESS;
            }
            else{
                return PaymentStatus.REJECTED;
            }
        }
    }

    private PaymentStatus getCashOnDeliveryStatus(){
        if (paymentData.size() != 2 || !paymentData.containsKey("address") || !paymentData.containsKey("deliveryFee")){
            return PaymentStatus.REJECTED;
        }
        else if (paymentData.get("address") == null || paymentData.get("address").isEmpty()){
            return PaymentStatus.REJECTED;
        }
        else if (paymentData.get("deliveryFee") == null || paymentData.get("deliveryFee").isEmpty()){
            return PaymentStatus.REJECTED;
        }
        else{
            return PaymentStatus.SUCCESS;
        }
    }
}
