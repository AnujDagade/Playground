var mongoose = require('mongoose');
var url='mongodb://192.168.100.203/DS';
mongoose.connect(url);

var customerModel = mongoose.model('customers', mongoose.Schema({name:String,addr:String,number:Number}));
customerModel.find({},(err,data)=>{
	if(err)
		console.log(err);
	else{
		console.log(data);
		mongoose.connection.close(()=>{
			console.log("Execution Completed! Exiting...");
			process.exit(0);
		});
	}
});
