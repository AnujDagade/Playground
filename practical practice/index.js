const express = require('express');
const session = require('express-session');
const app = express();

app.set('view engine', 'ejs');
app.use(session({
    secret:'idgidhgidhgu'
}))

app.get('/', (req, res) => {
    let name ="Guest"
    if(req.session.log){
        name = req.session.username
    }
    res.render('home',{name})
});

app.get('/login', (req, res) => {
    req.session.username = 'admin';
    req.session.log = true;
    res.redirect('/');
})

app.get('/logout', (req, res) => {
    req.session.destroy();
    res.redirect('/');
})

app.listen(3000, () => {});