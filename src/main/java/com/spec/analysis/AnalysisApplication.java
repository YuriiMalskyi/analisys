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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
@Transactional
public class AnalysisApplication implements CommandLineRunner {

    private final UserRepository userRepository;

    private final SpecificationElementRepository specificationElementRepository;

    private final SpecificationRepository specificationRepository;

    private final EvaluationService evaluationService;

    private final SpecificationService specificationService;

    public AnalysisApplication(SpecificationService specificationService,
                               EvaluationService evaluationService,
                               SpecificationRepository specificationRepository,
                               SpecificationElementRepository specificationElementRepository,
                               UserRepository userRepository) {
        this.specificationService = specificationService;
        this.evaluationService = evaluationService;
        this.specificationRepository = specificationRepository;
        this.specificationElementRepository = specificationElementRepository;
        this.userRepository = userRepository;
    }

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

                System.out.println("Creating new user...");
                User student = User.builder()
                        .username("student")
                        .password("password")
                        .firstName("student")
                        .lastName("student")
                        .isStudent(true)
                        .build();
                userRepository.save(student);
                System.out.println("User created:\n" + student.toString());
            }
            if (specificationsCount == 0) {
                System.out.println("Creating new specification...");
                Specification specification = Specification.builder()
                        .specificationName("Test specification")
                        .mark(0.0)
                        .description("Description")
                        .author(userRepository.findById(1L).get())
                        .specificationType(SpecificationType.STANDARD_SPECIFICATION)
                        .build();

                SpecificationElement specificationElement = SpecificationElement.builder()
                        .sequenceNumber(1)
                        .elementTitle("specificationElement1")
                        .specificationElements(null)
                        .specification(specification)
                        .build();

                SpecificationElement specificationElement21 = SpecificationElement.builder()
                        .sequenceNumber(1)
                        .elementTitle("specificationElement2.1")
                        .specificationElements(null)
                        .specification(null)
                        .build();

                SpecificationElement specificationElement2 = SpecificationElement.builder()
                        .sequenceNumber(2)
                        .elementTitle("specificationElement2")
                        .specificationElements(Collections.singletonList(specificationElement21))
                        .specification(specification)
                        .build();

                SpecificationElement specificationElement3 = SpecificationElement.builder()
                        .sequenceNumber(3)
                        .elementTitle("specificationElement3")
                        .specificationElements(null)
                        .specification(specification)
                        .build();

                specification.setSpecificationElements(Arrays.asList(specificationElement, specificationElement2, specificationElement3));
                System.out.println("Specification: \n" + specification.toString());
                specificationRepository.save(specification);

                System.out.println("Specification created:\n" + specification.toString());
            }
        }
        if (usersCount != 0
                || specificationsCount != 0
                || specificationElementsCount != 0) {
            System.out.println("DataBase data found...");
            if (usersCount != 0) {
                System.out.println("Users count: " + usersCount);
                List<User> users = userRepository.findAll();
                for (User u : users) {
                    System.out.println(u.toString());
                }
            }
            if (specificationsCount != 0) {
                System.out.println("Specifications count: " + specificationsCount);
                System.out.println(specificationRepository.getAllBySpecificationType(SpecificationType.STANDARD_SPECIFICATION).toString());
            }
        }

        Specification standard = specificationService.getStandards().get(0);

        System.out.println(evaluationService.evaluateSpecification(standard, standard));

    }

}
