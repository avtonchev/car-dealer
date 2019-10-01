package app.cardealer.repositories;

import app.cardealer.entites.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Repository
public interface CarRepository extends JpaRepository<Car, Long> {


    @Modifying
    @Query("SELECT c.make FROM Car c GROUP BY c.make ORDER BY c.make")
    List<String> extractCarMakes();


    @Modifying
    @Query("SELECT c FROM Car c WHERE c.make = :make ORDER BY c.model ASC, c.travelledDistance DESC")
    List<Car> findAllByMakeOrderByMakeAscTravelledDistanceDesc(@Param("make") String make);


    @Modifying
    @Query("SELECT c FROM Car c GROUP BY c.model ORDER BY c.make, c.model")
    List<Car> findAllCarsGroupedByModel();
}
