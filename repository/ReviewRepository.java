package com.ferramentas.ferramentasbackend.repository;

import com.ferramentas.ferramentasbackend.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findAllByFkSubService_PkSubService(Integer pkSubService);
    @Query("SELECT r FROM Review r, SubService ss, Technician tec WHERE r.fkSubService.pkSubService = ss.pkSubService AND ss.fkTechnician.pkTechnician = tec.pkTechnician AND tec.pkTechnician = :pkTechniciah ")
    List<Review> findAllByTechnician(Integer pkTechniciah);

    List<Review> findAllByFkSubService_FkTechnician_PkTechnician(Integer pkTechnician);
}