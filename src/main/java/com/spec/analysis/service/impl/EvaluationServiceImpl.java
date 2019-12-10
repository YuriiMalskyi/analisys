package com.spec.analysis.service.impl;

import com.spec.analysis.entity.Specification;
import com.spec.analysis.entity.SpecificationElement;
import com.spec.analysis.repository.SpecificationRepository;
import com.spec.analysis.service.EvaluationService;
import com.spec.analysis.service.constants.ElementsMapKeys;
import com.spec.analysis.utils.formatter.NumbersFormatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Slf4j
@Service
@Transactional
public class EvaluationServiceImpl implements EvaluationService {

    private final SpecificationRepository specificationRepository;

    public EvaluationServiceImpl(SpecificationRepository specificationRepository) {
        this.specificationRepository = specificationRepository;
    }

    @Override
    public Double evaluateSpecification(Specification standardSpecification, Specification studentSpecification) {
        Map<ElementsMapKeys, Integer> results;

        if (Objects.nonNull(studentSpecification.getSpecificationElements())
                && Objects.nonNull(standardSpecification.getSpecificationElements())) {

            results = startEvaluationProcess(studentSpecification.getSpecificationElements(), standardSpecification.getSpecificationElements());

            Integer mainElementsCount = results.get(ElementsMapKeys.MAIN_ELEMENTS_COUNT);
            Integer mainElementsCorrect = results.get(ElementsMapKeys.MAIN_ELEMENTS_CORRECT);
            Integer childElementsCount = results.get(ElementsMapKeys.CHILD_ELEMENTS_COUNT);
            Integer childElementsPoints = results.get(ElementsMapKeys.CHILD_ELEMENTS_POINTS);

            Double mainElementsMark = 0.0;
            Double childElementsMark = 0.0;

            // TODO refactor
            Integer realChildElementsCount = getChildElementsCount(standardSpecification.getSpecificationElements()) - standardSpecification.getSpecificationElements().size();
            childElementsCount = realChildElementsCount;

            int mainElementsMarkPercentage = (realChildElementsCount != 0) ? 30 : 100;
            int childElementsMarkPercentage = 70;

            if (mainElementsCount != 0 && mainElementsCorrect != 0) {
                mainElementsMark = NumbersFormatter.formatDouble(
                        (mainElementsMarkPercentage / mainElementsCount.doubleValue())
                                * mainElementsCorrect.doubleValue());
            }


            if (childElementsCount != 0 && childElementsPoints != 0) {
                childElementsMark = NumbersFormatter.formatDouble(
                        (childElementsMarkPercentage / childElementsCount.doubleValue())
                                * childElementsPoints.doubleValue());
            }

            log.info("\n------------------------------------------------------------------------------------------------"
                    + "\n|\tEvaluation completed:"
                    + "\n|\tBase elements points: " + mainElementsCorrect + " | Base elements count: " + mainElementsCount
                    + "\n|\tChild elements points: " + childElementsPoints + " | Child elements count: " + childElementsCount
                    + "\n------------------------------------------------------------------------------------------------"
                    + "\n|\tResult: " + (childElementsMark + mainElementsMark)
                    + "\n------------------------------------------------------------------------------------------------");
            return childElementsMark + mainElementsMark;
        } else {
            return null;
        }

    }

    private int getChildElementsCount(List<SpecificationElement> specificationElements) {
        int count = 0;
            for (SpecificationElement element : specificationElements) {
                count++;
                if (element.getSpecificationElements() != null && element.getSpecificationElements().size() > 0) {
                    for (SpecificationElement el : element.getSpecificationElements()) {
                        count++;
                        if (el.getSpecificationElements() != null && el.getSpecificationElements().size() > 0) {
                            count += getChildElementsCount(el.getSpecificationElements());
                        }
                    }
                }
            }
        return count;
    }

    private Map<ElementsMapKeys, Integer> startEvaluationProcess(List<SpecificationElement> studentSpecification,
                                                                 List<SpecificationElement> standardSpecification) {
        Map<ElementsMapKeys, Integer> values = new HashMap<>();

        int mainElementsCount = standardSpecification.size();
        int mainElementsCorrect = 0;
        int childElementsCount = 0;
        int childElementsPoints = 0;

        Iterator studentIterator = studentSpecification.listIterator();
        Iterator standardIterator = standardSpecification.listIterator();

        while (standardIterator.hasNext() && studentIterator.hasNext()) {

            SpecificationElement standardSpecificationElement = (SpecificationElement) standardIterator.next();
            SpecificationElement studentSpecificationElement = (SpecificationElement) studentIterator.next();

            if (standardSpecificationElement.getElementTitle().equals(studentSpecificationElement.getElementTitle())
                    && standardSpecificationElement.getSequenceNumber().equals(studentSpecificationElement.getSequenceNumber())) {
                mainElementsCorrect++; // because elements are equals

                if (Objects.nonNull(studentSpecificationElement.getSpecificationElements())
                        && Objects.nonNull(standardSpecificationElement.getSpecificationElements())) { // opening a loop/recursion

                    int[] points_elementsCount = startEvaluation(studentSpecificationElement.getSpecificationElements(),
                            standardSpecificationElement.getSpecificationElements());
                    childElementsCount += points_elementsCount[1];
                    childElementsPoints += points_elementsCount[0];

                }

            } else {
                for (SpecificationElement studentSpecEl : studentSpecification) {
                    if (standardSpecificationElement.getElementTitle().equals(studentSpecEl.getElementTitle())) {

                        int[] points_elementsCount = startEvaluation(studentSpecificationElement.getSpecificationElements(),
                                standardSpecificationElement.getSpecificationElements());
                        childElementsCount += points_elementsCount[1];
                        childElementsPoints += points_elementsCount[0];
                        break;

                    }
                }
            }

        }

        values.put(ElementsMapKeys.MAIN_ELEMENTS_COUNT, mainElementsCount);
        values.put(ElementsMapKeys.MAIN_ELEMENTS_CORRECT, mainElementsCorrect);
        values.put(ElementsMapKeys.CHILD_ELEMENTS_COUNT, childElementsCount);
        values.put(ElementsMapKeys.CHILD_ELEMENTS_POINTS, childElementsPoints);

        return values;
    }


    private int[] startEvaluation(List<SpecificationElement> studentSpecification, List<SpecificationElement> standardSpecification) {
        int points = 0;
        int elementsCount = 0;
        int[] points_elementCount;

        for (SpecificationElement standardElement : standardSpecification) {
            elementsCount++;
            SpecificationElement studentElement = null;

            for (SpecificationElement studentElementSearch : studentSpecification) {
                if (studentElementSearch.getElementTitle().equals(standardElement.getElementTitle())) {
                    studentElement = studentElementSearch;
                    break;
                }
            }

            if (Objects.nonNull(studentElement)) {

                if (studentElement.getSequenceNumber().equals(standardElement.getSequenceNumber())) {
                    points++;
                }

                if (Objects.nonNull(studentElement.getSpecificationElements())
                        && Objects.nonNull(standardElement.getSpecificationElements())) {

                    points_elementCount = startEvaluation(studentElement.getSpecificationElements(),
                            standardElement.getSpecificationElements());
                    log.info("Points: " + points_elementCount[0] + " | Elements count: " + points_elementCount[1]);
                    points += points_elementCount[0];
                    elementsCount += points_elementCount[1];

                }

            } else {
                break;
            }

        }

        return new int[]{points, elementsCount};
    }

}
