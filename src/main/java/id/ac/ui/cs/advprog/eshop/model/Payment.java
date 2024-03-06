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

    private static final String VOUCHER_CODE = "voucherCode";
    private static final String CASH_ON_DELIVERY = "cashOnDelivery";
    private static final String ADDRESS = "address";
    private static final String DELIVERY_FEE = "deliveryFee";

    private Payment(String id, String method, PaymentStatus status, Map<String, String> paymentData){
        this.id = id;
        this.method = method;
        if (paymentData.isEmpty()){
            throw new IllegalArgumentException();
        }
        this.paymentData = paymentData;

        if (method.equals(VOUCHER_CODE)){
            status = getVoucherCodeStatus();
        }
        else if (method.equals(CASH_ON_DELIVERY)){
            status = getCashOnDeliveryStatus();
        }

        this.status = status;
    }

    public Payment(String id, String method, Map<String, String> paymentData){
        this(id, method, PaymentStatus.NONE, paymentData);
    }

    private PaymentStatus getVoucherCodeStatus(){
        if (paymentData.size() != 1 || !paymentData.containsKey(VOUCHER_CODE)){
            return PaymentStatus.REJECTED;
        }
        else{
            String voucherCode = paymentData.get(VOUCHER_CODE);
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
        if (paymentData.size() != 2 || !paymentData.containsKey(ADDRESS) || !paymentData.containsKey(DELIVERY_FEE)){
            return PaymentStatus.REJECTED;
        }
        else if (paymentData.get(ADDRESS) == null || paymentData.get(ADDRESS).isEmpty()){
            return PaymentStatus.REJECTED;
        }
        else if (paymentData.get(DELIVERY_FEE) == null || paymentData.get(DELIVERY_FEE).isEmpty()){
            return PaymentStatus.REJECTED;
        }
        else{
            return PaymentStatus.SUCCESS;
        }
    }
}
