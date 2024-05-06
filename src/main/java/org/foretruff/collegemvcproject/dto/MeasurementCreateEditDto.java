package org.foretruff.collegemvcproject.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
public class MeasurementCreateEditDto {

    @NotBlank(message = "Location code cannot be blank")
    @Size(max = 255, message = "Location code must be at most 255 characters long")
    String locationCode;

    @NotBlank(message = "Employee code cannot be blank")
    @Size(max = 255, message = "Employee code must be at most 255 characters long")
    String employeeCode;

    @NotNull(message = "Measurement date cannot be null")
    LocalDate measurementDate;

    @NotNull(message = "Pressure cannot be null")
    @DecimalMin(value = "-999.99", inclusive = true, message = "Pressure must be at least -999.99")
    @DecimalMax(value = "999.99", inclusive = true, message = "Pressure cannot exceed 999.99")
    BigDecimal pressure;

    @NotNull(message = "Temperature cannot be null")
    @DecimalMin(value = "-999.99", inclusive = true, message = "Temperature must be at least -999.99")
    @DecimalMax(value = "999.99", inclusive = true, message = "Temperature cannot exceed 999.99")
    BigDecimal temperature;

    @NotNull(message = "Humidity cannot be null")
    @DecimalMin(value = "-999.99", inclusive = true, message = "Humidity must be at least -999.99")
    @DecimalMax(value = "999.99", inclusive = true, message = "Humidity cannot exceed 999.99")
    BigDecimal humidity;

    @NotNull(message = "Wind speed cannot be null")
    @DecimalMin(value = "0.00", inclusive = true, message = "Wind speed must be at least 0.00")
    @DecimalMax(value = "999.99", inclusive = true, message = "Wind speed cannot exceed 999.99")
    BigDecimal windSpeed;

    @NotBlank(message = "Cloud cover cannot be blank")
    @Size(max = 255, message = "Cloud cover must be at most 255 characters long")
    String cloudCover;

    MultipartFile photo;

}
