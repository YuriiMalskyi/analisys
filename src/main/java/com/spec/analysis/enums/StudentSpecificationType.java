package com.spec.analysis.enums;

public enum StudentSpecificationType {
    EVALUATED_SPECIFICATION("EVALUATED_SPECIFICATION"),
    SAVED_SPECIFICATION("SAVED_SPECIFICATION"),
    NOT_YET_STARTED("NOT_YET_STARTED");

    private String value;

    StudentSpecificationType(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }

}
