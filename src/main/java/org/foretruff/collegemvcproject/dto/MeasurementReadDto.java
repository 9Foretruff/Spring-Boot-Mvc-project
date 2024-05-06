package org.foretruff.collegemvcproject.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
@Builder
public class MeasurementReadDto {

    Long id;

    String locationCode;

    String employeeCode;

    LocalDate measurementDate;

    BigDecimal pressure;


    BigDecimal temperature;


    BigDecimal humidity;


    BigDecimal windSpeed;

    String cloudCover;

    byte[] photo;

}
