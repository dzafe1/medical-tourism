package com.tracking.panel.repository;

import com.tracking.panel.domain.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital,Long> {
    Hospital findByFullName(String name);
    @Query("SELECT h FROM Hospital h")
    List<Hospital> getAllHospitals();
    Hospital findOneById(Long id);
    @Query("SELECT COUNT(h.id) FROM Hospital h")
    Long getHospitalsCount();
}
