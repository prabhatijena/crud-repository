package com.coolcoder.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.coolcoder.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer>{


}
