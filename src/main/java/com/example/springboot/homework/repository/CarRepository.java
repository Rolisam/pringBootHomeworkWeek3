package com.example.springboot.homework.repository;

import com.example.springboot.homework.model.Car;
import com.example.springboot.homework.model.Color;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CarRepository {

    private List<Car> carList;

    public CarRepository() {
        carList = new ArrayList<>();
        fillCarList();
    }

    public List<Car> findAllCars() {
        return carList;
    }

    private void fillCarList(){
        carList.add(new Car(1, "BMW", "X5", Color.BLUE));
        carList.add(new Car(2, "Fiat", "126P", Color.BLUE));
        carList.add(new Car(3, "Polonez", "Caro", Color.GREEN));
        carList.add(new Car(4, "Fiat", "Punto", Color.BLACK));
    }
}
