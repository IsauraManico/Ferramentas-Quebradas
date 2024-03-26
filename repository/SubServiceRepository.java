package com.ferramentas.ferramentasbackend.repository;

import com.ferramentas.ferramentasbackend.entities.SubService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubServiceRepository extends JpaRepository<SubService, Integer> {
    List<SubService> findSubServiceByFkService_PkService(Integer pkService);

    List<SubService> findSubServiceByFkTechnician_PkTechnician(Integer technician);

    Optional<SubService> findSubServiceByPkSubServiceAndFkTechnician_PkTechnician(Integer subServicePk, Integer technicianPk);
}