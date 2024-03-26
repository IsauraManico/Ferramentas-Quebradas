package com.ferramentas.ferramentasbackend.controllers;

import com.ferramentas.ferramentasbackend.dto.input.ServiceCreationDto;
import com.ferramentas.ferramentasbackend.dto.output.ServicePresentationDto;
import com.ferramentas.ferramentasbackend.entities.Service;
import com.ferramentas.ferramentasbackend.repository.ServiceRepository;
import com.ferramentas.ferramentasbackend.repository.TechnicianServiceRepository;
import com.ferramentas.ferramentasbackend.services.files.FilesStorageServiceImpl;
import com.ferramentas.ferramentasbackend.utils.endpoints.RouterUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController(value = "Service Controller")
@RequestMapping(value = RouterUtils.SERVICE_MAIN_ROUTE)
public class ServiceController {
    private final ServiceRepository serviceRepository;
    private final FilesStorageServiceImpl storageService;
    private final TechnicianServiceRepository technicianServiceRepository;

    public ServiceController(ServiceRepository serviceRepository, FilesStorageServiceImpl storageService, TechnicianServiceRepository technicianServiceRepository) {
        this.serviceRepository = serviceRepository;
        this.storageService = storageService;
        this.technicianServiceRepository = technicianServiceRepository;
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ServicePresentationDto> create(@Valid @ModelAttribute ServiceCreationDto serviceCreationDto) {
        MultipartFile image = serviceCreationDto.getImage();

        try {
            storageService.save(image);
            Service service = new Service();

            service.setDescription(serviceCreationDto.getDescription());
            service.setImage(image.getOriginalFilename());
            service.setTitle(serviceCreationDto.getTitle());

            serviceRepository.save(service);


            return ResponseEntity.status(HttpStatus.OK).body(
                    new ServicePresentationDto(service.getPkService(), service.getTitle(), service.getImage())
            );
        } catch (Exception e) {
            //message = "Could not upload the file: " + image.getOriginalFilename() + ". Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    new ServicePresentationDto(0, "", "")
            );
        }
    }

    @GetMapping(value = "/technician", params = {"id"})
    public ResponseEntity<List<ServicePresentationDto>> getTechnicianServices(@RequestParam("id") Integer id) {

        List<ServicePresentationDto> servicePresentationDtos = technicianServiceRepository
                .findTechnicianServiceAllByFkTechnician(id)
                .stream()
                .map((serviceTechnician) -> {
                  return new ServicePresentationDto(
                          serviceTechnician.getFkService().getPkService(),
                          serviceTechnician.getFkService().getTitle(),
                          serviceTechnician.getFkService().getImage()
                          );
                }).collect(Collectors.toList());

        return ResponseEntity.ok().body(servicePresentationDtos);
    }


    @GetMapping
    public ResponseEntity<List<ServicePresentationDto>> getServices(Pageable pageable) {
        List<ServicePresentationDto> servicePresentationDtos = serviceRepository.findAll(pageable).map(
                service -> new ServicePresentationDto(
                        service.getPkService(),
                        service.getTitle(),
                        service.getImage()
                )
        ).toList();

        return ResponseEntity.ok(servicePresentationDtos);
    }

}
