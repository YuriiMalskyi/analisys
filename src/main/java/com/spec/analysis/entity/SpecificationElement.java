package com.spec.analysis.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
//@ToString(exclude = {"specification"})
@Table(name = "specification_elements")
public class SpecificationElement extends BaseEntity {

    @Column(name = "sequence_number")
    private Integer sequenceNumber;

    private String elementTitle;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinTable(
            name = "elements_hierarchy",
            joinColumns = {@JoinColumn(name = "base_spec_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "child_spec_id", referencedColumnName = "id")}
    )
    @BatchSize(size = 20)
    private List<SpecificationElement> specificationElements = new ArrayList<>();

    /**
     * JsonIgnore to avoid infinite recursion.
     */
    @JsonIgnore
    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "specification_id", referencedColumnName = "id")
    private Specification specification;

    public static boolean compareElements(SpecificationElement studentElement, SpecificationElement standardElement) {
        return (standardElement.getSequenceNumber().equals(studentElement.getSequenceNumber())
                && standardElement.getElementTitle().equals(studentElement.getElementTitle()));
    }

}
