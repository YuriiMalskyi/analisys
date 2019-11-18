package com.spec.analysis;

import com.spec.analysis.entity.Specification;
import com.spec.analysis.entity.SpecificationElement;
import com.spec.analysis.entity.User;
import com.spec.analysis.enums.Authorities;
import com.spec.analysis.enums.SpecificationTypes;
import com.spec.analysis.repository.SpecificationElementRepository;
import com.spec.analysis.repository.SpecificationRepository;
import com.spec.analysis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class AnalysisApplication implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SpecificationElementRepository specificationElementRepository;

    @Autowired
    SpecificationRepository specificationRepository;

    public static void main(String[] args) {
        SpringApplication.run(AnalysisApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        long usersCount = userRepository.count();
        long specificationsCount = specificationRepository.count();

        if (usersCount == 0
                || specificationsCount == 0) {
            System.out.println("Initializing...");
            if (usersCount == 0) {
                System.out.println("Creating new user...");
                User user = User.builder()
                        .username("admin")
                        .password("admin")
                        .firstName("admin")
                        .lastName("admin")
                        .authority(Authorities.ROLE_ADMIN)
                        .build();
                userRepository.save(user);
                System.out.println("User created:\n" + user.toString());
            }
            if (specificationsCount == 0) {
				Specification specification = Specification.builder()
						.specificationName("Test specification")
						.description("Description")
						.author(userRepository.findById(1L).get())
						.specificationType(SpecificationTypes.STANDARD_SPECIFICATION)
						.build();
				specificationRepository.save(specification);

                SpecificationElement specificationElement = SpecificationElement.builder()
                        .sequenceNumber(1)
						.childSpecificationElements(null)
						.specification(specification)
                        .text("zalupa")
                        .build();
                specificationElementRepository.save(specificationElement);

				specification.setSpecificationElements(Collections.singletonList(specificationElementRepository.findById(1L).get()));
				specificationRepository.save(specification);
            }
        } else {
            System.out.println("DataBase data found...");
            if (userRepository.count() != 0) {
                List<User> users = userRepository.findAll();
                for (User user : users) {
                    System.out.println(user.toString());
                }
            } else if (specificationRepository.count() != 0) {
                List<Specification> specifications = specificationRepository.findAll();
                for (Specification specification : specifications) {
                    System.out.println(specification.toString());
                }
            }
        }
    }


}
