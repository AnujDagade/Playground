module.exports=function currentDate(){
	var date=new Date();
	var d=date.toDateString();
	var time=date.toLocaleTimeString();
	var myDate="Date : "+d+" Time : "+time;
	return myDate;
}
