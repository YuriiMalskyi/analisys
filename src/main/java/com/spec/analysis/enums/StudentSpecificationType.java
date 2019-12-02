package com.spec.analysis.enums;

public enum StudentSpecificationType {
    SOLVED_SPECIFICATION("SOLVED_SPECIFICATION"),
    SAVED_SPECIFICATION("SAVED_SPECIFICATION");

    private String value;

    StudentSpecificationType(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }

}
