const http = require('http')
const fs = require('fs')
const { log } = require('console')
const url = require('url')
http.createServer((req, res) => {
	res.setHeader('Access-Control-Allow-Origin', '*');
	res.setHeader('Access-Control-Allow-Methods', 'GET, POST, PUT, DELETE');
    res.setHeader('Access-Control-Allow-Headers', 'Content-Type');
	if (req.url == "/merge" && req.method == "POST") {
		log(req.url)
		let body = ""
		req.on("data", (chunk) => {
			body += chunk.toString()
		})

		req.on("end", () => {
			log("BODY",body)
		})
	}
	res.write("hi")
	res.end()

}).listen(8080, (err) => { if (err) console.log(err) })