# Shabir Ahmad — Portfolio (Spring Boot + Thymeleaf)

A personal developer portfolio built with **Spring Boot 3**, **Thymeleaf**, and **TailwindCSS**.

---

## Project Structure

```
src/main/
├── java/com/shabir/portfolio/
│   ├── PortfolioApplication.java        ← Entry point
│   ├── controller/
│   │   ├── HomeController.java          ← /, /about, /services
│   │   └── ContactController.java       ← GET + POST /contact
│   └── model/
│       └── ContactForm.java             ← Form DTO with Bean Validation
│
└── resources/
    ├── application.properties
    ├── templates/
    │   ├── fragments/
    │   │   ├── nav.html                 ← Shared navigation (th:fragment)
    │   │   └── footer.html              ← Shared footer (th:fragment)
    │   ├── index.html                   ← Hero page   (GET /)
    │   ├── about.html                   ← About + Skills + Education (GET /about)
    │   ├── services.html                ← Projects (GET /services)
    │   └── contact.html                 ← Contact form (GET + POST /contact)
    │
    └── static/
        ├── css/
        │   ├── style.css                ← Custom animations & components
        │   ├── responsive.css           ← Breakpoint overrides
        │   └── bootstrap.min.css        ← Bootstrap 5 (available, not used by default)
        └── js/
            ├── tailwind-config.js       ← Shared design tokens
            ├── theme.js                 ← Mobile menu + scroll reveal
            ├── jquery.min.js            ← jQuery 3.7 (local copy)
            └── bootstrap.bundle.min.js  ← Bootstrap JS (available)
```

---

## Prerequisites

| Tool     | Version     |
|----------|-------------|
| Java     | 17 or later |
| Maven    | 3.8+        |

---

## Running the App

```bash
# 1. Clone / unzip the project
cd portfolio

# 2. Run with Maven wrapper (no install needed)
./mvnw spring-boot:run

# 3. Open in browser
http://localhost:8080
```

> On Windows: use `mvnw.cmd spring-boot:run`

---

## Pages

| URL         | Template           | Description                     |
|-------------|--------------------|---------------------------------|
| `/`         | `index.html`       | Hero section                    |
| `/about`    | `about.html`       | About me, skills, education     |
| `/services` | `services.html`    | Projects (coming soon cards)    |
| `/contact`  | `contact.html`     | Contact form (GET + POST)       |

---

## How the Contact Form Works

The form uses the **POST / Redirect / GET (PRG) pattern** to prevent duplicate
submissions on browser refresh.

```
User submits form
       │
       ▼
POST /contact ──► @Valid ContactForm ──► BindingResult has errors?
                                              │YES → re-render contact.html with errors
                                              │NO  → log message, add flash attribute
                                                     └─► redirect: /contact?  (GET)
                                                                   show success banner
```

### Enabling Real Email (Production)

1. Add `spring-boot-starter-mail` to `pom.xml`
2. Add SMTP config to `application.properties`:

```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

3. In `ContactController.java`, inject `JavaMailSender` and uncomment the
   `SimpleMailMessage` block in the `handleContactForm` method.

---

## Customization

### Changing content
- **Name, bio, skills** → edit the relevant `templates/*.html` file
- **Social links** → search for `shabirofridi@gmail.com` and `linkedin.com/in/` in the templates
- **Colors** → edit the `colors` block in `static/js/tailwind-config.js`
- **Fonts** → change the Google Fonts import URL and update `fontFamily` in `tailwind-config.js`

### Adding a real project card
In `services.html`, replace a "Coming Soon" card with:

```html
<div class="reveal reveal-delay-1 bg-surface-container-low rounded-xl p-8 border border-outline-variant/10 ...">
  <h3>Your Project Name</h3>
  <p>Description...</p>
  <a href="https://github.com/your-repo">View on GitHub</a>
</div>
```

---

## Tech Stack

- **Spring Boot 3.2** — application framework
- **Thymeleaf** — server-side HTML templating
- **Bean Validation (Jakarta)** — `@NotBlank`, `@Email`, `@Size` on `ContactForm`
- **TailwindCSS** (CDN) — utility-first styling
- **Bootstrap 5** (local) — available for forms/modals if needed
- **jQuery 3.7** (local) — DOM utilities
- **Material Symbols Outlined** — icon font
- **Google Fonts** — Space Grotesk, Inter, Roboto Mono

---

*Built with precision — Shabir Ahmad, 2025*
