package com.spec.analysis.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "specification_elements")
public class SpecificationElement extends BaseEntity {

    @Column(name = "sequence_number")
    private String sequenceNumber;

    private String text;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "elements_hierarchy",
            joinColumns = {@JoinColumn(name = "base_spec_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "child_spec_id", referencedColumnName = "id")}
    )
    @BatchSize(size = 20)
    private List<SpecificationElement> childSpecificationElements = new ArrayList<>();

//    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
//    @JoinColumn(name = "specifications_id", nullable = false, referencedColumnName = "id")
//    private Specification specification;

}
