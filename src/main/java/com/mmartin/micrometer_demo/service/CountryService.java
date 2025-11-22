package com.mmartin.micrometer_demo.service;

import com.mmartin.micrometer_demo.CountryDTO;
import com.mmartin.micrometer_demo.external.CountryExt;
import com.mmartin.micrometer_demo.external.ExternalService;
import com.mmartin.micrometer_demo.persistence.Country;
import com.mmartin.micrometer_demo.persistence.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;
    private final ExternalService externalService;


    public List<CountryDTO> all() {
        this.loadCountries();
        return this.countryRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    private void loadCountries() {
        if (this.countryRepository.count() > 0) {
            return;
        }
        final var countryDTOS = this.externalService.getAllCountries();
        final var countries = countryDTOS.stream()
                .map(this::toModel)
                .toList();
        countryRepository.saveAll(countries);
    }

    private Country toModel(CountryExt dto) {
        return Country.builder()
                .code(dto.cca2())
                .flag(dto.flag())
                .name(dto.name().official())
                .build();
    }

    private CountryDTO toDto(Country model) {
        return new CountryDTO(model.getId(), model.getName(), model.getCode(), model.getFlag());
    }
}
