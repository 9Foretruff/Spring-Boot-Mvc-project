package org.foretruff.collegemvcproject.mapper;

import lombok.SneakyThrows;
import org.foretruff.collegemvcproject.dto.MeasurementCreateEditDto;
import org.foretruff.collegemvcproject.model.Measurement;
import org.springframework.stereotype.Component;

@Component
public class MeasurementCreateEditMapper implements Mapper<MeasurementCreateEditDto, Measurement> {
    @Override
    public Measurement map(MeasurementCreateEditDto entity) {
        var user = new Measurement();
        copy(entity, user);

        return user;
    }

    @Override
    public Measurement map(MeasurementCreateEditDto fromObject, Measurement toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    @SneakyThrows
    private void copy(MeasurementCreateEditDto dto, Measurement measurement) {
        measurement.setLocationCode(dto.getLocationCode());
        measurement.setEmployeeCode(dto.getEmployeeCode());
        measurement.setMeasurementDate(dto.getMeasurementDate());
        measurement.setPressure(dto.getPressure());
        measurement.setTemperature(dto.getTemperature());
        measurement.setHumidity(dto.getHumidity());
        measurement.setWindSpeed(dto.getWindSpeed());
        measurement.setCloudCover(dto.getCloudCover());

        if (dto.getPhoto() != null) {
            measurement.setPhoto(dto.getPhoto().getBytes());
        }
    }

}
