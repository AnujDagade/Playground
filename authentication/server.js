const http = require('http')
const fs = require('fs')
const { log } = require('console')
const url = require('url')
http.createServer((req, res) => {

    if (req.url == "/") {
        const html = fs.readFileSync("./index.html")
        log(html.toString());
        res.write(html)
        res.end()
    } else {
        try {
            const file = fs.readFileSync(`.${req.url}`)
            res.write(file)
        } catch (error) {
            res.write(`${error}`)

        } finally {
            res.end();
        }
       
    }



}).listen(8080, (err) => { if (err) console.log(err) })