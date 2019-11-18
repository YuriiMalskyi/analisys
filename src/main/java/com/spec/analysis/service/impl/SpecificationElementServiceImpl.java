package com.spec.analysis.service.impl;

import com.spec.analysis.repository.SpecificationElementRepository;
import com.spec.analysis.service.SpecificationElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecificationElementServiceImpl implements SpecificationElementService {

    @Autowired
    private SpecificationElementRepository specificationElementRepository;

}
