package com.example.bookingservice.Repository;

import com.example.bookingservice.Model.Offers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OffersRepository extends JpaRepository<Offers,Long> {
    @Query("SELECT o FROM Offers o WHERE o.type=?1 ORDER BY RANDOM() LIMIT 1")
    Offers findRandom(String type);

}
