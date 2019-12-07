package com.spec.analysis.repository;

import com.spec.analysis.entity.Specification;
import com.spec.analysis.enums.SpecificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SpecificationRepository extends JpaRepository<Specification, Long> {

//    Optional<Specification> findById(Long id);

    List<Specification> getAllBySpecificationType(SpecificationType specificationType);

    Optional<List<Specification>> findByAuthorIdAndMark(Long userId, Double mark);

    Optional<List<Specification>> findByAuthorIdAndMarkNot(Long userId, Double mark);

    @Query("select s from Specification s where s.id not in (select ss.id from Specification ss where ss.author.id = :userId)")
    Optional<List<Specification>> findNotStartedSpecifications(Long userId);
}
