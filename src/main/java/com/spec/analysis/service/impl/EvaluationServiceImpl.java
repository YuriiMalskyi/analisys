package com.spec.analysis.service.impl;

import com.spec.analysis.entity.Specification;
import com.spec.analysis.entity.SpecificationElement;
import com.spec.analysis.enums.SpecificationType;
import com.spec.analysis.repository.SpecificationRepository;
import com.spec.analysis.service.EvaluationService;
import com.spec.analysis.utils.formatter.NumbersFormatter;
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

        int[] points_elementCount = null;
        int points = 0;
        Double mark = 0.0;
        int elementsCount = 0;

        if (Objects.nonNull(studentSpecification.getSpecificationElements())
                && Objects.nonNull(standardSpecification.getSpecificationElements())) {

            points_elementCount = openRecursion(studentSpecification.getSpecificationElements(), standardSpecification.getSpecificationElements());

        }
        if (Objects.nonNull(points_elementCount) && points_elementCount.length == 2) {
            points = points_elementCount[0];
            elementsCount = points_elementCount[1];
//            mark = (100 / (double) elementsCount) * points;
            mark = NumbersFormatter.formatDouble((100 / (double) elementsCount) * points);
        }
        return mark;
    }

    private int[] openRecursion(List<SpecificationElement> studentSpecification,
                                List<SpecificationElement> standardSpecification) {
        int points = 0;
        int elementsCount = 0;
        int[] points_elementCount = null;

        Iterator studentIterator = studentSpecification.listIterator();
        Iterator standardIterator = standardSpecification.listIterator();

        while (standardIterator.hasNext() && studentIterator.hasNext()) {

            elementsCount++;
            SpecificationElement standardSpecificationElement = (SpecificationElement) standardIterator.next();
            SpecificationElement studentSpecificationElement = (SpecificationElement) studentIterator.next();

            System.out.println("\n==============================================================================================================================");
            System.out.println("standardSpecificationElement #" + elementsCount + ": " + standardSpecificationElement.toString());
            System.out.println("studentSpecificationElement #" + elementsCount + ": " + studentSpecificationElement.toString());
            System.out.println("==============================================================================================================================");

            if (standardSpecificationElement.getElementTitle().equals(studentSpecificationElement.getElementTitle())
                    && standardSpecificationElement.getSequenceNumber().equals(studentSpecificationElement.getSequenceNumber())) {
                points++; // because elements are equals
            }

            if (Objects.nonNull(studentSpecificationElement.getSpecificationElements())
                    && Objects.nonNull(standardSpecificationElement.getSpecificationElements())) {

                points_elementCount = openRecursion(standardSpecificationElement.getSpecificationElements(),
                        studentSpecificationElement.getSpecificationElements());

                points += points_elementCount[0];
                elementsCount += points_elementCount[1];

            }

        }

        return new int[]{points, elementsCount};
    }


//    private int[] openRecursionForLoop(List<SpecificationElement> studentSpecification,
//                                       List<SpecificationElement> standardSpecification) {
//        int points = 0;
//        int elementsCount = 0;
//        int mainElementsCount = 0;
//        int mainElementsCountCorrect = 0;
//        int[] points_elementCount = null;
//
////        Iterator studentIterator = studentSpecification.listIterator();
////        Iterator standardIterator = standardSpecification.listIterator();
//
//        for (SpecificationElement standardElement : standardSpecification) {
//            mainElementsCount++;
//            SpecificationElement studentElement = null;
//
//            for (SpecificationElement studentElementSearch : studentSpecification) {
//                if (studentElementSearch.getElementTitle().equals(standardElement.getElementTitle())) {
//                    studentElement = studentElementSearch;
//                    break;
//                }
//            }
//            if (Objects.nonNull(studentElement)
//                    && studentElement.getSequenceNumber().equals(standardElement.getSequenceNumber())) {
//                mainElementsCountCorrect++;
//
//                System.out.println("\n==============================================================================================================================");
//                System.out.println("standardSpecificationElement #" + elementsCount + ": " + standardElement.toString());
//                System.out.println("studentSpecificationElement #" + elementsCount + ": " + studentElement.toString());
//                System.out.println("==============================================================================================================================");
//
//                if (standardElement.getElementTitle().equals(studentElement.getElementTitle())
//                        && standardElement.getSequenceNumber().equals(studentElement.getSequenceNumber())) {
//                    points++; // because elements are equals
//                }
//
//                if (Objects.nonNull(studentSpecificationElement.getSpecificationElements())
//                        && Objects.nonNull(standardSpecificationElement.getSpecificationElements())) {
//
//                    points_elementCount = openRecursion(standardSpecificationElement.getSpecificationElements(),
//                            studentSpecificationElement.getSpecificationElements());
//
//                    points += points_elementCount[0];
//                    elementsCount += points_elementCount[1];
//
//                }
//
//            } else {
//                break;
//            }
//
//        }
//
//
//        while (standardIterator.hasNext() && studentIterator.hasNext()) {
//
//            elementsCount++;
//            SpecificationElement standardSpecificationElement = (SpecificationElement) standardIterator.next();
//            SpecificationElement studentSpecificationElement = (SpecificationElement) studentIterator.next();
//
//            System.out.println("\n==============================================================================================================================");
//            System.out.println("standardSpecificationElement #" + elementsCount + ": " + standardSpecificationElement.toString());
//            System.out.println("studentSpecificationElement #" + elementsCount + ": " + studentSpecificationElement.toString());
//            System.out.println("==============================================================================================================================");
//
//            if (standardSpecificationElement.getElementTitle().equals(studentSpecificationElement.getElementTitle())
//                    && standardSpecificationElement.getSequenceNumber().equals(studentSpecificationElement.getSequenceNumber())) {
//                points++; // because elements are equals
//            }
//
//            if (Objects.nonNull(studentSpecificationElement.getSpecificationElements())
//                    && Objects.nonNull(standardSpecificationElement.getSpecificationElements())) {
//
//                points_elementCount = openRecursion(standardSpecificationElement.getSpecificationElements(),
//                        studentSpecificationElement.getSpecificationElements());
//
//                points += points_elementCount[0];
//                elementsCount += points_elementCount[1];
//
//            }
//
//        }
//
//        return new int[]{points, elementsCount};
//    }
}
