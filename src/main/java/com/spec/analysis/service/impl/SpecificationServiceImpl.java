package com.spec.analysis.service.impl;

import com.spec.analysis.repository.SpecificationRepository;
import com.spec.analysis.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private SpecificationRepository specificationRepository;


}
