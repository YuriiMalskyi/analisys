package com.spec.analysis.service.impl;

import com.spec.analysis.dto.SpecificationDTO;
import com.spec.analysis.dto.SpecificationElementDTO;
import com.spec.analysis.dto.response.ObjectResponse;
import com.spec.analysis.dto.response.SpecificationNameMarkResponse;
import com.spec.analysis.entity.Specification;
import com.spec.analysis.entity.SpecificationElement;
import com.spec.analysis.enums.SpecificationType;
import com.spec.analysis.enums.StudentSpecificationType;
import com.spec.analysis.repository.SpecificationRepository;
import com.spec.analysis.repository.UserRepository;
import com.spec.analysis.service.EvaluationService;
import com.spec.analysis.service.SpecificationService;
import com.spec.analysis.utils.mapper.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class SpecificationServiceImpl implements SpecificationService {

    private final SpecificationRepository specificationRepository;

    private final UserRepository userRepository;

    private final ObjectMapper mapper;

    private final EvaluationService evaluationService;

    public SpecificationServiceImpl(SpecificationRepository specificationRepository, UserRepository userRepository, ObjectMapper mapper, EvaluationService evaluationService) {
        this.specificationRepository = specificationRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.evaluationService = evaluationService;
    }

    @Override
    public Long addSpecification(Long userId, SpecificationDTO specificationDTO) {
        if (specificationDTO.getId() != null && specificationRepository.existsById(specificationDTO.getId())) {
            specificationRepository.delete(specificationRepository.getOne(specificationDTO.getId()));
            specificationDTO.setId(null);
        }
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
        specification.setAuthor(userRepository.findById(userId).orElse(null));
        specification.setStandardSpecification(
                specificationRepository.findById(specificationDTO.getStandardSpecificationId()).orElse(null));
        specificationRepository.save(specification);

        return specification.getId();
    }

    @Override
    public void updateSpecification(Long userId, SpecificationDTO specificationDTO) {
        if (specificationRepository.existsById(specificationDTO.getId())) {
            specificationRepository.delete(specificationRepository.getOne(specificationDTO.getId()));
        }
        addSpecification(userId, specificationDTO);
    }

    @Override
    public SpecificationDTO getSpecificationById(Long id) {
        Optional<Specification> optionalSpecification = specificationRepository.findById(id);
        SpecificationDTO specificationDTO = optionalSpecification.map(specification ->
                mapper.map(specification, SpecificationDTO.class)).orElse(null);
        if (optionalSpecification.isPresent()
                && specificationDTO != null
                && optionalSpecification.get().getSpecificationElements().size() > 0) {
            fillList(optionalSpecification.get(), specificationDTO);
            specificationDTO.setStandardSpecificationId(optionalSpecification.get().getStandardSpecification().getId());
        }
        return specificationDTO;
    }

    private void fillList(Specification main, SpecificationDTO dto) {
        List<SpecificationElementDTO> dtos = new ArrayList<>();

        for (SpecificationElement el : main.getSpecificationElements()) {
            SpecificationElementDTO elementDTO = mapper.map(el, SpecificationElementDTO.class);
            if (el.getSpecificationElements().size() > 0) {
                elementDTO.setSpecificationElements(fillChildElements(el));
            }
            dtos.add(elementDTO);
        }

        dto.setSpecificationElements(dtos);
    }

    private List<SpecificationElementDTO> fillChildElements(SpecificationElement specificationElement) {
        List<SpecificationElementDTO> dtos = new ArrayList<>();

        if (specificationElement.getSpecificationElements().size() > 0) {
            for (SpecificationElement el : specificationElement.getSpecificationElements()) {
                SpecificationElementDTO elementDTO = mapper.map(el, SpecificationElementDTO.class);
                if (el.getSpecificationElements().size() > 0) {
                    elementDTO.setSpecificationElements(fillChildElements(el));
                }
                dtos.add(elementDTO);
            }
        }

        return dtos;
    }

    private void fillList(SpecificationDTO main, Specification to) {
        List<SpecificationElement> els = new ArrayList<>();

        for (SpecificationElementDTO dtoEl : main.getSpecificationElements()) {
            SpecificationElement element = mapper.map(to, SpecificationElement.class);
            if (dtoEl.getSpecificationElements().size() > 0) {
                element.setSpecificationElements(fillChildElements(dtoEl));
            }
            els.add(element);
        }

        to.setSpecificationElements(els);
    }

    private List<SpecificationElement> fillChildElements(SpecificationElementDTO specificationElementDTO) {
        List<SpecificationElement> els = new ArrayList<>();

        if (specificationElementDTO.getSpecificationElements().size() > 0) {
            for (SpecificationElementDTO dtoEl : specificationElementDTO.getSpecificationElements()) {
                SpecificationElement element = mapper.map(dtoEl, SpecificationElement.class);
                if (dtoEl.getSpecificationElements().size() > 0) {
                    element.setSpecificationElements(fillChildElements(dtoEl));
                }
                els.add(element);
            }
        }

        return els;
    }


    @Override
    public List<SpecificationNameMarkResponse> getSpecificationsByStudentIdAndType(
            Long studentId, StudentSpecificationType studentSpecificationType) {
        Optional<List<Specification>> optionalSpecifications = Optional.empty();

        switch (studentSpecificationType) {
            case EVALUATED_SPECIFICATION: {
                optionalSpecifications = specificationRepository.findByAuthorIdAndMarkNot(studentId, 0.0);
                break;
            }
            case SAVED_SPECIFICATION: {
                optionalSpecifications = specificationRepository.findByAuthorIdAndMark(studentId, 0.0);
                break;
            }
            case NOT_YET_STARTED: {
                optionalSpecifications = specificationRepository.findNotStartedSpecifications(studentId);
                break;
            }
        }

        List<SpecificationNameMarkResponse> nameMarkResponses = optionalSpecifications.map(specification ->
                mapper.mapAll(specification, SpecificationNameMarkResponse.class)).orElse(null);
        if (nameMarkResponses != null) {
            for (SpecificationNameMarkResponse nameMarkResponse : nameMarkResponses) {
                if (nameMarkResponse.getStandardSpecificationId() == null) {
                    nameMarkResponse.setStandardSpecificationId(getStandardSpecificationIdByStudentSpecificationId(nameMarkResponse.getId()));
                }
            }
        }

        return nameMarkResponses;
    }

    @Override
    public List<SpecificationNameMarkResponse> getSpecificationsByStudentId(Long id) {
        List<Specification> specifications = specificationRepository.findAll();
        return mapper.mapAll(specifications, SpecificationNameMarkResponse.class);
    }

    @Override
    public SpecificationDTO getStandardDTO(Long specificationId) {
        Specification specification = specificationRepository.findById(specificationId).orElse(null);
        Specification standard = null;
        if (Objects.nonNull(specification) && Objects.nonNull(specification.getStandardSpecification())) {
            standard = specification.getStandardSpecification();
        }

        return (Objects.nonNull(standard)) ? mapper.map(standard, SpecificationDTO.class) : null;
    }

    @Override
    public Long getStandardSpecificationIdByStudentSpecificationId(Long studentSpecificationId) {
        Specification specification = specificationRepository.findById(studentSpecificationId).orElse(null);
        Long standardId = null;

        if (Objects.nonNull(specification) && Objects.nonNull(specification.getStandardSpecification())) {
            standardId = specification.getStandardSpecification().getId();
        }

        return standardId;
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
    public ObjectResponse<Double> evaluateSpecification(Long studentSpecificationId) {
        Optional<Specification> optionalStudentSpecification = specificationRepository.findById(studentSpecificationId);

        if (optionalStudentSpecification.isPresent()) {
            Specification standardSpecification = optionalStudentSpecification.get().getStandardSpecification();

            if (standardSpecification != null) {
                Specification studentSpecification = optionalStudentSpecification.get();
                Double mark = evaluationService.evaluateSpecification(standardSpecification, studentSpecification);
                studentSpecification.setMark(mark);
                specificationRepository.save(studentSpecification);
                return new ObjectResponse<>(mark);
            }
        }
        return null;
    }
}
