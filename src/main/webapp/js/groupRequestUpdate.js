var list_group = $('.requestbox');

var i = 1;


$(document).ready(function() {
	var no = location.href.split("?")[1].split("=")[1];
	$("#header").load(serverRoot + "/header.html");
	
	$("#groupInfoUpdate").attr("href", "groupInfoUpdate.html?no="+no);
	$("#groupMemberUpdate").attr("href", "groupMemberUpdate.html?no="+no);
	$("#groupRequestUpdate").attr("href", "groupRequestUpdate.html?no="+no);
	$("#groupSurveylist").attr("href", "surveylist.html?no="+no);
	$("#groupSurveyInsert").attr("href", "survey-enroll.html?no="+no);
	
	$.get(serverRoot + "/json/auth/loginstat", (data) => {
	       if(typeof(data) == "string") {
	    	   swal({
					  type: 'error',
					  title:  '권한이 없습니다.'
					})
	           location.href="index.html";
	       }
	       myNo = data.no;
	    });  
	
	rList();
});

function rList(){
	var no = location.href.split("?")[1].split("=")[1];
	$.ajax({
		url: "/FinalProject/json/awaitingMember/list/?no=" + no,
		method : "post",
		data:{},
		dataType:"json",
		success: function(data){
			var no = location.href.split("?")[1].split("=")[1];
			console.log("no : ", no);
			console.log("rList");
			let list_group = $('.requestbox');
	     
	        for(var item of data) {
	            $('<tr>' +
	            		'<form>'+
	                    '<td>'+item.member.name+'</td>'+
	                    '<td>'+item.member.email+'</td>'+
	                    '<td>'+item.requestDate+'</td>'+
	            '<td>'+
	            '<button class="btn btn-sm" style="margin-right: 1em;" onclick="acceptRequest('+item.memNo+', 2);">승낙</button>'+
	            '<button class="btn btn-secondary btn-sm" onclick="acceptRequest('+item.memNo+', 1);">거절</button>'+
	            '</td>'+
	            '</form>'+
	            '</tr>').appendTo(list_group);
	            
	            i++;
	        };
		}
	});
};

function acceptRequest(memno, type) {
	var no = location.href.split("?")[1].split("=")[1];
	console.log("memno : " + memno);
	console.log("type : " + type);
	console.log("no : " + no);
	var consoleMsg = '거절';
	
	const swalWithBootstrapButtons = swal.mixin({
        confirmButtonClass: 'btn btn-primary oBtn',
        cancelButtonClass: 'btn btn-secondary cBtn',
        buttonsStyling: false,
      })
      swalWithBootstrapButtons({
           title: '가입신청을 승인하시겠습니까?',
           type: 'question',
           showCancelButton: true,
           confirmButtonText: '네!',
           cancelButtonText: '아니요!',
           reverseButtons: true
         }).then((result) => {
        	 if (result.value) {
		$.ajax({
			url: "/FinalProject/json/awaitingMember/acceptRequest",
			method: "POST",
			data:{'memNo':memno, 'type' : type, 'no' : no},
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
		 swalWithBootstrapButtons({
             title: '승인 완료!',
               type: 'success'
             }).then(function(){
            	 reload();
             }
             )
           }  
         })
}

// location.href="/FinalProject/dhGM.html?no="+data <- studyNo
