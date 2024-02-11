const MongoClient = require('mongodb').MongoClient;

// Connection URL
//const url = 'mongodb://localhost:27017';

// Connection URL
//const url = 'mongodb://username:password@localhost:27017';
// Database Name
const foo = async () => {
    let users = []
  
    const URI = url
   
    const client = new MongoClient(
      URI
    )
    let conn = null

    try {
      client.connect()
      conn = client.db("Movie")
      const ngo = conn.collection("Film")
      const result = ngo.find()
      for await (doc of result) {
        users.push(doc)
        console.log(users);
      }
    } catch (error) {
      console.error(error)
    }
    finally{
      client.close()
    }
};

foo();