var list_group = $('.memberbox');
var no = location.href.split("?")[1].split("=")[1];
console.log(no);
var i = 1;

$(document).ready(function() {
	list();
    var no = -1;
     $.get("json/auth/loginstat", {}, res=> {
         console.log("[" + res + "]" + ", " + typeof(res));
         console.log(res.no);
         no = res.no;
     }).done(function(data){
        console.log(data);
        
        $.get("json/joinedMember/"+no, (data) => {
           let mystudy = $('.mystudy');
           let ownstudy = $('.ownstudy');
           for(var item of data) {
              console.log(item)
              
              if (item.grade == 0) {
              $('<div>'+
                    '<span class="gg">'+ item.study.category +' 스터디 </span><a href="groupMain.html">\''+ item.study.name +'\'</a>'+
                    '<button class="btn btn-secondary btn-sm ttbtn" data-toggle="modal" data-target="#tmodal">탈퇴</button>'+
                    '<a class="identifyingClass" data-toggle="modal" href="#dAD" data-id="my_id_value"><button class="btn btn-primary btn-sm review">후기 작성</button></a>'+
              '</div>').appendTo(mystudy);
              } else if (item.grade == 1) {
                 $('<div>'+
                        '<span class="gg">'+ item.study.category +' 스터디 </span><a href="groupMain.html">\''+ item.study.name +'\'</a>'+
                        '<button class="btn btn-secondary btn-sm ttbtn" data-toggle="modal" data-target="#tmodal">탈퇴</button>'+
                  '</div>').appendTo(ownstudy);
              }
           }
           
           $("#addBtn").click(() => {
                $.ajax({
                   type : 'POST',
                   url : 'json/review/add',
                   data: {
                      category : 'IT',
                      content: $(fContent).val(),
                      rating : 3,
                      "member.no": 6,
                      "study.no" : 29
                      /* category: 'IT',
                      rating : 3,
                      studyNo : 29,
                      memNo : memno */
                   },
                   
                   success:function(result){
                   location.href = "Mystudy.html";
                }
                });
              });
          
        
     });
        
        

  });
});





$(document).ready(function() {
	
});

function list(){
	
	
	$.ajax({
		url: "/FinalProject/json/joinedMember/list/?no=" + no,
		method : "post",
		data:{},
		dataType:"json",
		success: function(data){
			
			console.log("list");
			let list_group = $('.memberbox');
	        
	        for(var item of data) {
	            $('<tr>' +
	            		'<form>'+
	                    '<td>'+item.member.name+'</td>'+
	                    '<td>'+item.member.email+'</td>'+
	                    '<td>'+item.registedDate+'</td>'+
	            '<td>'+
	            '<button onclick="dep('+item.memNo+');">추방</button>'+
	            '</td>'+
	            '</form>'+
	            '</tr>').appendTo(list_group);
	            
	            i++;
	          //console.log(JSON.stringify(item));
	        };
		}
	});
};

function dep(memno) {
	console.log(memno);
	
	if(confirm('회원을 추방하시겠습니까?')){
		
		$.ajax({
			url: "/FinalProject/json/joinedMember/update/?no=" +no,
			method: "POST",
			data:{'memNo':memno},
			dataType: "json",
			success: function(data){
				if( data ) { 
					console.log(JSON.stringify(data));
					console.log("성공")
					$('.memberbox').empty();
					list();
				} else {
					console.log('업데이트 실패');
				}
			
			}
		});
		
	}
};

