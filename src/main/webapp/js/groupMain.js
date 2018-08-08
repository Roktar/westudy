$("#header").load("header.html");
$(document).ready(function() {
	var studyName = document.querySelectorAll(".studyName");
	var no = location.href.split("?")[1].split("=")[1];
	
	$(".groupInfoUpdate").click(function(){
		location.href =  "groupInfoUpdate.html?no="+no;
	});
	
    console.log("studyNo : " + no);
    $.get("json/auth/loginstat", {}, res=> {
    	console.log("res.no : " + res.no);
    	memno = res.no;
    }).done(function(data){
    	$.getJSON("json/studyInfo/" + no, function(data) {
            $(studyName).text(data.name);
            $(fInformation).text(data.information);
        }); 
    });
    // 가입요청하기 기능
    $(".acceptRequest").click(function(memno, no){
	    $.get("json/auth/loginstat", {}, res=> {
	    	memno = res.no;
	    }).done(function(data){
	        var no = location.href.split("?")[1].split("=")[1];

	        console.log("memno :"+ memno);
	        console.log("no :" + no);
	
	        var consoleMsg = '가입요청';
	        var _msg = '가입 요청을 하시겠습니까??';
	        
	        if (confirm(_msg)) {
	            $.ajax({
	                url : "/FinalProject/json/awaitingMember/add",
	                method : "POST",
	                data : {'memNo' : memno, 'no' : no},
	                dataType : "json",
	                success : function(data) {console.log(consoleMsg);}
	            })
	        };
        });
	});
});

var i = 1;
$.get("json/auth/loginstat", {}, res=> {
	memno = res.no;
}).done(function(data){
	var no = location.href.split("?")[1].split("=")[1];

    console.log("btnmemno :"+ memno);
    console.log("btnno :" + no);
    
    $.ajax({
        url : "/FinalProject/json/joinedMember/gradeList/?no=" + no,
        method : "POST",
        data : {'memNo':memno, 'no' : no},
        dataType : "json",
        success : function(data) {
        	for(var item of data){
        		console.log("item.grade : " + item.grade);
        	}
        	switch(item.grade){
	        	case 0 :
	        		$("#grade0").css('display','block');
	        		console.log("그룹관리");
	        		break;
	        	case 1 :
	        		console.log("회원입니다");
	        		break;
	        	default :
	        		$("#grade2").css('display','block');
	        		console.log("그룹가입");
	        		break;
        	}
        }
    })
})

