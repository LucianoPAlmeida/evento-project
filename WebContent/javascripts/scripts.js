/**
 * 
 

*/
function mapPageLoad(){
	document.getElementById("j_idt5:upload").style.visibility="hidden";
	document.getElementById("j_idt5:bSubmit").style.visibility="hidden";
	document.getElementById("j_idt5:bAlterar").style.visibility="hidden";
	document.getElementById("j_idt5:addButton").style.visibility="hidden";
}

function deletar() {
	return window.confirm("Confirmar Exclusão?");
}
function clickOnCarregar(){
	document.getElementById("j_idt5:upload").style.visibility = "visible";
	document.getElementById("j_idt5:bCarregar").style.visibility="hidden";
	document.getElementById("j_idt5:bSubmit").style.visibility="visible";
	return false;
}
function clickOnAlterarMapa(){
	document.getElementById("j_idt5:upload").style.visibility = "visible";
	document.getElementById("j_idt5:bAlterar").style.visibility="visible";
	document.getElementById("j_idt5:alterarMapa").style.visibility="hidden";
	return false;
}
function alterar(){
	var element = document.getElementById("j_idt5:upload");
	if(!element.value){
		alert("Para alterar deverá ser selecionado um arquivo!");
		return false;
	}
	return confirm('Alterar o mapa irá excluir todos os pontos marcados\nContinuar?');
}
function cadastrarMapa(){
	var element = document.getElementById("j_idt5:upload");
	if(!element.value){
		alert("Para alterar deverá ser selecionado um arquivo!");
		return false;
	}
}
function getClickPosition(e) {
    var parentPosition = getPosition(e.currentTarget);
    var xPosition = e.clientX - parentPosition.x;
    var yPosition = e.clientY - parentPosition.y;
    var lista = document.getElementById("j_idt5:listaEventos");
    if(lista){
    	document.getElementById("j_idt5:points").value = "{\"x\":\""+xPosition+"\",\"y\":\""+yPosition+"\"}";
        document.getElementById("j_idt5:addButton").style.visibility="visible";
    }else{
    	document.getElementById("j_idt5:bDeletarPonto").style.visibility = "hidden";
    	alert("Não existe nenhum evento não mapeado!");
    }
    return true;
}

function getPosition(element) {
    var xPosition = 0;
    var yPosition = 0;
      
    while (element) {
        xPosition += (element.offsetLeft - element.scrollLeft + element.clientLeft);
        yPosition += (element.offsetTop - element.scrollTop + element.clientTop);
        element = element.offsetParent;
    }
    return { x: xPosition, y: yPosition };
}

function clickOnPoint(e) {
	var element = e.currentTarget;
	var campoDelete = document.getElementById("j_idt5:pointToDelete");
	campoDelete.value = element.title;
	var bDelete = document.getElementById("j_idt5:bDeletarPonto");
	bDelete.style.marginTop = (parseInt(element.style.marginTop)+20)+"px";
	bDelete.style.marginLeft = (parseInt(element.style.marginLeft)+20)+"px"
	bDelete.style.visibility = "visible";
	return false;
}
function clickOnPointDeleteButton() {
	var ret = confirm("Tem certeza que deseja desmarcar este ponto?");
	if(ret){
		document.getElementById("j_idt5:bDeletarPonto").style.visibility = "hideen";
	}
	return ret;
	
}
function mouseOnLabel(e) {
	var element = e.currentTarget;
	element.style.color = "#0099FF";
}

function mouseOutLabel(e) {
	var element = e.currentTarget;
	element.style.color = "#686868";
}

function mouseOnMenuButton(e) {
	var element = e.currentTarget;
	element.style.backgroundColor = "#0099FF";
	element.style.color = "#FFFFFF";

}
function mouseOutMenuButton(e) {
	var element = e.currentTarget;
	element.style.backgroundColor = "#FFFFFF";
	element.style.color = "#686868";
	
}
function clickOnDownLoad() {
	document.getElementById("j_idt5:msgErro").style.visibility = "hidden";
}
function verificarEstadoTipoEvento(){
	var element = document.getElementById("j_idt19:tiposEvento");
	var value = element.options[element.selectedIndex].text;
	if(value == "Palestra"){
		var select = document.getElementById("j_idt19:selectPeriodo"); 
		select.selectedIndex = 0;
		document.getElementById("j_idt19:selectPeriodo").removeAttribute("disabled");
		var i;
		for(i = 0; i < select.length; i++){
			var option = select.options[i];
			if(option.value == 'TARDE'){
				option.setAttribute("disabled", "disabled");
			}
		}
	}else if(value == "Workshop"){
		var elem = document.getElementById("j_idt19:selectPeriodo");
		elem.selectedIndex = 1;
		elem.setAttribute("disabled", "disabled");
	}
	
}
function habilitarTodosItensSelectPeriodo(){
	document.getElementById("j_idt19:selectPeriodo").removeAttribute("disabled");
	var select = document.getElementById("j_idt19:selectPeriodo"); 
	var i;
	for(i = 0; i < select.length; i++){
		var option = select.options[i];
		option.removeAttribute("disabled");
	}
}

