package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PaymentRepository {
    private final List<Payment> payments = new ArrayList<>();
    private final Map<String, Order> orders = new HashMap<>();
    public Payment addPayment(Order order, String method, Map<String, String> paymentData){
        Payment payment = new Payment(Integer.toString(payments.size() + 1), method, paymentData);
        payments.add(payment);
        orders.put(payment.getId(), order);
        return payment;
    }
    public Payment setStatus(Payment payment, String status){
        PaymentStatus paymentStatus = PaymentStatus.getPaymentStatus(status);
        payment.setStatus(paymentStatus);
        String orderStatus = getOrderStatus(paymentStatus);
        orders.get(payment.getId()).setStatus(orderStatus);
        return payment;
    }

    private String getOrderStatus(PaymentStatus paymentStatus){
        if (paymentStatus == PaymentStatus.SUCCESS){
            return "SUCCESS";
        }
        else {
            return "FAILED";
        }
    }
    public Payment getPayment(String id){
        for (Payment savedPayment : payments){
            if (savedPayment.getId().equals(id)){
                return savedPayment;
            }
        }

        return null;
    }
    public List<Payment> getAll(){
        return payments;
    }

    public Map<String, Order> getAllOrders(){
        return orders;
    }
}
