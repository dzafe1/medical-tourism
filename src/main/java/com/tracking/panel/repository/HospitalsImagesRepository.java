package com.tracking.panel.repository;

import com.tracking.panel.domain.Hospital;
import com.tracking.panel.domain.HospitalsImages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalsImagesRepository extends JpaRepository<HospitalsImages,Long> {
    List<HospitalsImages> findAllByHospital(Hospital hospital);
}
