$("#header").load("header.html");
var studyName = document.querySelectorAll(".studyName");
var stdno = location.href.split("?")[1].split("=")[1];

$(document).ready(function() {
	$(".groupInfoUpdate").click(function(){
		location.href =  "groupInfoUpdate.html?no="+stdno;
	});
	
    $.get("json/auth/loginstat", {}, res=> {
    	memno = res.no;
    }).done(function(data){
    	$.getJSON("json/studyInfo/" + stdno, function(data) {
            $(studyName).text(data.name);
            $(fInformation).text(data.information);
        }); 
    });
    // 가입요청하기 기능
    $(".acceptRequest").click(function(memno, stdno){
	    $.get("json/auth/loginstat", {}, res=> {
	    	memno = res.no;
	    }).done(function(data){
	
	        var consoleMsg = '가입요청';
	        var _msg = '가입 요청을 하시겠습니까??';
	        
	        if (confirm(_msg)) {
	        	if(memno == undefined){
	        		alert("로그인 후 이용하실 수 있습니다.");  
	        	} else{
		            $.ajax({
		                url : "/FinalProject/json/awaitingMember/add",
		                method : "POST",
		                data : {'memNo' : memno, 'no' : stdno},
		                dataType : "json",
		                success : function(data) {console.log(consoleMsg);}
		            })
	        	}
	        };
        });
	});

	$.get("json/auth/loginstat", {}, res=> {
		memNo = res.no;
	}).done(function(data){
	    console.log("memNo :"+ memNo);
	    console.log("no :" + stdno);
	    
	    $.ajax({
	        url : "/FinalProject/json/joinedMember/gradeList",
	        method : "POST",
	        data : {'memNo': memNo, 'no' : stdno},
	        dataType : "json",
	        success : function(data) {
	        	console.log("item : " + item);
	        	if(item == undefined){
	        		$("#grade2").css('display','block');
	        	} else{
		        	for(var item of data){
		        		console.log("item.grade : " + item.grade);
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
	        	}
	        }
	    })
	})
});
