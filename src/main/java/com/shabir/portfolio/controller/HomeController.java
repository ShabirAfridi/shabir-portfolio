package com.shabir.portfolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * HomeController
 * Maps each URL route to its corresponding Thymeleaf template.
 *
 * Routes:
 *   GET /          → templates/index.html   (Hero)
 *   GET /about     → templates/about.html   (About + Skills + Education)
 *   GET /services  → templates/services.html (Projects)
 *   GET /contact   → templates/contact.html  (Contact)
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("activePage", "home");
        return "index";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("activePage", "about");
        return "about";
    }

    @GetMapping("/services")
    public String services(Model model) {
        model.addAttribute("activePage", "services");
        return "services";
    }

    // NOTE: /contact is handled by ContactController (supports GET + POST with form binding)
}
