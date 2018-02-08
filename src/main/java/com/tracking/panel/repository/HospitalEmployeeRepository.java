package com.tracking.panel.repository;

import com.tracking.panel.domain.Hospital;
import com.tracking.panel.domain.HospitalEmploye;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalEmployeeRepository extends JpaRepository<HospitalEmploye,Long>{
    List<HospitalEmploye> findAllByHospital(Hospital hospital);
}
