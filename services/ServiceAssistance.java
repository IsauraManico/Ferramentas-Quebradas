package com.ferramentas.ferramentasbackend.services;

import com.ferramentas.ferramentasbackend.entities.Service;
import com.ferramentas.ferramentasbackend.repository.ServiceRepository;
import com.ferramentas.ferramentasbackend.dto.output.ServicePresentationDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceAssistance {
    private final ServiceRepository serviceRepository;

    public ServiceAssistance(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<ServicePresentationDto> listMostRequiredServices() {
        List<ServicePresentationDto> services = serviceRepository
                .findAll(Pageable.ofSize(3))
                .map(
                        service -> new ServicePresentationDto(service.getPkService(), service.getTitle(), service.getImage())
                ).toList();

        return services;
    }

    public ServicePresentationDto getServicePresentationDtoBy(Service service) {
        return new ServicePresentationDto(service.getPkService(), service.getTitle(), service.getImage());
    }
}
