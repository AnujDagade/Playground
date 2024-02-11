const express = require('express');
const session = require('express-session');
const app = express();

// Set view engine to EJS
app.set('view engine', 'ejs');

// Use express built-in middleware to parse form data
app.use(express.urlencoded({ extended: false }));

// Set up express-session
app.use(session({
    secret: 'secret',
    resave: true,
    saveUninitialized: true
}));

// Routes
app.get('/', (req, res) => {
    res.render('login');
});

app.post('/auth', (req, res) => {
    const username = req.body.username;
    const password = req.body.password;

    // For simplicity, we're using a hardcoded username and password
    // In a real application, you should check these against a database
    if (username && password) {
        if (username === 'admin' && password === 'password') {
            req.session.loggedin = true;
            req.session.username = username;
            res.redirect('/home');
        } else {
            res.send('Incorrect Username and/or Password!');
        }
    } else {
        res.send('Please enter Username and Password!');
    }
});

app.get('/home', (req, res) => {
    if (req.session.loggedin) {
        res.send('Welcome back, ' + req.session.username + '!');
    } else {
        res.send('Please login to view this page!');
    }
});

app.listen(3000, () => {
    console.log('Server started on port 3000');
});