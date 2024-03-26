package com.ferramentas.ferramentasbackend.controllers;

import com.ferramentas.ferramentasbackend.dto.output.TechnicianPresentationDto;
import com.ferramentas.ferramentasbackend.repository.TechnicianServiceRepository;
import com.ferramentas.ferramentasbackend.services.TechnicianServiceAssistance;
import com.ferramentas.ferramentasbackend.utils.endpoints.RouterUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = RouterUtils.TECHNICIAN_PAGE_ROUTE)
public class TechnicianPageController
{
    private final TechnicianServiceRepository technicianServiceRepository;
    private final TechnicianServiceAssistance technicianServiceAssistance;

    public TechnicianPageController(
            TechnicianServiceRepository technicianServiceRepository,
            TechnicianServiceAssistance technicianServiceAssistance
    ) {
        this.technicianServiceRepository = technicianServiceRepository;
        this.technicianServiceAssistance = technicianServiceAssistance;
    }

    @GetMapping
    public ResponseEntity<List<TechnicianPresentationDto>> listaTechnians()
    {
        List<TechnicianPresentationDto> technicianPresentationDtos = technicianServiceAssistance.listTechnicians();
        return  ResponseEntity.ok(technicianPresentationDtos);
    }


    @GetMapping(params = {"technician"})
    public ResponseEntity<TechnicianPresentationDto> getTechnician(@RequestParam("technician") Integer technicianId)
    {
        Optional<TechnicianPresentationDto> technicianPresentationDtoOptional = technicianServiceAssistance.getSpecificTechnicianPresentation(technicianId);

        technicianPresentationDtoOptional.orElseThrow(() -> {
            throw new Error("Technician Not Founded");
        });

        return  ResponseEntity.ok(technicianPresentationDtoOptional.get());
    }
}
