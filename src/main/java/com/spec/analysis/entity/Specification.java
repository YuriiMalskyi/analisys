package com.spec.analysis.entity;


import com.spec.analysis.enums.SpecificationType;
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
@ToString(callSuper = true)
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
    private SpecificationType specificationType;

    @OneToMany(mappedBy = "specification")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<SpecificationElement> specificationElements;

    @OneToOne
    @JoinColumn(name = "author")
    private User author;

    private int points;

}
