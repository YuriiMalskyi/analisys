package com.spec.analysis.entity;


import com.spec.analysis.enums.SpecificationTypes;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "specifications")
public class Specification extends BaseEntity {

    @Size(min = 1, max = 100)
    @Column(name = "specification_name")
    private String specificationName;

    @Column(name = "description")
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "specification_type")
    private SpecificationTypes specificationType;

    @OneToMany(mappedBy = "specifications")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<SpecificationElement> specificationElements;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User author;

}
