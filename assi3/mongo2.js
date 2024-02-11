const MongoClient = require('mongodb').MongoClient;

// Connection URL
const url = 'mongodb://localhost:27017';

// Database Name
const dbName = 'mydatabase';

// Create a new MongoClient
const client = new MongoClient(url);

// Use connect method to connect to the Server
client.connect(function(err) {
    if (err) {
        console.error('Error connecting to the database:', err);
        return;
    }

    console.log('Connected successfully to the database');

    const db = client.db(dbName);

    // Get the "student" collection
    const collection = db.collection('student');

    // Array of student records to be inserted
    const students = [
        { name: 'John Doe', age: 20, grade: 'A' },
        { name: 'Jane Smith', age: 22, grade: 'B' },
        { name: 'Mike Johnson', age: 21, grade: 'A+' }
    ];

    // Insert the student records
    collection.insertMany(students, function(err, result) {
        if (err) {
            console.error('Error inserting records:', err);
            return;
        }

        console.log('Inserted', result.insertedCount, 'records into the "student" table');
        console.log('Result object:', result);

        // Close the database connection
        client.close();
    });
});
