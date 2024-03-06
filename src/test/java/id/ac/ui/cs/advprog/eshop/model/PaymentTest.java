package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    private Map<String, String> voucherCodePaymentData;
    private Map<String, String> cashOnDeliveryPaymentData;
    @BeforeEach
    void setUp(){
        this.voucherCodePaymentData = new HashMap<>();
        this.cashOnDeliveryPaymentData = new HashMap<>();

        this.voucherCodePaymentData.put("voucherCode", "ESHOP12345678ABC");
        this.cashOnDeliveryPaymentData.put("address", "Jakarta");
        this.cashOnDeliveryPaymentData.put("deliveryFee", "50000");
    }
    @Test
    void testCreatePaymentEmptyData(){
        this.voucherCodePaymentData.clear();

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("1",
                    "voucherCode",
                    this.voucherCodePaymentData
            );
        });
    }

    @Test
    void testCreatePaymentWithVoucherCodeSuccessStatus(){
         Payment payment = new Payment("2",
                 "voucherCode",
                this.voucherCodePaymentData
         );

        assertSame(this.voucherCodePaymentData, payment.getPaymentData());
        assertEquals(1, payment.getPaymentData().size());
        assertTrue(payment.getPaymentData().containsKey("voucherCode"));

        assertEquals("2", payment.getId());
        assertEquals("voucherCode", payment.getMethod());
        assertEquals(PaymentStatus.SUCCESS, payment.getStatus());
    }

    @Test
    void testCreateOrderRejectedIfLessThan16CharStatus(){
        this.voucherCodePaymentData.clear();
        this.voucherCodePaymentData = new HashMap<>();
        this.voucherCodePaymentData.put("voucherCode", "ESHOP123");

        Payment payment = new Payment("1",
                "voucherCode",
                this.voucherCodePaymentData
        );

        assertSame(this.voucherCodePaymentData, payment.getPaymentData());
        assertEquals(1, payment.getPaymentData().size());
        assertTrue(payment.getPaymentData().containsKey("voucherCode"));

        assertEquals("1", payment.getId());
        assertEquals("voucherCode", payment.getMethod());
        assertEquals(PaymentStatus.REJECTED, payment.getStatus());
    }

    @Test
    void testCreateOrderRejectedIfNotStartedWithESHOPStatus(){
        this.voucherCodePaymentData.clear();
        this.voucherCodePaymentData = new HashMap<>();
        this.voucherCodePaymentData.put("voucherCode", "12345678ABCDEFGH");

        Payment payment = new Payment("1",
                "voucherCode",
                this.voucherCodePaymentData
        );

        assertSame(this.voucherCodePaymentData, payment.getPaymentData());
        assertEquals(1, payment.getPaymentData().size());
        assertTrue(payment.getPaymentData().containsKey("voucherCode"));

        assertEquals("1", payment.getId());
        assertEquals("voucherCode", payment.getMethod());
        assertEquals(PaymentStatus.REJECTED, payment.getStatus());
    }

    @Test
    void testCreateOrderRejectedIfNotContained8NumericCharStatus(){
        this.voucherCodePaymentData.clear();
        this.voucherCodePaymentData = new HashMap<>();
        this.voucherCodePaymentData.put("voucherCode", "ESHOP123456789BC");

        Payment payment = new Payment("1",
                "voucherCode",
                this.voucherCodePaymentData
        );

        assertSame(this.voucherCodePaymentData, payment.getPaymentData());
        assertEquals(1, payment.getPaymentData().size());
        assertTrue(payment.getPaymentData().containsKey("voucherCode"));

        assertEquals("1", payment.getId());
        assertEquals("voucherCode", payment.getMethod());
        assertEquals(PaymentStatus.REJECTED, payment.getStatus());
    }

    @Test
    void testCreatePaymentWithCashOnDeliverySuccessStatus(){
        Payment payment = new Payment("1",
                "cashOnDelivery",
                this.cashOnDeliveryPaymentData
        );

        assertSame(this.cashOnDeliveryPaymentData, payment.getPaymentData());
        assertEquals(2, payment.getPaymentData().size());
        assertTrue(payment.getPaymentData().containsKey("address"));
        assertTrue(payment.getPaymentData().containsKey("deliveryFee"));

        assertEquals("1", payment.getId());
        assertEquals("cashOnDelivery", payment.getMethod());
        assertEquals(PaymentStatus.SUCCESS, payment.getStatus());
    }

    @Test
    void testCreatePaymentRejectedIfAddressIsInvalidStatus(){
        this.cashOnDeliveryPaymentData.clear();
        this.cashOnDeliveryPaymentData = new HashMap<>();
        this.cashOnDeliveryPaymentData.put("address", "");
        this.cashOnDeliveryPaymentData.put("deliveryFee", "50000");

        Payment payment = new Payment("1",
                "cashOnDelivery",
                this.cashOnDeliveryPaymentData
        );

        assertSame(this.cashOnDeliveryPaymentData, payment.getPaymentData());
        assertEquals(2, payment.getPaymentData().size());
        assertTrue(payment.getPaymentData().containsKey("address"));
        assertTrue(payment.getPaymentData().containsKey("deliveryFee"));

        assertEquals("1", payment.getId());
        assertEquals("cashOnDelivery", payment.getMethod());
        assertEquals(PaymentStatus.REJECTED, payment.getStatus());
    }

    @Test
    void testCreatePaymentRejectedIfDeliveryFeeIsInvalidStatus(){
        this.cashOnDeliveryPaymentData.clear();
        this.cashOnDeliveryPaymentData = new HashMap<>();
        this.cashOnDeliveryPaymentData.put("address", "Jakarta");
        this.cashOnDeliveryPaymentData.put("deliveryFee", "");

        Payment payment = new Payment("1",
                "cashOnDelivery",
                this.cashOnDeliveryPaymentData
        );

        assertSame(this.cashOnDeliveryPaymentData, payment.getPaymentData());
        assertEquals(2, payment.getPaymentData().size());
        assertTrue(payment.getPaymentData().containsKey("address"));
        assertTrue(payment.getPaymentData().containsKey("deliveryFee"));

        assertEquals("1", payment.getId());
        assertEquals("cashOnDelivery", payment.getMethod());
        assertEquals(PaymentStatus.REJECTED, payment.getStatus());
    }
}
