package com.mmartin.micrometer_demo.external;

public record CountryExt(
        CountryName name,
        String cca2,
        String flag
) {
    public record CountryName(
            String official
    ) {

    }
}
