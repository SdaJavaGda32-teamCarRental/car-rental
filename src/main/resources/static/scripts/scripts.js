let navbar = document.querySelector("nav.navbar");
window.onscroll = function () {
    shrinkNavbarOnScroll();
};

function shrinkNavbarOnScroll() {
    if (document.body.scrollTop > 70 || document.documentElement.scrollTop > 70) {
        navbar.classList.add('top-nav-collapse');
    } else {
        navbar.classList.remove('top-nav-collapse');
    }
}

function setBlankPickupAndReturnSelectOptions() {
    document.getElementById('blank_pickup').selected = true;
    document.getElementById('blank_return').selected = true;
}

window.onload = () => {
    setCurrentDateAsPickupDate();
    setBlankPickupAndReturnSelectOptions();
    setReturnDateToMatchPickup();
}

let pickupDate = document.getElementById('pickup_date');

function setCurrentDateAsPickupDate() {
    let today = new Date();
    let month = today.getMonth() + 1;
    if (month < 10) {
        month = '0' + month
    }
    pickupDate.value = today.getFullYear() + '-' + month + '-' + today.getDate();
}

pickupDate.addEventListener('change', setReturnDateToMatchPickup);

function setReturnDateToMatchPickup() {
    let returnDatePicker = document.getElementById('return_date');
    returnDatePicker.value = pickupDate.value;
    returnDatePicker.min = pickupDate.value;
}
