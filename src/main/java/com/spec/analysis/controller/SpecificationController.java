package com.spec.analysis.controller;

import com.spec.analysis.dto.SpecificationDTO;
import com.spec.analysis.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/specification")
public class SpecificationController {

    @Autowired
    private SpecificationService specificationService;

    @PostMapping
    public ResponseEntity addSpecification(@RequestBody SpecificationDTO specificationDTO) {
        specificationService.addSpecification(specificationDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/id")
    public ResponseEntity<SpecificationDTO> getSpecificationById(@RequestParam("specificationId") Long specificationId) {
        return new ResponseEntity<SpecificationDTO>(specificationService.getSpecificationById(specificationId), HttpStatus.OK);
    }

    @GetMapping("/standard")
    public ResponseEntity<SpecificationDTO> getStandard(){
        return new ResponseEntity<>(specificationService.getStandardDTO(), HttpStatus.OK);
    }

    @GetMapping("/student/id")
    public ResponseEntity<SpecificationDTO> getSpecificationByStudentId(@RequestParam("studentId") Long studentId) {
        return new ResponseEntity<>(specificationService.getSpecificationByStudentId(studentId), HttpStatus.OK);
    }

    @GetMapping("/student/all/id")
    public ResponseEntity<List<SpecificationDTO>> getSpecificationsByStudentId(@RequestParam("studentId") Long studentId) {
        return new ResponseEntity<>(specificationService.getSpecificationsByStudentId(studentId), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updateSpecification(@RequestBody SpecificationDTO specificationDTO) {
        specificationService.updateSpecification(specificationDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

}
