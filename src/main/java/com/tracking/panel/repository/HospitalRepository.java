package com.tracking.panel.repository;

import com.tracking.panel.domain.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital,Long> {
    Hospital findByFullName(String name);
}
