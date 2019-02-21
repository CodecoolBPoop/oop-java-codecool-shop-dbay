
const plusNumber = (target) => {
    let row = target.parentNode.parentNode;
    let input = row.childNodes[5].childNodes[1];
    let value = isNaN(input.value) ? 1 : input.value;
    ++value;
    input.value = value;
};

const minusNumber = (target) => {
    let row = target.parentNode.parentNode;
    let input = row.childNodes[5].childNodes[1];
    let value = isNaN(input.value) ? 1 : input.value;
    if (input.value > 1) {
        --value;
        input.value = value;
    }
};

const addClickEventListener = () => {
    let btns = document.querySelectorAll(".btn-plus");
    for (let btn of btns) {
        btn.addEventListener("click", e => {
            plusNumber(e.currentTarget);
        })
    }
    btns = document.querySelectorAll(".btn-minus");
    for (let btn of btns) {
        btn.addEventListener("click", evt => {
            minusNumber(evt.currentTarget)
        })
    }

};

addClickEventListener();


