package com.example.geocoding.repository;

import com.example.geocoding.Model.Company;
import com.example.geocoding.Model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store,Long> {
    @Query("SELECT s from Store s where ROUND(ACOS(COS(RADIANS( ?1 )-RADIANS(s.latitude))*COS(RADIANS(s.longitude)-RADIANS( ?2 ))) * 6371 ) <=?3 AND s.company=?4")
    List<Store> findStore(double latitude, double longitude, int Distance, Company company);
}
