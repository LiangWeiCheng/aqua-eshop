
var highlightcolor = '#d5f4fe';
var clickcolor='#51b2f6';//此处clickcolor只能用win系统颜色代码才能成功,如果用#xxxxxx的代码就不行,还没搞清楚为什么:(

function changeto(){
	source = event.srcElement;
	if(source.tagName=="TR"||source.tagName=="TABLE"){
		return false;
	}
	while(source.tagName != "TD"){
		source = source.parentElement;
	}
	source = source.parentElement;
	cs = source.children;
	//alert(cs.length);
	if(cs[1].style.backgroundColor!=highlightcolor&&source.id!="nc"&&cs[1].style.backgroundColor!=clickcolor){
		for(i=0;i<cs.length;i++){
			cs[i].style.backgroundColor=highlightcolor;
		}
	}
}

function changeback(){
	source = event.srcElement;
	if(event.fromElement.contains(event.toElement)||source.contains(event.toElement)||source.id=="nc"){
		return false;
	}
	if(event.toElement!=source&&cs[1].style.backgroundColor!=clickcolor){
		//source.style.backgroundColor=originalcolor
		for(i=0;i<cs.length;i++){
			cs[i].style.backgroundColor="";
		}
	}
}

function clickto(){
	source = event.srcElement;
	if(source.tagName=="TR"||source.tagName=="TABLE"){
		return;
	}
	while(source.tagName!="TD"){
		source = source.parentElement;
	}
	source = source.parentElement;
	cs = source.children;
	//alert(cs.length);
	if(cs[1].style.backgroundColor!=clickcolor&&source.id!="nc"){
		for(i=0;i<cs.length;i++){
			cs[i].style.backgroundColor=clickcolor;
		}
	}else{
		for(i=0;i<cs.length;i++){
			cs[i].style.backgroundColor="";
		}
	}
}

function toPage(url){
	var toPageValue = document.getElementById("toPage").value;
	if(toPageValue!=""){
		window.location.href = url + '?queryResult.currentPage=' + toPageValue;
	}
}
