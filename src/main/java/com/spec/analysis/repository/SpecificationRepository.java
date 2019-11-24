package com.spec.analysis.repository;

import com.spec.analysis.entity.Specification;
import com.spec.analysis.enums.SpecificationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpecificationRepository extends JpaRepository<Specification, Long> {

    Optional<Specification> findById(Long id);

    Specification getBySpecificationType(SpecificationType specificationType);

}
