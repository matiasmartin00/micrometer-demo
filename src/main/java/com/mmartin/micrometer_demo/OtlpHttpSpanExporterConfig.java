package com.mmartin.micrometer_demo;

import io.opentelemetry.exporter.otlp.http.trace.OtlpHttpSpanExporter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OtlpHttpSpanExporterConfig {

    @Bean
    OtlpHttpSpanExporter otlpHttpSpanExporter(
        @Value("${management.opentelemetry.tracing.export.otlp.endpoint}") String url
    ) {
        return OtlpHttpSpanExporter.builder()
            .setEndpoint(url)
            .build();
    }
}
