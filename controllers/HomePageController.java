package com.ferramentas.ferramentasbackend.controllers;

import com.ferramentas.ferramentasbackend.dto.output.ServicePresentationDto;
import com.ferramentas.ferramentasbackend.dto.output.TechnicianPresentationDto;
import com.ferramentas.ferramentasbackend.services.ServiceAssistance;
import com.ferramentas.ferramentasbackend.services.TechnicianServiceAssistance;
import com.ferramentas.ferramentasbackend.utils.endpoints.RouterUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("Home Page Controller")
@RequestMapping(RouterUtils.HOME_PAGE_MAIN_ROUTE)
public class HomePageController {
    private final ServiceAssistance serviceAssistance;
    private final TechnicianServiceAssistance technicianServiceAssistance;

    public HomePageController(ServiceAssistance serviceAssistance, TechnicianServiceAssistance technicianServiceAssistance) {
        this.serviceAssistance = serviceAssistance;
        this.technicianServiceAssistance = technicianServiceAssistance;
    }

    @GetMapping("/services")
    public ResponseEntity<List<ServicePresentationDto>> listMostRequiredServices() {
        return ResponseEntity.ok(
                serviceAssistance.listMostRequiredServices()
        );
    }

    @GetMapping("/technician")
    public ResponseEntity<List<TechnicianPresentationDto>> listMostRequiredTechnicians() {
        return ResponseEntity.ok(
                technicianServiceAssistance.listMostRequiredTechnicians()
        );
    }

}
