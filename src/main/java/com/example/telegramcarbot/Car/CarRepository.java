package com.example.telegramcarbot.Car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("SELECT u FROM Car u where u.dnz LIKE :dnz OR u.vin LIKE :vin")
    List<Car> findByParams(@Param("dnz") String dnz,
                           @Param("vin") String vin);

    @Query("SELECT u FROM Car u where u.dnz LIKE :dnz")
    List<Car> findByDnz(@Param("dnz") String dnz);

    @Query("SELECT u FROM Car u where u.vin LIKE :vin")
    List<Car> findByVin(@Param("vin") String vin);

}
