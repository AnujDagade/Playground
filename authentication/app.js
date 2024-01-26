const button = document.querySelector('button')
const inputs = document.querySelectorAll('input')


function getFormData() {
    let formData = {}

    for (const ele of inputs) {
        formData[ele.id] = ele.value
    }

    return formData;
}
function validateFormData(formData) {
    const emailRegEx = /[a-z]+@[a-z]+\.[a-z]+/i
    let err = {}

    if (!emailRegEx.test(formData.email)) {

        err['email'] = "Invalid email"
    }
    if (!(/[a-z]+/i).test(formData.name) || formData.name.length == 0) {
        err['name'] = "Invalid name"
    }

    return err
}


button.addEventListener('click', (event) => {
    event.preventDefault()
    const errs = validateFormData(getFormData())

    if (!errs) {
        console.log("form is correct");
    } else {
        for (const err in errs) {
            console.log(errs[err]);
        }
        const errEle = document.createElement('div')
        errEle.className = "err"
        const errCon = document.querySelector(".errCon")
        errCon.appendChild(errEle)
        
        setTimeout(()=> errCon.removeChild(errEle),5000)
    }

})


