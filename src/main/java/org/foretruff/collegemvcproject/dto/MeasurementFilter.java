
package org.foretruff.collegemvcproject.dto;

import java.time.LocalDate;

public record MeasurementFilter(String locationCode,
                                String employeeCode,
                                LocalDate measurementDate) {

}
