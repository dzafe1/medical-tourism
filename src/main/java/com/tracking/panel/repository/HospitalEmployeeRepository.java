package com.tracking.panel.repository;

import com.tracking.panel.domain.Hospital;
import com.tracking.panel.domain.HospitalEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalEmployeeRepository extends JpaRepository<HospitalEmployee,Long>{
    List<HospitalEmployee> findAllByHospital(Hospital hospital);
    HospitalEmployee findOneById(Long id);
}
