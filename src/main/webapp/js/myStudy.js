$(document).ready(function() {
	  
	  var no = -1;
      $.get("json/auth/loginstat", {}, res=> {
          console.log("[" + res + "]" + ", " + typeof(res));
          console.log(res.no);
          no = res.no;
          
      }).done(function(data){
    	   
    	  $.get("json/joinedMember/"+no, (data) => {
    		  let mystudy = $('.mystudy');
    		  let ownstudy = $('.ownstudy');
    		  
    		  console.log(data)
    		  var i = 0;
    		  var k = 0;
    		  var s = 2;
    		
    		  for(var item of data) {
    			  if (item.grade == 0) {
    			  $('<div>'+
    					  '<span class="gg">'+ item.study.category +' 스터디 </span><a href="groupMain.html"><span id="stdname">\''+ item.study.name +'\'</span></a>'+
    					  '<button class="btn btn-secondary btn-sm ttbtn" data-toggle="modal" data-target="#tmodal">탈퇴</button>'+
    					  '<a class="identifyingClass" data-toggle="modal" href="#dAD" data-id="my_id_value"><button class="btn btn-primary btn-sm review" id="rbtn'+ i +'">후기 작성</button></a>'+
    			  '</div>').appendTo(mystudy);
    			      i++;
    			 
    			  
    			
    			  
    			  } else if (item.grade == 1) {
    				  $('<div>'+
        					  '<span class="gg">'+ item.study.category +' 스터디 </span><a href="groupMain.html"><span id="stdname">\''+ item.study.name +'\'</span></a>'+
        					  '<button class="btn btn-secondary btn-sm ttbtn" data-toggle="modal" data-target="#tmodal">탈퇴</button>'+
        					  '<a class="identifyingClass" data-toggle="modal" href="#dAD" data-id="my_id_value"><button class="btn btn-primary btn-sm review" id="rbtn'+ s +'">후기 작성</button></a>'+
        			  '</div>').appendTo(ownstudy);
    				  s++;
    			  }
    			  console.log(k);
    			  (function(k) {
        			  
        			  $('#rbtn' + k).click(function()  {
        				  console.log(this);
        				  this.sno = data[k].studyNo;
        				  this.mno = data[k].memNo;
        				  this.ctg = data[k].study.category;
      	  				console.log(this.sno);
      	  				console.log(this.mno);
      	  				console.log(this.ctg);
      	  			
      	  		     $("#addBtn").click(() => {
      	  		     $('#modal').modal('hide');
      	  		    	$.ajax({
      	  		    		type : 'POST',
      	  		    		url : 'json/review/add',
      	  		    		data: {
      	  		    			category : this.ctg,
      	  		    			content: $(fContent).val(),
      	  		    			rating : $(example).val(),
      	  		    			"member.no": this.mno,
      	  		    			"study.no" : this.sno
      	  		    		}
      	  		    	 }).done(function(){
      	  		    		
      	  		    	 /*swal({
      	  		    		 type: 'success',
      	  		    		 title: '작성 완료!',
      	  		    		 preConfirm: () => {
      	  		    		 $('#modal').modal('hide');
      	  		    		 location.reload("Mystudy.html")
      	  		    		 
      	  		    		 }
      	  		    	 })*/
      	  		    	
      	  		    		swal({
      	  		    		 type: 'success',
      	                      title: '작성 완료!',
      	                      showConfirmButton: false,
      	                      timer: 700
           	  		    		 

      	  		    		}).then((result) => {
      	  		    			location.href="review2.html";
      	  		    		})
      	  		    		 
      	  		    		 
      	  		       })
      	  		     });
      	  		     
      	  		  })
        			  })(k);
      	  		 k++;
    			  
    		  }
    	  })
	});
      

     
      
 });

 
 




