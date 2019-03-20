
function init() {
    initialiseDropdown();
    //initialiseAddToCartBtn();
    //initialiseCartNumber();
    //wipeLocalStorage();
    registerButton();
    loginButton();
}
///// LOGIN/REGISTER /////
function registerButton()
{
    let btn = document.getElementsByClassName('btn-dark')[1];
    btn.addEventListener('click', function () {
        let button = document.getElementById('login-register-btn');
        button.innerText = 'Register';
        let username = document.getElementById('exampleInputUsername1');
        username.classList.remove('display-off');
        username.classList.add('display-on');
    });
}

function loginButton()
{
    let username = document.getElementById('exampleInputUsername1');
    username.classList.add('display-off');
    let btn = document.getElementsByClassName('btn-dark')[0];
    btn.addEventListener('click', function () {
        let button = document.getElementById('login-register-btn');
        button.innerText = 'Login';
        let username = document.getElementById('exampleInputUsername1');
        username.classList.remove('display-on');
        username.classList.add('display-off');
    });
}

///// CARD NUMBER /////
function initialiseCartNumber() {
    const number = document.getElementById("shopping-cart-number");
    if (window.localStorage.getItem("cartNumber") === null) {
        window.localStorage.setItem("cartNumber", "");
    }
    if (number.textContent !== null) {
        number.textContent = window.localStorage.getItem("cartNumber");
    }
}
function wipeLocalStorage() {
    window.localStorage.clear();
    const number = document.getElementById("shopping-cart-number");
    number.textContent = '';
}

///// SHOPPING CART /////
function initialiseAddToCartBtn() {
    const addToCartBtns = document.getElementsByClassName("card-btn");
    if (addToCartBtns !== null) {
        for(const cartBtn of addToCartBtns) {
            cartBtn.addEventListener('click', function (e) {
                addQuantityToCart(e.currentTarget);
            })
        }
    }
}

function addQuantityToCart(btn) {
    const select = btn.nextElementSibling;
    const quantityNumber = select.options[select.selectedIndex].text;
    let cartNumber = document.getElementById("shopping-cart-number");
    if (cartNumber.textContent === null || cartNumber.textContent === '') {
        cartNumber.textContent = '0';
    }
    let newNumber = Number(cartNumber.innerText) + Number(quantityNumber);
    window.localStorage.setItem('cartNumber', String(newNumber));
    cartNumber.innerText = window.localStorage.getItem('cartNumber');

    // sendDataToServer(btn, quantityNumber);
}
//
// function sendDataToServer(btn, quantity) {
    // const price = btn.parentElement.closest('div').previousElementSibling.firstElementChild.textContent;
//     const name = btn.parentElement.closest('div').previousElementSibling.parentElement.previousElementSibling.firstElementChild.textContent;
//     const cookie = document.cookie;
//     console.log(cookie.substring(11));
//     console.log(cookie.substring(11, 42));
//     // let xhr = new XMLHttpRequest();
//     // xhr.open("POST", '/shopping-cart', true);
//     // xhr.setRequestHeader('Content-Type', 'application/json');
//     // xhr.send(JSON.stringify({
//     //     addProduct: btn.dataset.id,
//     //     sessionId: cookie.substring(11, 42)
//     // }));
//     // event.stopPropagation();
//     fetch("/shopping-cart", {
//         method: "POST",
//         headers: {
//             "Accept": "application/json",
//             "Content-Type": "application/json"
//         },
//         body: JSON.stringify({
//             addProduct: btn.dataset.id}),
//         credentials: "same-origin"
//     })
// }


///// DROPDOWN /////
function initialiseDropdown() {
    const options = document.getElementById("filter-supplier");
    if (options !== null) {
        options.addEventListener('change', function () {
            filterElements(this.value);
        })
    }
}

function filterElements(element) {
    const allCards = document.getElementsByClassName("col col-sm-12 col-md-6 col-lg-4");
    if (element === "All") {
        for (let card of allCards) {
            card.classList.remove('display-off');
            card.classList.add('display-on');
        }
    } else {
        for (let card of allCards) {
            if (card.dataset.name !== element) {
                card.classList.remove('display-on');
                card.classList.add('display-off');
            } else {
                card.classList.remove('display-off');
                card.classList.add('display-on');
            }
        }
    }
}

init();