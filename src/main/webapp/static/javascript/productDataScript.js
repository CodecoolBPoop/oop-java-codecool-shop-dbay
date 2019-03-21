
function init() {
    initialiseDropdown();
    initialiseCartNumber();
    registerButton();
    loginButton();
    loginAndRegister();
    logoutButton();
}

///// LOGIN/REGISTER/LOGOUT /////
function logoutButton() {
    const btn = document.getElementById('logout');
    btn.addEventListener('click', function () {
        $.ajax({
            method: "GET",
            url: "/logout",
            data: "logout"
        })
            .done(function() {
                location.reload();
            })
            .fail(function(xhr, status, error) {
                console.log(error);
            });
    })
}

function registerButton() {
    let btn = document.getElementsByClassName('btn-dark')[1];
    btn.addEventListener('click', function () {
        let button = document.getElementById('login-register-btn');
        button.innerText = 'Register';
        let username = document.getElementById('exampleInputUsername1');
        username.classList.remove('display-off');
        username.classList.add('display-on');
    });
}

function loginButton() {
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

function loginAndRegister() {
    const btn = document.getElementById('login-register-btn');
    btn.addEventListener('click',function () {
        const action = document.getElementById('login-register-btn').textContent;
        if (action === 'Register') {
            const username = document.getElementById('exampleInputUsername1').value;
            const email = document.getElementById('exampleInputEmail1').value;
            const password = document.getElementById('exampleInputPassword1').value;
            $.ajax({
                method: "POST",
                url: "/register",
                data: { username: username, email: email, password:password }
            })
                .done(function() {
                    location.reload();
                })
                .fail(function(xhr, status, error) {
                    const alert = document.getElementById('alert-register');
                    alert.classList.remove('display-off');
                    alert.classList.add('display-on');
                });
        } else {
            const email = document.getElementById('exampleInputEmail1').value;
            const password = document.getElementById('exampleInputPassword1').value;
            $.ajax({
                method: "POST",
                url: "/login",
                data: { email: email, password:password }
            })
                .done(function() {
                    location.reload();
                })
                .fail(function(xhr, status, error) {
                    const alert = document.getElementById('alert-login');
                    alert.classList.remove('display-off');
                    alert.classList.add('display-on');
                });
        }
    })
}

///// CARD NUMBER /////
function initialiseCartNumber() {
    window.addEventListener("load", getShoppingCartSize);
}

function getShoppingCartSize(){
    fetch("/shopping-cart-size", {
        method: "POST",
        mode: "same-origin",
        cache: "default",
        credentials: "include",
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then(response => response.json())
        .then(response => showCartSize(response.cartSize))
}

function showCartSize(shoppingCartSize){
    document.getElementById("shopping-cart-size").innerText = shoppingCartSize;
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
}

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