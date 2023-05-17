package com.example.bmwdemo.dao;

import com.example.bmwdemo.dto.CarDTO;
import com.example.bmwdemo.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CarDAO {
    @Autowired
    CarRepository carRepository;

    public boolean createCar(CarDTO dto) {
        CarDTO carDTO = carRepository.save(dto);
        return carDTO != null;
    }

    public CarDTO searchCar(int id) {
        Optional<CarDTO> carDTO = carRepository.findById(id);
        return carDTO.get();
    }

    public boolean updateCar(CarDTO carDTO) {
        if(searchCar(carDTO.getId()) != null){
            carRepository.save(carDTO);
            return true;
        }
        return false;
    }

    public boolean removeCar(int id) {
        CarDTO carDTO = searchCar(id);
        if (carDTO != null) {
            carRepository.delete(carDTO);
            return true;
        }
        return false;
    }

    public List<CarDTO> retrieveAllCars() {
        List<CarDTO> carList = carRepository.findAll();
        return carList;
    }
}
