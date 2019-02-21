function handleClickOnProceed(){
    let proceedButton = document.getElementById("proceed");
    proceedButton.addEventListener("click", checkout)
}

function checkout(){
    //Sends shipping info to server, proceeds to payment page
    let shippingInfo = getShippingInfo();

    fetch("/order", {
        method: 'post',
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        },
        credentials: 'same-origin',
        body: JSON.stringify(shippingInfo)
    }).then(function() {window.location.href = "/pay";});
}

function getShippingInfo(){
    let shippingInfo = {
        personalInfo: {
            firstName: document.getElementById("first-name").value,
            lastName: document.getElementById("last-name").value,
            email: document.getElementById("email").value,
            phoneNumber: document.getElementById("phone-number").value
        },
        billingAddress: {
            country: document.getElementById("billing-country").value,
            city: document.getElementById("billing-city").value,
            zipcode: document.getElementById("billing-zipcode").value,
            address: document.getElementById("billing-address").value
        },
        shippingAddress: {
            country: document.getElementById("shipping-country").value,
            city: document.getElementById("shipping-city").value,
            zipcode: document.getElementById("shipping-zipcode").value,
            address: document.getElementById("shipping-address").value
        }
    }

    return shippingInfo;
}

function main(){
    handleClickOnProceed();
}

main();
