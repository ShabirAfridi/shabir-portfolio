package com.shabir.portfolio.controller;

import com.shabir.portfolio.model.ContactForm;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * ContactController
 *
 * Handles the Contact page at /contact.
 *
 * ┌───────────────────────────────────────────────────────────────────┐
 * │  GET  /contact  → show the empty contact form                     │
 * │  POST /contact  → validate + process the form                     │
 * │                   ✓ valid   → redirect to /contact?success=true   │
 * │                   ✗ invalid → re-render form with error messages   │
 * └───────────────────────────────────────────────────────────────────┘
 *
 * In a production app you would inject JavaMailSender here and send a
 * real email. For now, the form data is logged to the console — this
 * lets you confirm everything works without any SMTP configuration.
 */
@Controller
@RequestMapping("/contact")
public class ContactController {
    @Autowired
    private JavaMailSender mailSender;
    private static final Logger log = LoggerFactory.getLogger(ContactController.class);

    /**
     * GET /contact
     * Shows the contact page with a fresh, empty ContactForm.
     * The `contactForm` attribute is required by th:object in the template.
     */
    @GetMapping
    public String showContactPage(Model model) {
        model.addAttribute("activePage", "contact");
        model.addAttribute("contactForm", new ContactForm());
        return "contact";
    }

    /**
     * POST /contact
     * Receives the submitted form, runs Bean Validation (@Valid),
     * and either re-renders the form with errors or processes it.
     *
     * @param contactForm   bound from the HTML form fields
     * @param bindingResult holds any @Valid constraint violations
     * @param redirectAttrs used to pass a one-time "success" flash message
     * @param model         used to re-render the page on validation failure
     */
    @PostMapping
    public String handleContactForm(
            @Valid @ModelAttribute("contactForm") ContactForm contactForm,
            BindingResult bindingResult,
            RedirectAttributes redirectAttrs,
            Model model
    ) {
        // ── 1. Validation failed — show errors on the same page ──────────
        if (bindingResult.hasErrors()) {
            model.addAttribute("activePage", "contact");
            // Spring automatically puts the rejected contactForm + errors
            // back into the model; Thymeleaf picks them up with th:errors.
            return "contact";
        }
        // TODO (production): Replace the log below with:
        //
            SimpleMailMessage mail = new SimpleMailMessage();
             mail.setTo("shabirofridi@gmail.com");
             mail.setSubject("[Portfolio] " + contactForm.getSubject());
             mail.setText("From: " + contactForm.getName()
                        + " <" + contactForm.getEmail() + ">\n\n"
                        + contactForm.getMessage());
        // ── 3. Redirect with a flash attribute (PRG pattern) ─────────────
        // RedirectAttributes.addFlashAttribute is temporary: it lives for
        // exactly one redirect and is then removed — prevents form resubmit
        // on browser refresh.
        log.info("Contact from Email sent From {}", contactForm.getEmail());
        redirectAttrs.addFlashAttribute("successMessage",
                "Message sent! I'll reply to " + contactForm.getEmail() + " within 24 hours.");

        try{
            mailSender.send(mail);
            log.info("Contract from sent from {}",contactForm.getEmail());
            redirectAttrs.addFlashAttribute("successMessage",
                    "Message sent! I'II replay to" + contactForm.getEmail() + "within 24 hours");

        }catch (MailException e ){
            log.error("Failed to send Contact email from {}",contactForm.getEmail(),e);
            redirectAttrs.addFlashAttribute("errorMessage",
                    "Something went Wrong sending your message - please email me directly at shabirofridi@gmail.com.");
        }
        return "redirect:/contact";
    }
}
