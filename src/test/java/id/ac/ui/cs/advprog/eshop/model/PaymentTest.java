package id.ac.ui.cs.advprog.eshop.model;

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
            Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b",
                    "voucherCode",
                    this.voucherCodePaymentData
            );
        });
    }

    @Test
    void testCreatePaymentSuccessStatus(){
         Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b",
                "voucherCode",
                this.voucherCodePaymentData
        );

        assertSame(this.voucherCodePaymentData, payment.getPaymentData());
        assertEquals(1, payment.getPaymentData().size());
        assertTrue(payment.getPaymentData().containsKey("ESHOP12345678ABC"));

        assertEquals("13652556-012a-4c07-b546-54eb1396d79b", payment.getId());
        assertEquals("voucherCode", payment.getMethod());
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreateOrderRejectedIfLessThan16CharStatus(){
        this.voucherCodePaymentData.clear();
        this.voucherCodePaymentData = new HashMap<>();
        this.voucherCodePaymentData.put("voucherCode", "ESHOP123");

        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b",
                "voucherCode",
                this.voucherCodePaymentData
        );

        assertSame(this.voucherCodePaymentData, payment.getPaymentData());
        assertEquals(1, payment.getPaymentData().size());
        assertTrue(payment.getPaymentData().containsKey("ESHOP123"));

        assertEquals("13652556-012a-4c07-b546-54eb1396d79b", payment.getId());
        assertEquals("voucherCode", payment.getMethod());
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreateOrderRejectedIfNotStartedWithESHOPStatus(){
        this.voucherCodePaymentData.clear();
        this.voucherCodePaymentData = new HashMap<>();
        this.voucherCodePaymentData.put("voucherCode", "12345678ABCDEFGH");

        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b",
                "voucherCode",
                this.voucherCodePaymentData
        );

        assertSame(this.voucherCodePaymentData, payment.getPaymentData());
        assertEquals(1, payment.getPaymentData().size());
        assertTrue(payment.getPaymentData().containsKey("ESHOP123"));

        assertEquals("13652556-012a-4c07-b546-54eb1396d79b", payment.getId());
        assertEquals("voucherCode", payment.getMethod());
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreateOrderRejectedIfNotContained8NumericCharStatus(){
        this.voucherCodePaymentData.clear();
        this.voucherCodePaymentData = new HashMap<>();
        this.voucherCodePaymentData.put("voucherCode", "ESHOP123456789BC");

        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b",
                "voucherCode",
                this.voucherCodePaymentData
        );

        assertSame(this.voucherCodePaymentData, payment.getPaymentData());
        assertEquals(1, payment.getPaymentData().size());
        assertTrue(payment.getPaymentData().containsKey("ESHOP123"));

        assertEquals("13652556-012a-4c07-b546-54eb1396d79b", payment.getId());
        assertEquals("voucherCode", payment.getMethod());
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentSuccessStatus(){
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b",
                "cashOnDelivery",
                this.cashOnDeliveryPaymentData
        );

        assertSame(this.cashOnDeliveryPaymentData, payment.getPaymentData());
        assertEquals(2, payment.getPaymentData().size());
        assertTrue(payment.getPaymentData().containsKey("address"));
        assertTrue(payment.getPaymentData().containsKey("deliveryFee"));

        assertEquals("13652556-012a-4c07-b546-54eb1396d79b", payment.getId());
        assertEquals("cashOnDelivery", payment.getMethod());
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreatePaymentRejectedIfAddressIsInvalidStatus(){
        this.cashOnDeliveryPaymentData.clear();
        this.cashOnDeliveryPaymentData = new HashMap<>();
        this.cashOnDeliveryPaymentData.put("address", "");
        this.cashOnDeliveryPaymentData.put("deliveryFee", "50000");

        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b",
                "cashOnDelivery",
                this.cashOnDeliveryPaymentData
        );

        assertSame(this.cashOnDeliveryPaymentData, payment.getPaymentData());
        assertEquals(2, payment.getPaymentData().size());
        assertTrue(payment.getPaymentData().containsKey("address"));
        assertTrue(payment.getPaymentData().containsKey("deliveryFee"));

        assertEquals("13652556-012a-4c07-b546-54eb1396d79b", payment.getId());
        assertEquals("cashOnDelivery", payment.getMethod());
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentRejectedIfDeliveryFeeIsInvalidStatus(){
        this.cashOnDeliveryPaymentData.clear();
        this.cashOnDeliveryPaymentData = new HashMap<>();
        this.cashOnDeliveryPaymentData.put("address", "Jakarta");
        this.cashOnDeliveryPaymentData.put("deliveryFee", "");

        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b",
                "cashOnDelivery",
                this.cashOnDeliveryPaymentData
        );

        assertSame(this.cashOnDeliveryPaymentData, payment.getPaymentData());
        assertEquals(2, payment.getPaymentData().size());
        assertTrue(payment.getPaymentData().containsKey("address"));
        assertTrue(payment.getPaymentData().containsKey("deliveryFee"));

        assertEquals("13652556-012a-4c07-b546-54eb1396d79b", payment.getId());
        assertEquals("cashOnDelivery", payment.getMethod());
        assertEquals("REJECTED", payment.getStatus());
    }
}
