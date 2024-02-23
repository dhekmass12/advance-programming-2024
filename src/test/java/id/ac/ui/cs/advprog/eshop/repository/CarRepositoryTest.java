package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CarRepositoryTest {
    @InjectMocks
    CarRepository carRepository;
    @BeforeEach
    void setUp(){

    }
    @Test
    void testCreateCar(){
        Car car = new Car();
        car.setCarName("Toyota");
        car.setCarQuantity(100);
        carRepository.create(car);

        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());
        Car savedCar = carIterator.next();
        assertFalse(carIterator.hasNext());
        assertEquals(car.getCarId(), savedCar.getCarId());
        assertEquals(car.getCarName(), savedCar.getCarName());
        assertEquals(car.getCarQuantity(), savedCar.getCarQuantity());
    }

    @Test
    void testFindAll(){
        Car car1 = new Car();
        car1.setCarId("eb55Be9f-1c39-460e-8860-71af6af63bd6");
        carRepository.create(car1);
        Car car2 = new Car();
        car2.setCarId("eb55Be9f-1c39-460e-8860-71af6af63bd7");
        carRepository.create(car2);

        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());
        assertEquals("eb55Be9f-1c39-460e-8860-71af6af63bd6", carIterator.next().getCarId());
        assertTrue(carIterator.hasNext());
        assertEquals("eb55Be9f-1c39-460e-8860-71af6af63bd7", carIterator.next().getCarId());
        assertFalse(carIterator.hasNext());
    }

    @Test
    void testFindAllIfEmpty(){
        Iterator<Car> carIterator = carRepository.findAll();
        assertFalse(carIterator.hasNext());
    }

    @Test
    void testFindById(){
        Car car1 = new Car();
        car1.setCarId("eb55Be9f-1c39-460e-8860-71af6af63bd6");
        car1.setCarName("Toyota");
        car1.setCarQuantity(100);
        carRepository.create(car1);
        Car car2 = new Car();
        car2.setCarId("eb55Be9f-1c39-460e-8860-71af6af63bd7");
        car2.setCarName("Yamaha");
        car2.setCarQuantity(200);
        carRepository.create(car2);

        Car searchedCar = carRepository.findById("eb55Be9f-1c39-460e-8860-71af6af63bd7");
        assertNotNull(searchedCar);
        assertEquals("Yamaha", searchedCar.getCarName());
        assertEquals(200, searchedCar.getCarQuantity());
    }

    @Test
    void testUpdateCar(){
        Car car1 = new Car();
        car1.setCarId("eb55Be9f-1c39-460e-8860-71af6af63bd6");
        car1.setCarName("Toyota");
        car1.setCarQuantity(100);
        carRepository.create(car1);

        Car oldCar = carRepository.findById("eb55Be9f-1c39-460e-8860-71af6af63bd6");
        assertEquals("eb55Be9f-1c39-460e-8860-71af6af63bd6", oldCar.getCarId());
        assertEquals("Toyota", oldCar.getCarName());
        assertEquals(100, oldCar.getCarQuantity());

        Car car2 = new Car();
        car2.setCarId(car1.getCarId());
        car2.setCarName("Yamaha");
        car2.setCarQuantity(200);

        assertNotNull(carRepository.update(car2.getCarId(), car2));

        Car updatedCar = carRepository.findById(car2.getCarId());
        assertEquals(car2.getCarId(), updatedCar.getCarId());
        assertEquals(car2.getCarName(), updatedCar.getCarName());
        assertEquals(car2.getCarQuantity(), updatedCar.getCarQuantity());
    }

    @Test
    void testUpdateEmptyCar(){
        Car car1 = new Car();
        car1.setCarId("eb55Be9f-1c39-460e-8860-71af6af63bd6");
        car1.setCarName("Toyota");
        car1.setCarQuantity(100);
        carRepository.create(car1);

        Car car2 = new Car();
        car2.setCarId("eb55Be9f-1c39-460e-8860-71af6af63bd7");
        car2.setCarName("Yamaha");
        car2.setCarQuantity(200);

        assertNull(carRepository.update(car2.getCarId(), car2));
    }

    @Test
    void testDeleteProduct(){
        Car car1 = new Car();
        car1.setCarId("eb55Be9f-1c39-460e-8860-71af6af63bd6");
        car1.setCarName("Toyota");
        car1.setCarQuantity(100);
        carRepository.create(car1);

        assertNotNull(carRepository.findById(car1.getCarId()));

        carRepository.delete(car1.getCarId());

        assertNull(carRepository.findById(car1.getCarId()));
    }
}
