package com.spec.analysis.enums;

public enum SpecificationType {
    STANDARD_SPECIFICATION("STANDARD_SPECIFICATION"),
    STUDENT_SPECIFICATION("STUDENT_SPECIFICATION");

    private String value;

    SpecificationType(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }

}
