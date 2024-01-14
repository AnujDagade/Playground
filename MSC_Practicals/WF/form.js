const submit = document.querySelector('form')


submit.addEventListener('submit',(event)=>{
    event.preventDefault();
    const select = document.querySelector('select').value
    const gender = document.querySelector('input[name=gender]:checked')
    console.log(gender)
})