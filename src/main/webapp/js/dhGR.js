var list_group = $('.requestbox');
var no = location.href.split("?")[1].split("=")[1];
console.log(no);
var i = 1;

$(document).ready(function() {
	rList();
});
function rList(){
	
	
	$.ajax({
		url: "/FinalProject/json/awaitingMember/list/?no=" + no,
		method : "post",
		data:{},
		dataType:"json",
		success: function(data){
			
			console.log("rList");
			let list_group = $('.requestbox');
	     
	        for(var item of data) {
	            $('<tr>' +
	            		'<form>'+
	                    '<td>'+item.member.name+'</td>'+
	                    '<td>'+item.member.email+'</td>'+
	                    '<td>'+item.requestDate+'</td>'+
	            '<td>'+
	            '<button onclick="acceptRequest('+item.memNo+', 1);">거절</button>'+
	            '<button onclick="acceptRequest('+item.memNo+', 2);">승낙</button>'+
	            '</td>'+
	            '</form>'+
	            '</tr>').appendTo(list_group);
	            
	            i++;
	        };
		}
	});
};

function acceptRequest(memno, type) {
	console.log(memno);
	var consoleMsg = '거절';
	var _msg = '요청을 거절하시겠습니까?';
	if( type == 2 ){
		_msg = '요청을 승인하시겠습니까?';
		consoleMsg = '승락';
	}
	
	if(confirm(_msg)){
		$.ajax({
			url: "/FinalProject/json/awaitingMember/acceptRequest",
			method: "POST",
			data:{'memNo':memno, 'type' : type},
			dataType: "json",
			success: function(data){
				if( data ) { 
					console.log(consoleMsg);
					$('.requestbox').empty();
					rList();
				} else {
					console.log('업데이트 실패');
				}
			}
		});
	}
};
