package com.spec.analysis.service.impl;

import com.spec.analysis.dto.SpecificationDTO;
import com.spec.analysis.dto.response.SingleObjectResponse;
import com.spec.analysis.dto.response.SpecificationNameMarkResponse;
import com.spec.analysis.entity.Specification;
import com.spec.analysis.entity.SpecificationElement;
import com.spec.analysis.enums.SpecificationType;
import com.spec.analysis.enums.StudentSpecificationType;
import com.spec.analysis.repository.SpecificationRepository;
import com.spec.analysis.service.EvaluationService;
import com.spec.analysis.service.SpecificationService;
import com.spec.analysis.utils.mapper.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class SpecificationServiceImpl implements SpecificationService {

    private final SpecificationRepository specificationRepository;

    private final ObjectMapper mapper;

    private final EvaluationService evaluationService;

    public SpecificationServiceImpl(SpecificationRepository specificationRepository, ObjectMapper mapper, EvaluationService evaluationService) {
        this.specificationRepository = specificationRepository;
        this.mapper = mapper;
        this.evaluationService = evaluationService;
    }

    @Override
    public void addSpecification(SpecificationDTO specificationDTO) {
        Specification specification = mapper.map(specificationDTO, Specification.class);
        specification.setMark(0.0);
        if (Objects.isNull(specification.getSpecificationType())) {
            specification.setSpecificationType(SpecificationType.STANDARD_SPECIFICATION);
            specificationRepository.save(specification);
            specification.setStandardSpecification(specificationRepository.getOne(specification.getId()));
        }
        List<SpecificationElement> els = specification.getSpecificationElements();
        if (Objects.nonNull(els)) {
            for (SpecificationElement el : els) {
                el.setSpecification(specification);
            }
        }
        specificationRepository.save(specification);
    }

    @Override
    public void updateSpecification(SpecificationDTO specificationDTO) {
        Specification specification = mapper.map(specificationDTO, Specification.class);
        specificationRepository.save(specification);
    }

    @Override
    public SpecificationDTO getSpecificationById(Long id) {
        Optional<Specification> optionalSpecification = specificationRepository.findById(id);
        return optionalSpecification.map(specification -> mapper.map(specification, SpecificationDTO.class)).orElse(null);
    }

    @Override
    public List<SpecificationNameMarkResponse> getSpecificationsByStudentIdAndType(Long
                                                                                           id, StudentSpecificationType studentSpecificationType) {
        Optional<List<Specification>> optionalSpecifications = Optional.empty();

        if (studentSpecificationType.equals(StudentSpecificationType.SAVED_SPECIFICATION)) {
            optionalSpecifications = specificationRepository.findByIdAndMark(id, 0.0);
        } else if (studentSpecificationType.equals(StudentSpecificationType.EVALUATED_SPECIFICATION)) {
            optionalSpecifications = specificationRepository.findByIdAndMarkNot(id, 0.0);
        }

        return optionalSpecifications.map(specification -> mapper.mapAll(specification, SpecificationNameMarkResponse.class)).orElse(null);
    }

    @Override
    public List<SpecificationNameMarkResponse> getSpecificationsByStudentId(Long id) {
        List<Specification> specifications = specificationRepository.findAll();
        return mapper.mapAll(specifications, SpecificationNameMarkResponse.class);
    }

    @Override
    public List<Specification> getStandards() {
        return specificationRepository.getAllBySpecificationType(SpecificationType.STANDARD_SPECIFICATION);
    }

    @Override
    public List<SpecificationDTO> getStandardsDTO() {
        List<Specification> specifications = specificationRepository.getAllBySpecificationType(SpecificationType.STANDARD_SPECIFICATION);
        return mapper.mapAll(specifications, SpecificationDTO.class);
    }

    @Override
    public SingleObjectResponse evaluateSpecification(Long standardSpecificationId, Long studentSpecificationId) {
        Optional<Specification> optionalStandardSpecification = specificationRepository.findById(standardSpecificationId);
        Optional<Specification> optionalStudentSpecification = specificationRepository.findById(studentSpecificationId);
        if (optionalStandardSpecification.isPresent() && optionalStudentSpecification.isPresent()) {
            Specification standardSpecification = optionalStandardSpecification.get();
            Specification studentSpecification = optionalStudentSpecification.get();
            Double mark = evaluationService.evaluateSpecification(standardSpecification, studentSpecification);
            studentSpecification.setMark(mark);
            specificationRepository.save(studentSpecification);
            return new SingleObjectResponse<>(mark);
        } else {
            return null;
        }
    }
}
