
var oTbl;
//Row 추가
function insRow() {
  oTbl = document.getElementById("addTable");
  var oRow = oTbl.insertRow();
  oRow.onmouseover=function(){oTbl.clickedRowIndex=this.rowIndex}; //clickedRowIndex - 클릭한 Row의 위치를 확인;
  var oCell = oRow.insertCell();

  //삽입될 Form Tag
  var frmTag = "<input type='text' class='form-control list-form' id='exampleInputEmail1' placeholder='항목을 입력해주세요' style='width:90%; float:left'>";
  frmTag += "<input type='button' class='fas fa-minus-circle fa-1x' onClick='removeRow()' style='float:right';>";
 
  oCell.innerHTML = frmTag;
}
//Row 삭제
function removeRow() {
  oTbl.deleteRow(oTbl.clickedRowIndex);
}

function frmCheck()
{
  var frm = document.form;
  
  for( var i = 0; i <= frm.elements.length - 1; i++ ){
     if( frm.elements[i].name == "addText" )
     {
         if( !frm.elements[i].value ){
             alert("텍스트박스에 값을 입력하세요!");
                 frm.elements[i].focus();
	 return;
          }
      }
   }
 }
