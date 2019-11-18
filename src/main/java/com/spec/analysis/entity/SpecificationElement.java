package com.spec.analysis.entity;

import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "specification_elements")
public class SpecificationElement extends BaseEntity {

    @Column(name = "sequence_number")
    private Integer sequenceNumber;

    private String text;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "elements_hierarchy",
            joinColumns = {@JoinColumn(name = "base_spec_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "child_spec_id", referencedColumnName = "id")}
    )
    @BatchSize(size = 20)
    private List<SpecificationElement> childSpecificationElements = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "specifications_id", referencedColumnName = "id")
    private Specification specification;

}
