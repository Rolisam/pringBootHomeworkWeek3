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
    public boolean editCarField(Car car) {
        boolean isEdited;

        Optional<Car> carWithTheSameId = carList
                .stream().filter(o -> o.getId() == car.getId())
                .findFirst();

        if (carWithTheSameId.isPresent()) {
            if (car.getColor() == null) car.setColor(carWithTheSameId.get().getColor());
            if (car.getMark() == null) car.setMark(carWithTheSameId.get().getMark());
            if (car.getModel() == null) car.setModel(carWithTheSameId.get().getModel());
            carList.remove(carWithTheSameId.get());
            isEdited = carList.add(car);

        } else {
            isEdited = false;
        }
        return isEdited;
    }

    @Override
    public List<Car> getCarsByColor(String color){
        return  carList.stream()
                    .filter(o -> o.getColor() == Color.valueOf(color))
                    .collect(Collectors.toList());
    }

    @Override
    public Optional<List<Car>> getCarList() {
        return Optional.ofNullable(carList);
    }

}
