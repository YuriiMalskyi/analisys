package com.spec.analysis.service.constants;

public enum ElementsMapKeys {
    MAIN_ELEMENTS_COUNT("MAIN_ELEMENTS_COUNT"),
    MAIN_ELEMENTS_CORRECT("MAIN_ELEMENTS_CORRECT"),
    CHILD_ELEMENTS_POINTS("CHILD_ELEMENTS_POINTS"),
    CHILD_ELEMENTS_COUNT("CHILD_ELEMENTS_COUNT");

    private String value;

    ElementsMapKeys(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
