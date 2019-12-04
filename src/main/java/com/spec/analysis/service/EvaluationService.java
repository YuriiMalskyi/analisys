package com.spec.analysis.service;

import com.spec.analysis.entity.Specification;

public interface EvaluationService {

    Double evaluateSpecification(Specification standardSpecification, Specification studentSpecification);

}
