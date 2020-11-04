package com.example.springboot.homework.service;

import com.example.springboot.homework.model.Car;
import com.example.springboot.homework.model.Color;

import java.util.List;
import java.util.Optional;

public interface CarService {

    Optional<Car> getCarById(Long id);

    boolean addCar(Car car);

    boolean deleteCar(Long id);

    boolean editCarField(Long id, String field, String value);

    List<Car> getCarsByColor(String color);

    Optional<List<Car>> getCarList();

}
