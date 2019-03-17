//时间函数，调用函数。使html标签动态显示时间
function startTime()
{
	var date = new Date();
	var d=date.getDate();
	var mon=date.getMonth() + 1;
	var y=date.getFullYear();
	var h=date.getHours();
	var m=date.getMinutes();
	var s=date.getSeconds();
	m=checkTime(m)
	s=checkTime(s)
	$("#time_show").html(y+"年"+mon+"月"+d+"  "+h+":"+m+":"+s);
	t=setTimeout('startTime()',1000);//设定定时器，循环执行 
}

function checkTime(i)
{
	if (i<10) 
	{
	  	i="0" + i
	}
	return i
}