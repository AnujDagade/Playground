const express = require('express');
const path = require('path');
const app = express();

app.get('/download', (req, res) => {
    // Define the path to the file you want to download
    // Here we assume the file is in a directory named "files" at the root of your project
    const filePath = path.join(__dirname, 'files', 'file.txt');

    // Use the res.download() function to send the file
    res.download(filePath, (err) => {
        if (err) {
            console.log(err);
            res.status(500).send('File not found!');
        }
    });
});

app.listen(3000, () => {
    console.log('Server started on port 3000');
});