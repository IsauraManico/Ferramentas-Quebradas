package com.ferramentas.ferramentasbackend.repository;

import com.ferramentas.ferramentasbackend.entities.TechnicianService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TechnicianServiceRepository extends JpaRepository<TechnicianService, Integer> {
    //List<TechnicianService> findAllByFkTechnician_PkTechnician(Integer pkTechnician);
    @Query("SELECT ts.fkService.title FROM TechnicianService  ts WHERE ts.fkTechnician.pkTechnician = :pkTechnician")
    List<String> findAllByFkTechnician_PkTechnician(Integer pkTechnician);

    @Query("SELECT ts FROM TechnicianService  ts WHERE ts.fkTechnician.pkTechnician = :pkTechnician")
    List<TechnicianService> findTechnicianServiceAllByFkTechnician(Integer pkTechnician);

    Optional<TechnicianService> findByFkTechnician_PkTechnicianAndFkService_PkService(Integer pkTechnician, Integer pkService);


}