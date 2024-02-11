const MongoClient = require('mongodb').MongoClient;

// Connection URL
const url = 'mongodb://localhost:27017';
// Connection URL
//const url = 'mongodb://username:password@localhost:27017';
// Database Name
const dbName = 'mydatabase';

// Create a new MongoClient
const client = new MongoClient(url);

// Use connect method to connect to the Server
client.connect(function(err) {
    if (err) {
        console.error('Error connecting to MongoDB:', err);
        return;
    }

    console.log('Connected successfully to MongoDB');

    const db = client.db(dbName);

    // Get the customers collection
    const collection = db.collection('customers');

    // Find all documents in the customers collection
    collection.find({}).toArray(function(err, result) {
        if (err) {
            console.error('Error retrieving documents:', err);
            return;
        }

        console.log('All records from the "customers" table:');
        console.log(result);
    });

    // Close the connection
    client.close();
});
