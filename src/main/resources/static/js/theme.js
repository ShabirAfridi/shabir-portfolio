/**
 * theme.js  —  Portfolio Animations & Interactivity
 * Place at: src/main/resources/static/js/theme.js
 *
 * Features:
 *  1. Mobile hamburger menu toggle
 *  2. Scroll-reveal animations via IntersectionObserver
 *  3. Active nav link highlighting as you scroll
 *  4. Hero elements auto-reveal on page load
 */

/* ═══════════════════════════════════════════════
   1. MOBILE MENU
   Toggles the #mobile-menu panel open/closed.
   Also swaps the hamburger ↔ X icon.
════════════════════════════════════════════════ */
const hamburgerBtn = document.getElementById('hamburger-btn');
const mobileMenu   = document.getElementById('mobile-menu');
const iconOpen     = document.getElementById('icon-open');
const iconClose    = document.getElementById('icon-close');

let menuOpen = false;

if (hamburgerBtn) {
  hamburgerBtn.addEventListener('click', () => {
    menuOpen = !menuOpen;
    mobileMenu.classList.toggle('open', menuOpen);
    iconOpen.classList.toggle('hidden', menuOpen);
    iconClose.classList.toggle('hidden', !menuOpen);
  });
}

/**
 * Called by onclick on each mobile nav link.
 * Closes the menu after a link is tapped.
 */
function closeMobileMenu() {
  menuOpen = false;
  if (mobileMenu)  mobileMenu.classList.remove('open');
  if (iconOpen)    iconOpen.classList.remove('hidden');
  if (iconClose)   iconClose.classList.add('hidden');
}


/* ═══════════════════════════════════════════════
   2. SCROLL REVEAL
   Every element with class .reveal starts hidden.
   IntersectionObserver adds .visible when the
   element enters the viewport.
════════════════════════════════════════════════ */
const revealObserver = new IntersectionObserver(
  (entries) => {
    entries.forEach(entry => {
      if (entry.isIntersecting) {
        entry.target.classList.add('visible');
        revealObserver.unobserve(entry.target); // animate only once
      }
    });
  },
  {
    threshold:   0.12,
    rootMargin: '0px 0px -40px 0px'
  }
);

document.querySelectorAll('.reveal').forEach(el => {
  revealObserver.observe(el);
});


/* ═══════════════════════════════════════════════
   3. ACTIVE NAV LINK on scroll (single-page only)
   Only runs if there are <section id="..."> elements
   and matching nav links with href="#id".
   On multi-page layout this is replaced by
   Thymeleaf's th:classappend in the nav fragment.
════════════════════════════════════════════════ */
const sections = document.querySelectorAll('section[id]');
const navLinks = document.querySelectorAll('a.nav-link');

if (sections.length > 0 && navLinks.length > 0) {
  const navObserver = new IntersectionObserver(
    (entries) => {
      entries.forEach(entry => {
        if (entry.isIntersecting) {
          navLinks.forEach(link => {
            link.classList.remove('text-on-surface', '!text-primary');
            const href = link.getAttribute('href');
            if (href === '#' + entry.target.id) {
              link.classList.add('!text-primary');
            }
          });
        }
      });
    },
    { threshold: 0.5 }
  );

  sections.forEach(s => navObserver.observe(s));
}


/* ═══════════════════════════════════════════════
   4. HERO AUTO-REVEAL on load
   Staggers hero .reveal elements with a small
   timeout so they animate immediately on arrival.
════════════════════════════════════════════════ */
window.addEventListener('load', () => {
  document.querySelectorAll('#hero .reveal').forEach((el, i) => {
    setTimeout(() => el.classList.add('visible'), i * 120);
  });
});
