/**
 * tailwind-config.js
 * Shared Tailwind CSS design token configuration.
 * Loaded AFTER the Tailwind CDN script on every page.
 *
 * Place at: src/main/resources/static/js/tailwind-config.js
 * Reference in Thymeleaf: <script th:src="@{/js/tailwind-config.js}"></script>
 */
tailwind.config = {
  darkMode: "class",
  theme: {
    extend: {
      colors: {
        "surface-variant":            "#23262c",
        "surface-container-high":     "#1d2025",
        "error-container":            "#9f0519",
        "error":                      "#ff716c",
        "on-surface":                 "#f6f6fc",
        "outline":                    "#74757a",
        "secondary":                  "#1cede1",
        "background":                 "#0c0e12",
        "surface-container-lowest":   "#000000",
        "on-primary":                 "#006657",
        "primary-fixed":              "#00f8d7",
        "on-primary-container":       "#005d4f",
        "on-background":              "#f6f6fc",
        "on-secondary":               "#00534e",
        "secondary-container":        "#006a64",
        "inverse-on-surface":         "#53555a",
        "on-secondary-fixed":         "#003d3a",
        "on-secondary-container":     "#dbfffa",
        "surface":                    "#0c0e12",
        "outline-variant":            "#46484d",
        "primary-container":          "#00fedc",
        "error-dim":                  "#d7383b",
        "surface-bright":             "#292c32",
        "on-error":                   "#490006",
        "surface-dim":                "#0c0e12",
        "surface-container-highest":  "#23262c",
        "primary":                    "#b6ffed",
        "on-primary-fixed":           "#00443a",
        "surface-container-low":      "#111318",
        "tertiary":                   "#64d0ff",
        "surface-container":          "#171a1f",
        "on-surface-variant":         "#aaabb0",
        "tertiary-fixed":             "#09c4fd",
      },
      borderRadius: {
        DEFAULT: "0.125rem",
        lg:      "0.25rem",
        xl:      "0.5rem",
        full:    "0.75rem",
      },
      fontFamily: {
        headline: ["Space Grotesk"],
        body:     ["Inter"],
        label:    ["Inter"],
        mono:     ["Roboto Mono"],
      },
    },
  },
};
