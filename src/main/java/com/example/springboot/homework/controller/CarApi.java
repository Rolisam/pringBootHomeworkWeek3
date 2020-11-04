package com.example.springboot.homework.controller;

import com.example.springboot.homework.model.Car;
import com.example.springboot.homework.service.CarService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/cars", produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE})
public class CarApi {
    CarService carService;

    @Autowired
    public CarApi(CarService carService) {
        this.carService = carService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Car> getCarById(@PathVariable("id") long id) {

        return carService.getCarById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Car>> getCars() {

        if (carService.getCarList().isPresent() || !carService.getCarList().get().isEmpty()) {
            return ResponseEntity.ok(carService.getCarList().get());
        } else {
            return ResponseEntity.notFound()
                    .build();
        }
    }

    @RequestMapping(value = "color/{color}", method = RequestMethod.GET)
    public ResponseEntity<List<Car>> getCarsByColor(@PathVariable("color") String color) {
        List<Car> carListByColor = carService.getCarsByColor(color);
        if (carListByColor.isEmpty()) {
            return ResponseEntity.notFound()
                    .build();
        } else {
            return ResponseEntity.ok(carListByColor);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Car> addCar(@Valid @RequestBody Car car, BindingResult bindingResult) {
        boolean isAdded = carService.addCar(car);
        if (isAdded) {
            carService.addCar(car);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Car> editCar(@RequestBody Car car, BindingResult bindingResult) {
        boolean isDeleted = carService.deleteCar(car.getId());
        boolean isEdited = carService.addCar(car);
        if (isDeleted && isEdited) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound()
                    .build();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Car> deleteCarById(@PathVariable("id") long id) {
        boolean isDeleted = carService.deleteCar(id);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound()
                    .build();
        }
    }

    @RequestMapping(method = RequestMethod.PATCH)
    public ResponseEntity<Car> editCarField(@RequestBody Car car) {
        boolean isEdited = carService.editCarField(car);
        if (isEdited) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound()
                    .build();
        }
    }
}
