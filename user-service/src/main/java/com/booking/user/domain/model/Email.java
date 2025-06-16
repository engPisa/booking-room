package com.booking.user.domain.model;
import java.util.regex.Pattern;

public record Email(String email) {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE);

    public Email{
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()){
            throw new IllegalArgumentException("Formato de e-mail inválido.");
        }
    }
}
