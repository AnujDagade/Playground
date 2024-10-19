const mongoose = require('mongoose');
const url='mongodb://192.168.100.203/DS';
mongoose.connect(url);

const customerModel = mongoose.model('customers', mongoose.Schema({name:String,addr:String,number:Number}));

const myquery={addr:"pune"};

customerModel.deleteMany(myquery,(err,data)=>{
	if(err)
		console.log(err);
	else{
		console.log("Records with addr Pune removed successfully!");
		customerModel.find({},(err,data)=>{
			if(err) console.log(err);
			else console.log(data);
		});
		mongoose.connection.close(()=>{
			console.log("Exiting..");
			process.exit(0);
		})
	}
});
