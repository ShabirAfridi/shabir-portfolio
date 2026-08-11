package com.shabir.portfolio.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * ContactForm
 *
 * Data Transfer Object (DTO) for the contact page form.
 * Spring MVC binds the HTML form fields to this object automatically
 * via th:object="${contactForm}" in the Thymeleaf template.
 *
 * Bean Validation (@NotBlank, @Email, @Size) runs when the controller
 * method receives @Valid ContactForm — errors are collected into BindingResult.
 */
public class ContactForm {

    /**
     * Sender's full name.
     * Required, 2–60 characters.
     */
    @NotBlank(message = "Name is required.")
    @Size(min = 2, max = 60, message = "Name must be between 2 and 60 characters.")
    private String name;

    /**
     * Sender's email address.
     * Required, must be a valid email format.
     */
    @NotBlank(message = "Email is required.")
    @Email(message = "Please enter a valid email address.")
    private String email;

    /**
     * Subject line.
     * Required, 3–100 characters.
     */
    @NotBlank(message = "Subject is required.")
    @Size(min = 3, max = 100, message = "Subject must be between 3 and 100 characters.")
    private String subject;

    /**
     * Message body.
     * Required, 10–1000 characters.
     */
    @NotBlank(message = "Message is required.")
    @Size(min = 10, max = 1000, message = "Message must be between 10 and 1000 characters.")
    private String message;

    // ── Getters & Setters ──────────────────────────────────────────────

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
