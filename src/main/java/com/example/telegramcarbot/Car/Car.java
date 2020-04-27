package com.example.telegramcarbot.Car;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Car {
    @Id
    @GeneratedValue
    private long id;

    private String brand;
    private String model;
    private String dnz;
    private String color;
    private String year;
    private String vin;
    private String district;
    private String inGroupDate;
    private String inBaseDate;
    private String note;

    public Car() {
    }


    public Car(String brand, String model, String dnz, String color, String year, String vin, String district, String inGroupDate, String inBaseDate, String note) {
        this.brand = brand;
        this.model = model;
        this.dnz = dnz;
        this.color = color;
        this.year = year;
        this.vin = vin;
        this.district = district;
        this.inGroupDate = inGroupDate;
        this.inBaseDate = inBaseDate;
        this.note = note;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Марка: " + brand + "\n")
                .append("Модель: " + model + "\n")
                .append("ДНЗ: " + dnz + "\n");

        if (!color.isEmpty())
            sb.append("Колір: " + color + "\n");

        if (!year.isEmpty())
            sb.append("Рік випуску: " + year + "\n");

        if (!vin.isEmpty())
            sb.append("VIN: " + vin + "\n");

        if (!district.isEmpty())
            sb.append("Район: " + district + "\n");

        if (!inGroupDate.isEmpty())
            sb.append("Дата додавання в группу: " + inGroupDate + "\n");

        if (!note.isEmpty())
            sb.append("Примiтка: " + note + "\n\n");

        return sb.toString();
    }
}
