package id.ac.ui.cs.advprog.eshop.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CarTest {
    Car car;
    @BeforeEach
    void setUp(){
        this.car = new Car();
        this.car.setCarId("eb55Be9f-1c39-460e-8860-71af6af63bd6");
        this.car.setCarName("Toyota");
        this.car.setCarColor("Red");
        this.car.setCarQuantity(100);
    }
    @Test
    void testGetCarId(){
        assertEquals("eb55Be9f-1c39-460e-8860-71af6af63bd6", this.car.getCarId());
    }

    @Test
    void testGetCarName(){
        assertEquals("Toyota", this.car.getCarName());
    }

    @Test
    void testGetCarColor(){
        assertEquals("Red", this.car.getCarColor());
    }

    @Test
    void testGetCarQuantity(){
        assertEquals(100, this.car.getCarQuantity());
    }
}
