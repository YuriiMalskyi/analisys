package com.spec.analysis.enums;

public enum SpecificationTypes {
    STANDARD_SPECIFICATION("STANDARD_SPECIFICATION"), STUDENT_SPECIFICATION("STUDENT_SPECIFICATION");

    private String value;

    SpecificationTypes(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }

}
