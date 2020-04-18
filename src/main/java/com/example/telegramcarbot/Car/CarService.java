package com.example.telegramcarbot.Car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    @Transactional(readOnly = true)
    public List<Car> findCarByParams(String dnz, String vin) {
        if(dnz.isEmpty())
            dnz = "-";
        if(vin.isEmpty())
            vin = "-";

        return carRepository.findByParams("%" + dnz + "%","%" + vin + "%");}

    @Transactional
    public void addCar(Car car) {
        carRepository.save(car);
    }

    @Transactional
    public void update(String sId, String brand, String model, String dnz,
                       String color, String year, String vin, String district,
                       String inGroupDate, String inBaseDate, String note) {
        Long id = Long.parseLong(sId);
        Car car = carRepository.getOne(id);
        car.setBrand(brand);
        car.setModel(model);
        car.setDnz(dnz);
        car.setColor(color);
        car.setYear(year);
        car.setVin(vin);
        car.setDistrict(district);
        car.setInGroupDate(inGroupDate);
        car.setInBaseDate(inBaseDate);
        car.setNote(note);

        carRepository.save(car);
    }

}
