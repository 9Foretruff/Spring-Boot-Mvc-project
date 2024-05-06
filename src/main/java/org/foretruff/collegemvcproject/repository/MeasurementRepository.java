package org.foretruff.collegemvcproject.repository;

import org.foretruff.collegemvcproject.model.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends
        JpaRepository<Measurement, Long>,
        QuerydslPredicateExecutor<Measurement> {

}
