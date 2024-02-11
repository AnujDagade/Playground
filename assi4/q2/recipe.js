const express = require('express');
const app = express();

// Set view engine to EJS
app.set('view engine', 'ejs');

// Use express built-in middleware to parse form data
app.use(express.urlencoded({ extended: false }));

// In-memory store for recipes
let recipes = [];

// Routes
app.get('/', (req, res) => {
    res.render('index', { recipes: recipes });
});

app.get('/new', (req, res) => {
    res.render('new');
});

app.post('/new', (req, res) => {
    const recipe = {
        title: req.body.title,
        ingredients: req.body.ingredients,
        instructions: req.body.instructions
    };
    recipes.push(recipe);
    res.redirect('/');
});

app.listen(3000, () => {
    console.log('Server started on port 3000');
});