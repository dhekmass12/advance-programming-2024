package id.ac.ui.cs.advprog.eshop.functional;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CarControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    CarRepository carRepository;

    @Test
    void createCarPageTest() throws Exception{
        ResultActions response = mockMvc.perform(//
                get("/car/createCar")
        );

        // Assert
        response.andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"));
    }

    @Test
    void editCarPageTest() throws Exception{
        Car car = new Car();
        car.setCarId("eb55Be9f-1c39-460e-8860-71af6af63bd6");
        car.setCarName("Toyota");
        car.setCarQuantity(100);

        mockMvc.perform(post("/car/createCar")
                .param("carId", car.getCarId())
                .param("carName", car.getCarName())
                .param("carColor", car.getCarColor())
                .param("carQuantity", Integer.toString(car.getCarQuantity()))
        );

        ResultActions response = mockMvc.perform(//
                get("/car/editCar/eb55Be9f-1c39-460e-8860-71af6af63bd6")
        );

        // Assert
        response.andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"));
    }

    @Test
    void listCarPageTest() throws Exception{
        ResultActions response = mockMvc.perform(//
                get("/car/listCar")
        );

        // Assert
        response.andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"));
    }

    @Test
    void createCarPostTest() throws Exception{
        Car car = new Car();
        car.setCarId("car id");
        car.setCarName("car name");
        car.setCarColor("car color");
        car.setCarQuantity(100);

        mockMvc.perform(post("/car/createCar")
                        .param("carId", car.getCarId())
                        .param("carName", car.getCarName())
                        .param("carColor", car.getCarColor())
                        .param("carQuantity", Integer.toString(car.getCarQuantity()))
                )
                .andExpect(status().is(302))
                .andReturn();
    }

    @Test
    void editCarPostTest() throws Exception{
        Car car = new Car();
        car.setCarId("car id");
        car.setCarName("car name");
        car.setCarColor("car color");
        car.setCarQuantity(100);

        mockMvc.perform(post("/car/editCar")
                        .param("carId", car.getCarId())
                        .param("carName", car.getCarName())
                        .param("carColor", car.getCarColor())
                        .param("carQuantity", Integer.toString(car.getCarQuantity()))
                )
                .andExpect(status().is(302))
                .andReturn();
    }

    @Test
    void deleteCarPostTest() throws Exception{
        Car car = new Car();
        car.setCarId("car id");
        car.setCarName("car name");
        car.setCarColor("car color");
        car.setCarQuantity(100);

        mockMvc.perform(post("/car/deleteCar")
                        .param("carId", car.getCarId())
                        .param("carName", car.getCarName())
                        .param("carColor", car.getCarColor())
                        .param("carQuantity", Integer.toString(car.getCarQuantity()))
                )
                .andExpect(status().is(302))
                .andReturn();
    }
}