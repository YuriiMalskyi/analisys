package com.spec.analysis.controller;

import com.spec.analysis.dto.SpecificationDTO;
import com.spec.analysis.dto.response.ObjectResponse;
import com.spec.analysis.dto.response.SpecificationNameMarkResponse;
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

    @PostMapping("/{userId}")
    public ResponseEntity<Long> addSpecification(@RequestBody SpecificationDTO specificationDTO,
                                                    @PathVariable("userId") Long userId) {
        return new ResponseEntity<>( specificationService.addSpecification(userId, specificationDTO), HttpStatus.OK);
    }

    // Done
    @GetMapping("/id/{specificationId}")
    public ResponseEntity<SpecificationDTO> getSpecificationById(@PathVariable("specificationId") Long specificationId) {
        return new ResponseEntity<>(specificationService.getSpecificationById(specificationId), HttpStatus.OK);
    }

    // Done
    @GetMapping("/get_standard/{specificationId}")
    public ResponseEntity<Long> getStandardBySpecification(@PathVariable("specificationId") Long specificationId) {
        return new ResponseEntity<>(specificationService
                .getStandardSpecificationIdByStudentSpecificationId(specificationId), HttpStatus.OK);
    }

    // Done
    @GetMapping("/standards")
    public ResponseEntity<List<SpecificationDTO>> getStandard() {
        return new ResponseEntity<>(specificationService.getStandardsDTO(), HttpStatus.OK);
    }

    // Done
    @GetMapping("/student/all/{studentId}")
    public ResponseEntity<List<SpecificationNameMarkResponse>> getSpecificationsByStudentId(@PathVariable("studentId") Long studentId) {
        return new ResponseEntity<>(specificationService.getSpecificationsByStudentId(studentId), HttpStatus.OK);
    }

    // Done
    @GetMapping("/student/all/{studentId}/{type}")
    public ResponseEntity<List<SpecificationNameMarkResponse>> getSpecificationsByStudentIdAndType(
            @PathVariable("studentId") Long studentId,
            @PathVariable("type") StudentSpecificationType studentSpecificationType) {
        return new ResponseEntity<>(specificationService
                .getSpecificationsByStudentIdAndType(studentId, studentSpecificationType), HttpStatus.OK);
    }

    @PostMapping("/student/update/{userId}")
    public ResponseEntity<Void> updateSpecification(@PathVariable("userId") Long userId, @RequestBody SpecificationDTO specificationDTO) {
        specificationService.updateSpecification(userId, specificationDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/evaluate/{studentSpecificationId}")
    public ResponseEntity<ObjectResponse> evaluateSpecification(@PathVariable("studentSpecificationId") Long studentSpecificationId) {
        return new ResponseEntity<>(specificationService.evaluateSpecification(studentSpecificationId), HttpStatus.OK);
    }

}
