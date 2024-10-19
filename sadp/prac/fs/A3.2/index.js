const mongoose = require('mongoose');
const url='mongodb://192.168.100.203/DS';
mongoose.connect(url);

const studentModel=mongoose.model('students',mongoose.Schema({rollno:Number,name:String,div:String}));

const records=[
{rollno:1,name:"Adarsh",div:'A'},
{rollno:2,name:"Divya",div:'A'},
{rollno:3,name:"Shreyas",div:'B'},
{rollno:4,name:"Sonu",div:'B'},
{rollno:5,name:"Ketan",div:'C'}];

studentModel.insertMany(records,(err,data)=>{
	if(err)
		console.log(err);
	else{
		console.log("Records inserted successfully!");
		studentModel.find({},(err,data)=>{
			if(err) console.log(err);
			else console.log(data);
		}).limit(5);
		mongoose.connection.close(()=>{
			console.log("Execution Completed! Exiting..");
			process.exit(0);
		})
	}
});
