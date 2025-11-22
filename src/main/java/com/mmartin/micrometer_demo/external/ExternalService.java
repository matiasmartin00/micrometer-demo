package com.mmartin.micrometer_demo.external;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ExternalService {

    private static final String ALL_PATH = "%s/all?fields=name,flag,cca2";

    private final String baseUrl;
    private final RestTemplate restTemplate;

    public ExternalService(@Value("${external.countries.base-url}") String baseUrl) {
        this.baseUrl = baseUrl;
        this.restTemplate = new RestTemplate();
    }

    public List<CountryExt> getAllCountries() {
        final var countries = this.restTemplate.getForObject(ALL_PATH.formatted(this.baseUrl), CountryExt[].class);
        assert countries != null;
        return List.of(countries);
    }
}
