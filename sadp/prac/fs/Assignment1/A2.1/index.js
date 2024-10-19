const fs=require('fs');
const rl=require('readline').createInterface(process.stdin, process.stdout);

rl.question('Enter file 1 : ', (f1) => {
	console.log(f1);
	fs.readFile(f1,'utf8',(err,data)=>{
		if(err)
			throw err;
		rl.question('Enter file 2 : ',(f2)=>{
		fs.appendFile(f2,data,(err)=>{})
		console.log('Successful!');
		})
	});
});



