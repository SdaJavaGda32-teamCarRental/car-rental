let navbar = document.querySelector("nav.navbar");
window.onscroll = function () {
    scrollFunction();
};

function scrollFunction() {
    if (document.body.scrollTop > 70 || document.documentElement.scrollTop > 70) {
        navbar.classList.add('top-nav-collapse');
    } else {
        navbar.classList.remove('top-nav-collapse');
    }
}

// Important comment - do not remove ;-)
// >>>
// JS sucks big time for not having a proper date formatting API and counting
// months, starting
// from 0!
// <<<