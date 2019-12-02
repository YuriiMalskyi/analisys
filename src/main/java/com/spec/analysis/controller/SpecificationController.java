package com.spec.analysis.controller;

import com.spec.analysis.dto.SpecificationDTO;
import com.spec.analysis.enums.StudentSpecificationType;
import com.spec.analysis.service.SpecificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/specification")
public class SpecificationController {

    private final SpecificationService specificationService;

    public SpecificationController(SpecificationService specificationService) {
        this.specificationService = specificationService;
    }

    @PostMapping
    public ResponseEntity addSpecification(@RequestBody SpecificationDTO specificationDTO) {
        specificationService.addSpecification(specificationDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/id")
    public ResponseEntity<SpecificationDTO> getSpecificationById(@RequestParam("specificationId") Long specificationId) {
        return new ResponseEntity<>(specificationService.getSpecificationById(specificationId), HttpStatus.OK);
    }

    @GetMapping("/standard")
    public ResponseEntity<SpecificationDTO> getStandard() {
        return new ResponseEntity<>(specificationService.getStandardDTO(), HttpStatus.OK);
    }

    @GetMapping("/student/id")
    public ResponseEntity<List<SpecificationDTO>> getSpecificationsByStudentIdAndType(
            @RequestParam("studentId") Long studentId,
            @RequestParam("type") StudentSpecificationType studentSpecificationType) {
        return new ResponseEntity<>(specificationService
                .getSpecificationsByStudentIdAndType(studentId, studentSpecificationType), HttpStatus.OK);
    }

    @GetMapping("/student/all/id")
    public ResponseEntity<List<SpecificationDTO>> getSpecificationsByStudentId(@RequestParam("studentId") Long studentId) {
        return new ResponseEntity<>(specificationService.getSpecificationsByStudentId(studentId), HttpStatus.OK);
    }

    @PutMapping("/student/update")
    public ResponseEntity updateSpecification(@RequestBody SpecificationDTO specificationDTO) {
        specificationService.updateSpecification(specificationDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

}
