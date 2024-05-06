package org.foretruff.collegemvcproject.service;

import lombok.RequiredArgsConstructor;
import org.foretruff.collegemvcproject.dto.MeasurementCreateEditDto;
import org.foretruff.collegemvcproject.dto.MeasurementFilter;
import org.foretruff.collegemvcproject.dto.MeasurementReadDto;
import org.foretruff.collegemvcproject.mapper.MeasurementCreateEditMapper;
import org.foretruff.collegemvcproject.mapper.MeasurementReadMapper;
import org.foretruff.collegemvcproject.repository.MeasurementRepository;
import org.foretruff.collegemvcproject.repository.querydsl.QPredicates;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.foretruff.collegemvcproject.model.QMeasurement.measurement;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final MeasurementReadMapper measurementReadMapper;
    private final MeasurementCreateEditMapper measurementCreateEditMapper;

    public Page<MeasurementReadDto> findAll(MeasurementFilter filter, Pageable pageable) {
        var predicate = QPredicates.builder()
                .add(filter.locationCode(), measurement.locationCode::containsIgnoreCase)
                .add(filter.employeeCode(), measurement.employeeCode::containsIgnoreCase)
                .add(filter.measurementDate(), measurement.measurementDate::before)
                .build();

        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id"));

        return measurementRepository.findAll(predicate, pageable)
                .map(measurementReadMapper::map);
    }

    @Transactional
    public Optional<MeasurementReadDto> findById(Long id) {
        return measurementRepository.findById(id)
                .map(measurementReadMapper::map);
    }

    @Transactional
    public MeasurementReadDto create(MeasurementCreateEditDto measurementDto) {
        return Optional.of(measurementDto)
                .map(measurementCreateEditMapper::map)
                .map(measurementRepository::save)
                .map(measurementReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<MeasurementReadDto> update(Long id, MeasurementCreateEditDto measurementDto) {
        return measurementRepository.findById(id)
                .map(entity -> measurementCreateEditMapper.map(measurementDto, entity))
                .map(measurementRepository::saveAndFlush)
                .map(measurementReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return measurementRepository.findById(id)
                .map(measurement -> {
                    measurementRepository.delete(measurement);
                    measurementRepository.flush();
                    return true;
                }).orElse(false);
    }

}
