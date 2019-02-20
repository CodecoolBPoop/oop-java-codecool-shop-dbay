
function init() {
    initialiseDropdown();
    initialiseAddToCartBtn();
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
    if (cartNumber.textContent === null) {
        cartNumber.textContent = '0';
    }
    let newNumber = Number(cartNumber.innerText) + Number(quantityNumber);
    cartNumber.innerText = String(newNumber);
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