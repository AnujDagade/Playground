const http = require('http')
const fs = require('fs')
const { log } = require('console')
const url = require('url')
const mergeFile = require('./index')

http.createServer((req, res) => {
	res.setHeader('Access-Control-Allow-Origin', '*');
	res.setHeader('Access-Control-Allow-Methods', 'GET, POST, PUT, DELETE, OPTIONS');
	res.setHeader('Access-Control-Allow-Headers', 'Content-Type');
	if (req.url == "/merge" && req.method == "POST") {

		log(req.url)
		let body = ""
		req.on("data", (chunk) => {
			body += chunk.toString()
		})

		req.on("end", () => {
			const { file1, file2 } = JSON.parse(body)
			mergeFile(file1, file2).then((data) => {
				res.writeHead(200, { 'Content-Type': 'text/plain' })
				res.write(data)

			}).catch((err) => {
				res.write("Failed to get Merged Data")

			}).finally(() => {
				res.end()
			})
		})
	} else if (req.method == 'OPTIONS') {
		res.writeHead(200);
		res.end();
	}




}).listen(8080, (err) => { if (err) console.log(err) })