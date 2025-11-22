package com.mmartin.micrometer_demo;

public record CountryDTO(
        Long id,
        String name,
        String code,
        String flag
) {
}
