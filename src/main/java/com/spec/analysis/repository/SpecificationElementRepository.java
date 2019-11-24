package com.spec.analysis.repository;

import com.spec.analysis.entity.SpecificationElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

//@Transactional//(readOnly = true)
public interface SpecificationElementRepository extends JpaRepository<SpecificationElement, Long> {
}
