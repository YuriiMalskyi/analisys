package com.spec.analysis.repository;

import com.spec.analysis.entity.SpecificationElement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecificationElementRepository extends JpaRepository<SpecificationElement, Long> {
}
