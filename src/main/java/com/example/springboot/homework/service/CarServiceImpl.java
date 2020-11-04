package com.example.springboot.homework.service;

import com.example.springboot.homework.model.Car;
import com.example.springboot.homework.model.Color;
import com.example.springboot.homework.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService{
    private List<Car> carList;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        carList = carRepository.getCarList();
    }

    @Override
    public Optional <Car> getCarById(Long id){
        return carList
                .stream()
                .filter(o -> id == o.getId())
                .findFirst();
    }

    @Override
    public boolean addCar(Car car) {
        boolean isAdded;

        Optional<Car> carWithTheSameId = carList
                .stream().filter(o -> o.getId() == car.getId())
                .findFirst();

        if (carWithTheSameId.isPresent()) {
            isAdded = false;
        } else {
            isAdded = carList.add(car);
        }
        return isAdded;
    }

    @Override
    public boolean deleteCar(Long id) {
        boolean isRemoved;
        Optional<Car> car = carList.stream()
                .filter(o -> o.getId() == id)
                .findFirst();

        isRemoved = car.map(value -> carList.remove(value)).orElse(false);
        return isRemoved;
    }

    @Override
    public boolean editCarField(Long id, String field, String value) {
        boolean isEdited;

        Optional<Car> optionalCar = carList.stream()
                .filter(o -> o.getId() == id)
                .findFirst();

        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
            switch (field) {
                case "mark":
                    car.setMark(value);
                    break;
                case "model":
                    car.setModel(value);
                    break;
                case "color":
                    car.setColor(Color.valueOf(value));
                    break;
            }
        } else {
            return false;
        }

        return true;
    }



    @Override
    public List<Car> getCarsByColor(String color) {
        return carList.stream()
                .filter(o -> o.getColor() == Color.valueOf(color))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<List<Car>> getCarList() {
        return Optional.ofNullable(carList);
    }

}
