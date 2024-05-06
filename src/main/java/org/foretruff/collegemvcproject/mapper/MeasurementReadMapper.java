package org.foretruff.collegemvcproject.mapper;

import org.foretruff.collegemvcproject.dto.MeasurementReadDto;
import org.foretruff.collegemvcproject.model.Measurement;
import org.springframework.stereotype.Component;

@Component
public class MeasurementReadMapper implements Mapper<Measurement, MeasurementReadDto> {
    @Override
    public MeasurementReadDto map(Measurement entity) {
        return MeasurementReadDto.builder()
                .id(entity.getId())
                .locationCode(entity.getLocationCode())
                .employeeCode(entity.getEmployeeCode())
                .measurementDate(entity.getMeasurementDate())
                .pressure(entity.getPressure())
                .temperature(entity.getTemperature())
                .humidity(entity.getHumidity())
                .windSpeed(entity.getWindSpeed())
                .cloudCover(entity.getCloudCover())
                .photo(entity.getPhoto())
                .build();
    }

}
