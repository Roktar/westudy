var studyName = document.querySelectorAll(".studyName");
let stdno = location.href.split("?")[1].split("=")[1];



	





$(document).ready(function() {
	$("#header").load("header.html");
	
	$("#surveylisttab").attr("href", "surveylisttab.html?no="+stdno);
	$("#calendartab").attr("href", "calendar.html?no="+stdno);
	$(".mainLink").attr("href", "groupMain.html?no="+stdno);
	$(".calLink").attr("href", "calendar.html?no="+stdno);
	$(".surveyLink").attr("href", "surveylisttab.html?no="+stdno);
	$(".albumLink").attr("href", "photoMain.html?no="+stdno);
	
	$(".groupInfoUpdate").click(function(){
		location.href =  "groupInfoUpdate.html?no="+stdno;
	});
    $.getJSON(serverRoot + "/json/studyInfo/survey/list?no="+stdno, {}, data => {
        let cnt = 0;

        for(let survey of data) {
            if(cnt == 2)
                return;
            
            let outerDiv = $("<div class='col-sm survey-' style='margin:1rem'>");
            let sDiv = $("<div class='survey'>");
            let span = $("<span class='pollTitle'>");
            $(span).text(survey.title);
            let br = $("<br>");
            
            $(sDiv).append(span);
            $(sDiv).append(br);
            $(sDiv).append(br);
            $(outerDiv).append(sDiv);

            let innerDiv = $("<div class='surveyInner'>");

            if(survey.answerNum == 1) {
                for(let radio of survey.items) {
                    let wrapDiv = $("<div class='custom-control custom-radio'>");
                    let radioBt = $("<input type='radio' id='c'" + radio.no + "' class='custom-control-input'>");
                    let label = $("<label class='custom-control-label' for='c" + radio.no + "'>");
                    $(label).text(radio.itemName);
                    $(wrapDiv).append(radioBt);
                    $(wrapDiv).append(label);
                    $(innerDiv).append(wrapDiv);
                }
            } else {
                for(let cb of survey.items) {
                    let wrapDiv = $("<div class='custom-control custom-checkbox'>");
                    let radioBt = $("<input type='checkbox' id='c'" + cb.no + "' class='custom-control-input'>");
                    let label = $("<label class='custom-control-label' for='c" + cb.no + "'>");
                    $(label).text(cb.itemName);
                    $(wrapDiv).append(radioBt);
                    $(wrapDiv).append(label);
                    $(innerDiv).append(wrapDiv);
                }
            }
            $(outerDiv).append(innerDiv);
            $("#surveyList").append(outerDiv);
            cnt++;
        }
    });
	
    $.get("json/auth/loginstat", {}, res=> {
    	var memno = res.no;
    }).done(function(data){
    	/* 스터디 정보 */
    	$.getJSON("json/studyInfo/" + stdno, function(data) {
            $(studyName).text(data.name);
            $(fInformation).text(data.information);
            $('.maxPeople').text(data.maxPeople);
        }); 
    	
    	/* 리더정보 가져오기 */
    	 $.getJSON("json/joinedMember/leaderList", {no: stdno}, data =>{
      		  console.log(data);
      		 console.log(data.member.name);
      		$('.leaderName').text(data.member.name);
      		$(leaderpfp).attr('src', 'img/' + data.member.photo + '_350x350.jpg');
          });
    	 
    	 
    	  /* 멤버정보가져오기 */
         $.getJSON("json/joinedMember/listMember", {no: stdno}, data =>{
      		  console.log(data);
      		  let memberInfo = $('.memberInfo');
      		  
      		  for (var item of data) {
      			  $('<div class="col-4">'+
                    '<div class="row eachMember">'+
                       '<div class="col-sm">'+
                            '<div class="member-img"><img src="img/'+ item.member.photo +'_350x350.jpg" alt="profile"></div>'+
                        '</div>'+
                        '<div class="col-sm memberProfile">'+
                            (item.grade == 1 ?'<span class="memberClass">회원</span>' : '<span class="memberClass">리더</span>')+
                            '<span class="memberName">'+ item.member.name +'</span>'+
                        '</div>'+
                        '<div class="col-sm message">'+
                            '<a href="message.html">'+
                                '<img src="img/message.png" alt="..." class="message" onclick="">'+
                            '</a>'+
                        '</div>'+
                    '</div>'+
                '</div>').appendTo(memberInfo);
      		  }
      		
          }); 
         
         
         /* 멤버 카운트 */
         $.getJSON("json/joinedMember/count", {no : stdno}, data => {
      	   		$('.memberCount').text(data);
      	   		
         });
         
         /* 장소, 제목, 주제 + Timeline */
         $.getJSON("json/schedule/recent", {
        	studyNo : stdno
         }, data => {
        	console.log(data);
        	let date = new Date(data.startDate);

        	$("#scheduleDate").text(date.getFullYear() + "-" + (date.getMonth()+1) + "-" + date.getDate());
   	   		$('.scheduleLocationDetail').text(data.placeAddress);
   	   		$('.Loc').text(data.placeDetail);
   	   		$('.scheduleTilte').text(data.title);
   	   		$('.scheduleTopics').text(data.content);
   	   		
   	   		for(let item of data.schedules) {
   	   			let div = $("<div class='gg'>");
   	   			let startSpan = $("<span style='color:#a4a4a4;' class='schedyleStartTime'>");
   	   			let innerSpan = $("<span class='m'> ~ </span>");
   	   			let endSpan = $("<span style='color:#a4a4a4'> class='scheduleEndTime'>");
   	   			let contSpan = $("<span class='scheduleTimelineDetail'>");
   	   			
   	   			$(startSpan).text(item.startTime.split(":")[0] + ":" + item.startTime.split(":")[1] );
   	   			$(endSpan).text(item.endTime.split(":")[0] + ":" + item.endTime.split(":")[1]);
   	   			$(contSpan).text(item.content);
   	   			
   	   			$(div).append(startSpan);
   	   			$(div).append(innerSpan);
   	   			$(div).append(endSpan);
   	   			$(div).append(contSpan);
   	   			$($(".scheduleDetails")[2]).append(div);
   	   		}
   	   		
         });
            
          
    	 
    	 
    	 
    });
    // 가입요청하기 기능
    $(".acceptRequest").click(function(memno, stdno){
	    $.get("json/auth/loginstat", {}, res=> {
	    	memno = res.no;
	    }).done(function(data){
	    	console.log("memNo :"+ memNo);
		    console.log("no :" + stdno);
	        var consoleMsg = '가입요청';
	        var _msg = '가입 요청을 하시겠습니까??';
	        
	        if (confirm(_msg)) {
	        	if(memno == undefined){
	        		swal({
	  				  type: 'error',
	  				  title:  '로그인 후 이용하실 수 있습니다.'
	  				})
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
    			for(var item of data){
					console.log("item.grade : " + item.grade);
					switch(item.grade){
					case 0 :
						$("#grade0").css('display','block');
						$("#grade2").css('display','none');
						console.log("그룹관리");
						break;
					case 1 :
						$("#grade2").css('display','none');
						console.log("회원입니다");
						break;
					default :
						console.log("그룹가입");
						break;
					}
    			}
    		}
    	})
	})
});





