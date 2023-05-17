package com.example.bmwdemo.repository;

import com.example.bmwdemo.dao.CarDAO;
import com.example.bmwdemo.dto.CarDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CarRepositoryTest {
    @Autowired
    CarDAO carDAO;

    @Test
    void insertCar() {
        CarDTO carDto = new CarDTO("320i", "black", "year", "R500000.00", "2.0");
        boolean transactionSuccessful = carDAO.createCar(carDto);
        Assertions.assertEquals(true, transactionSuccessful);
    }

    @Test
    void searchCar() {
        CarDTO carDTO = carDAO.searchCar(1);
        Assertions.assertTrue(carDTO != null);
    }

    @Test
    void retrieveAllCars() {
        List<CarDTO> carDTOList = carDAO.retrieveAllCars();
        Assertions.assertTrue(carDTOList.size() > 0);
    }

    @Test
    void deleteCar() {
        boolean transactionSuccesful = carDAO.removeCar(1);
        Assertions.assertTrue(transactionSuccesful);
    }

    @Test
    void updateCar() {
        CarDTO carDto = new CarDTO("340D", "black", "year", "R500000.00", "2.0");
        carDto.setId(3);
        boolean transactionSuccesful = carDAO.updateCar(carDto);
        Assertions.assertTrue(transactionSuccesful);
    }

}