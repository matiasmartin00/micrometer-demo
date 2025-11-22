package com.mmartin.micrometer_demo.external;

import io.micrometer.observation.annotation.Observed;
import io.micrometer.tracing.annotation.NewSpan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class ExternalService {

    private static final String ALL_PATH = "%s/all?fields=name,flag,cca2";

    private final String baseUrl;
    private final RestTemplate restTemplate;

    public ExternalService(@Value("${external.countries.base-url}") String baseUrl, RestTemplate restTemplate) {
        this.baseUrl = baseUrl;
        this.restTemplate = restTemplate;
    }

    @Observed(name = "call-api-countries-all")
    public List<CountryExt> getAllCountries() {
        log.info("Getting all countries and mapping");
        final var countries = this.restTemplate.getForObject(ALL_PATH.formatted(this.baseUrl), CountryExt[].class);
        assert countries != null;
        return List.of(countries);
    }
}
