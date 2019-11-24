package com.spec.analysis;

import com.spec.analysis.entity.Specification;
import com.spec.analysis.entity.SpecificationElement;
import com.spec.analysis.entity.User;
import com.spec.analysis.enums.SpecificationType;
import com.spec.analysis.repository.SpecificationElementRepository;
import com.spec.analysis.repository.SpecificationRepository;
import com.spec.analysis.repository.UserRepository;
import com.spec.analysis.service.EvaluationService;
import com.spec.analysis.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootApplication
@Transactional
public class AnalysisApplication implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SpecificationElementRepository specificationElementRepository;

    @Autowired
    SpecificationRepository specificationRepository;

    @Autowired
    EvaluationService evaluationService;

    @Autowired
    SpecificationService specificationService;

    public static void main(String[] args) {
        SpringApplication.run(AnalysisApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        long usersCount = userRepository.count();
        long specificationsCount = specificationRepository.count();
        long specificationElementsCount = specificationElementRepository.count();

        if (usersCount == 0
                || specificationsCount == 0
                || specificationElementsCount == 0) {
            System.out.println("Initializing...");
            if (usersCount == 0) {
                System.out.println("Creating new user...");
                User user = User.builder()
                        .username("admin")
                        .password("admin")
                        .firstName("admin")
                        .lastName("admin")
                        .isStudent(false)
                        .build();
                userRepository.save(user);
                System.out.println("User created:\n" + user.toString());
            }
            if (specificationsCount == 0) {
                System.out.println("Creating new specification...");
                Specification specification = Specification.builder()
                        .specificationName("Test specification")
                        .description("Description")
                        .author(userRepository.findById(1L).get())
                        .specificationType(SpecificationType.STANDARD_SPECIFICATION)
                        .build();
                specificationRepository.save(specification);
                System.out.println("Specification created:\n" + specification.toString());
            }
            if (specificationElementsCount == 0) {
                System.out.println("Creating new specification element...");
                Specification specification = specificationRepository.getOne(1L);
                SpecificationElement specificationElement = SpecificationElement.builder()
                        .sequenceNumber(1)
                        .childSpecificationElements(null)
                        .specification(specification)
                        .text("specificationElement")
                        .build();
                specificationElementRepository.save(specificationElement);
                System.out.println("Specification element created:\n" + specificationElement.toString());
            }
        } else {
            System.out.println("DataBase data found...");
            System.out.println("Users count: " + usersCount);
            List<User> users = userRepository.findAll();
            for(User u : users){
                System.out.println(u.toString());
            }
            System.out.println("Specifications count: " + specificationsCount);
            System.out.println("Specifications elements count: " + specificationElementsCount);
        }

        System.out.println(evaluationService.evaluateSpecification(specificationService.getStandard()));

    }

}
