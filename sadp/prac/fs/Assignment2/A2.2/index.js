var http=require('http');
const mod=require('./module.js');

http.createServer(function(req,res){
	res.write(`<h2>Current Date and Time is : ${mod()}</h2>`);
	res.end();
}).listen(8080);
