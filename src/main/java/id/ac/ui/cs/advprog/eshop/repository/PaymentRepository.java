package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PaymentRepository {
    private final List<Payment> payments = new ArrayList<>();
    public Payment save(Payment payment){
        payments.add(payment);
        return payment;
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
}
