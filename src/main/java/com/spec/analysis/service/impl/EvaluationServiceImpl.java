package com.spec.analysis.service.impl;

import com.spec.analysis.entity.Specification;
import com.spec.analysis.entity.SpecificationElement;
import com.spec.analysis.enums.SpecificationType;
import com.spec.analysis.repository.SpecificationRepository;
import com.spec.analysis.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class EvaluationServiceImpl implements EvaluationService {

    @Autowired
    private SpecificationRepository specificationRepository;

    @Override
    public Double evaluateSpecification(Specification studentSpecification) {
        Specification standardSpecification = specificationRepository.getBySpecificationType(SpecificationType.STANDARD_SPECIFICATION);
        System.out.println("Standard specification: " + standardSpecification.getSpecificationName());

        int[] points_elementCount;
        int points = 0;
        Double mark = 0.0;
        int elementsCount = 0;

        if (Objects.nonNull(studentSpecification.getSpecificationElements())
                && Objects.nonNull(standardSpecification.getSpecificationElements())) {

            Iterator standardIterator = standardSpecification.getSpecificationElements().listIterator();
            Iterator studentIterator = standardSpecification.getSpecificationElements().listIterator();

            while (standardIterator.hasNext() && studentIterator.hasNext()) {

                elementsCount++;
                SpecificationElement standardSpecificationElement = (SpecificationElement) standardIterator.next();
                SpecificationElement studentSpecificationElement = (SpecificationElement) studentIterator.next();

                System.out.println("\n==============================================================================================================================");
                System.out.println("standardSpecificationElement #" + elementsCount + ": " + standardSpecificationElement.toString());
                System.out.println("studentSpecificationElement #" + elementsCount + ": " + studentSpecificationElement.toString());
                System.out.println("==============================================================================================================================");
//
                if (standardSpecificationElement.getElementTitle().equals(studentSpecificationElement.getElementTitle())
                        && standardSpecificationElement.getSequenceNumber().equals(studentSpecificationElement.getSequenceNumber())
                        && Objects.nonNull(studentSpecificationElement.getChildSpecificationElements())
                        && Objects.nonNull(standardSpecificationElement.getChildSpecificationElements())) {

                    points++; // because elements are equals
                }
//
//                    points_elementCount = openRecursion(standardSpecificationElement.getChildSpecificationElements(),
//                            studentSpecificationElement.getChildSpecificationElements());
//
//                    points += points_elementCount[0];
//                    elementsCount += points_elementCount[1];
//
//                }

            }

        }
        mark = new Double(100 / elementsCount) * points;
        return mark;
    }

    private int[] openRecursion(List<SpecificationElement> studentChildSpecificationElement,
                                List<SpecificationElement> standardChildSpecificationElement) {
        int points = 0;
        Double mark = 0.0;
        int elementsCount = 0;

        return new int[]{points, elementsCount};
    }

}
