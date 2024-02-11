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

    // Get the "customers" collection
    const collection = db.collection('customers');

    // Select all records
    collection.find({}).toArray(function(err, docs) {
        console.log('All records:');
        console.log(docs);

        // Specify the record to delete
        const query = { name: 'John Doe' };

        // Delete the specified record
        collection.deleteOne(query, function(err, result) {
            if (err) {
                console.error('Error deleting record:', err);
                return;
            }

            console.log('Record deleted successfully');
            client.close();
        });
    });
});