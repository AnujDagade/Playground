// const http = require('http');
// const fs = require('fs');

// const server = http.createServer((req, res) => {
    
//     if(req.url === '/'){
//         res.writeHead(200, {'Content-Type': 'text/html'})
//         console.log(req.url);
//         res.write(fs.readFileSync("./index.html"));
//         res.end();
//     }
//     else if(req.url === '/data' && req.method === 'GET'){
//         console.log(req.url);
//         let body = '';
//         req.on('data', (data) => {
//             body += data.toString();
//         });

//         req.on('end', () => {
//             console.log(body);
//             res.end();
//         })

       

        
//     } else {
//         res.end()
//     }
// });

// server.listen(3000, () => {
//   console.log('Server is running on http://localhost:3000');
// });



