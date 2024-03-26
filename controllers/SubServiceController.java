package com.ferramentas.ferramentasbackend.controllers;

import com.ferramentas.ferramentasbackend.dto.input.SubServiceCreationDto;
import com.ferramentas.ferramentasbackend.dto.output.SubServicePresentationDto;
import com.ferramentas.ferramentasbackend.dto.output.TechnicianPresentationDto;
import com.ferramentas.ferramentasbackend.entities.SubService;
import com.ferramentas.ferramentasbackend.entities.TechnicianService;
import com.ferramentas.ferramentasbackend.repository.ServiceRepository;
import com.ferramentas.ferramentasbackend.repository.SubServiceRepository;
import com.ferramentas.ferramentasbackend.repository.TechnicianRepository;
import com.ferramentas.ferramentasbackend.repository.TechnicianServiceRepository;
import com.ferramentas.ferramentasbackend.services.ServiceAssistance;
import com.ferramentas.ferramentasbackend.services.TechnicianServiceAssistance;
import com.ferramentas.ferramentasbackend.services.files.FilesStorageServiceImpl;
import com.ferramentas.ferramentasbackend.utils.endpoints.RouterUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(RouterUtils.SUB_SERVICE_PAGE_ROUTE)
public class SubServiceController {
    private final SubServiceRepository subServiceRepository;
    private final ServiceRepository serviceRepository;
    private final TechnicianRepository technicianRepository;
    private final FilesStorageServiceImpl filesStorageService;
    private final TechnicianServiceRepository technicianServiceRepository;
    private final TechnicianServiceAssistance technicianServiceAssistance;
    private final ServiceAssistance serviceAssistance;


    public SubServiceController(SubServiceRepository subServiceRepository, ServiceRepository serviceRepository, TechnicianRepository technicianRepository, FilesStorageServiceImpl filesStorageService, TechnicianServiceRepository technicianServiceRepository, TechnicianServiceAssistance technicianServiceAssistance, ServiceAssistance serviceAssistance) {
        this.subServiceRepository = subServiceRepository;
        this.serviceRepository = serviceRepository;
        this.technicianRepository = technicianRepository;
        this.filesStorageService = filesStorageService;
        this.technicianServiceRepository = technicianServiceRepository;
        this.technicianServiceAssistance = technicianServiceAssistance;
        this.serviceAssistance = serviceAssistance;
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Transactional
    public ResponseEntity<Object> createSubService(@Valid @ModelAttribute SubServiceCreationDto subServiceCreationDto) {
        Optional<TechnicianService> optionalTechnicianService = technicianServiceRepository.findByFkTechnician_PkTechnicianAndFkService_PkService(
                subServiceCreationDto.getTechnician(),
                subServiceCreationDto.getService()
        );

        if (optionalTechnicianService.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        TechnicianService technicianService = optionalTechnicianService.get();

        SubService subService = new SubService();

        String subServiceImage = filesStorageService.save(subServiceCreationDto.getImage());

        subService.setTitle(subServiceCreationDto.getTitle());
        subService.setImage(subServiceImage);
        subService.setDescription(subServiceCreationDto.getDescription());
        subService.setMinPrice(subServiceCreationDto.getMinPrice());
        subService.setMaxPrice(subServiceCreationDto.getMaxPrice());

        subService.setFkService(technicianService.getFkService());
        subService.setFkTechnician(technicianService.getFkTechnician());

        subServiceRepository.save(subService);
        Optional<TechnicianPresentationDto> technicianPresentationDtoOptional = technicianServiceAssistance.getSpecificTechnicianPresentation(
                subServiceCreationDto.getTechnician()
        );

        if (technicianPresentationDtoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }


        return ResponseEntity.ok().body(
                new SubServicePresentationDto(
                        subService.getPkSubService(),
                        subService.getTitle(),
                        subService.getImage(),
                        subService.getDescription(),
                        subService.getMinPrice(),
                        subService.getMaxPrice(),
                        subService.getRating(),
                        technicianPresentationDtoOptional.get(),
                        serviceAssistance.getServicePresentationDtoBy(subService.getFkService())
                )
        );
    }

    @GetMapping(params = {"service"})
    public ResponseEntity<Object> getSubServiceByService(@RequestParam Integer service) {
        List<SubServicePresentationDto> subServices = subServiceRepository
                .findSubServiceByFkService_PkService(service)
                .stream().map(subService ->
                        new SubServicePresentationDto(
                                subService.getPkSubService(),
                                subService.getTitle(),
                                subService.getImage(),
                                subService.getDescription(),
                                subService.getMinPrice(),
                                subService.getMaxPrice(),
                                subService.getRating(),
                                technicianServiceAssistance.getSpecificTechnicianPresentation(
                                        subService.getFkTechnician().getPkTechnician()
                                ).get(),
                                serviceAssistance.getServicePresentationDtoBy(subService.getFkService())
                        )
                ).collect(Collectors.toList());

        return ResponseEntity.ok().body(subServices);
    }


    @GetMapping(path = "/technician", params = {"technician"})
    public ResponseEntity<List<SubServicePresentationDto>> getSubServiceByTechnician(@RequestParam Integer technician) {
        List<SubServicePresentationDto> subServices = subServiceRepository
                .findSubServiceByFkTechnician_PkTechnician(technician)
                .stream().map(subService ->
                        new SubServicePresentationDto(
                                subService.getPkSubService(),
                                subService.getTitle(),
                                subService.getImage(),
                                subService.getDescription(),
                                subService.getMinPrice(),
                                subService.getMaxPrice(),
                                subService.getRating(),
                                technicianServiceAssistance.getSpecificTechnicianPresentation(
                                        subService.getFkTechnician().getPkTechnician()
                                ).get(),
                                serviceAssistance.getServicePresentationDtoBy(subService.getFkService())
                        )
                ).collect(Collectors.toList());

        return ResponseEntity.ok().body(subServices);
    }
}
