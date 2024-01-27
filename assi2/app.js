

const fileNameInputs = document.querySelectorAll("input")
const submitButton = document.querySelector("button")

let fileNames = {}



submitButton.addEventListener("click", async (event) => {
    event.preventDefault()
    for (const fileName of fileNameInputs) {
        fileNames[fileName.id] = fileName.value
    }
    console.log(fileNames);



    try {
        const response = await fetch('https://laughing-palm-tree-7p6jqr6q5943p6gx-8080.app.github.dev/merge', {
            method: "POST",
            body: JSON.stringify(fileNames),
            headers: {
                'Content-Type': 'application/json'
            }
        })

        const data = await response.text()
        console.log("Data", data)
    } catch (error) {
        console.error(error)
    }
})