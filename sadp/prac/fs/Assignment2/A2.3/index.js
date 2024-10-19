const EventEmitter=require('events');
var eventEmitter=new EventEmitter();
const readLine=require('readline');

eventEmitter.on('hello',()=>console.log('Hey!'));
eventEmitter.on('bye',()=>console.log('Ok Bye!'));

readLine.emitKeypressEvents(process.stdin);
process.stdin.setRawMode(true);

process.stdin.on('keypress',(chunk,key)=>{
	if(key){
		if(key.name=='h') eventEmitter.emit('hello');
		if(key.name=='b') eventEmitter.emit('bye');
		if(key.name=='q') process.exit();	
	}
})
