package com.spec.analysis.utils.validators.constants;

import java.util.regex.Pattern;

public final class ValidationConstants {

    public static final String LOGIN_REGEX = "^[_.A-Za-z0-9-]*$";

    public static final Pattern SMALL_CHARACTERS = Pattern.compile("^(?=.*[a-z]).+$");

    public static final Pattern BIG_CHARACTERS = Pattern.compile("^(?=.*[A-Z]).+$");

    public static final Pattern DIGITS = Pattern.compile("^(?=.*[0-9])");

    public static final Pattern SPECIAL_CHARACTERS = Pattern.compile("^(?=.*[$&+,:;=?@#|'<>.^*()%!\\p{P}\\p{S}]).+$");

}
