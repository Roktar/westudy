"use strict"
var memNo;	
$(document).ready(function(){
	$('#uploadBtn').click(function(){
		$('#fileupload').click();
		
	});
	$('.input-view').css('display', 'none');
	$('#cityCount').click(function(){
		$(".datail-view").css('display','none');
		$(".input-view").css('display','block');
		$('#updataCity').click();
	});


	$.getJSON(serverRoot + '/json/auth/loginstat',{}, res =>{
		console.log("loginData = " + res.no);
		memNo = res.no;
		
		
	$.getJSON(serverRoot + "/json/member/" + memNo, function(data) {
		console.log(data);
		$('#myPhoto').attr("src", serverRoot +"/img/"+ data.profile + "_350x350.jpg" );
		$('#name').attr('placeholder', data.name);
		$('#email').attr('placeholder', data.email);
		$('#tel').attr('placeholder', data.tel);
		$('<input class="float-right inputPro" readonly value="'+data.city +',  '+ data.county +'"' +'">').appendTo('#cityCount');
		
	});

	$("#updateBtn").click(() => {
		let updateData = {
				memNo : memNo,
                tel: $("#tel").val(),
                city: $("#fCity option:checked").text(),
                county: $("#fCounty option:checked").text()
		}
		$.post(serverRoot + "/json/member/updateProfile", updateData, (data) => {
            if(data.result == 1) {
                swal("정보 변경 완료",
                     "정보를 변경하였습니다.",
                     "success");
            } else {
                swal("정보 변경 실패",
                "정보 변경에 실패하였습니다.",
                "error");
            }
        });
    });
	
	
	
	    $('#fileupload').fileupload({
	    	url: 'json/member/uploadPhoto',
	        dataType: 'json',
	        sequentialUploads: true,  // 여러 개의 파일을 업로드 할 때 순서대로 요청하기.
	        add: function (e, data) {
	        	console.log(data);
	            console.log('add()...');
//	            $('#myPhoto').attr('src',);
	            for (var i = 0; i < data.files.length; i++) { 
	                try {
	                  if (data.files[i].preview.toDataURL) {
	                	  $("<img/>").attr('src', data.files[i].preview.toDataURL()).appendTo('#myPhoto');
	                  }
	                } catch (err) {}
	              }
	            $('#updateBtn').unbind("click");
	            $('#updateBtn').click(function() {
	                data.submit();
	            });
	            
	        },
	        done: function (e, data) {
	          console.log('done()...');
	          console.log(data.result);
	          var data = data.result;
	          location.href = "myProfile.html"

	        },
	        submit:function(e, data) {
	        	swal({
		    		type: 'success',
		    		title: '변경 완료',
		    		showConfirmButton: false,
		    		timer: 2000
		    	});
		    	console.log("submit()");
	            data.formData = {
	                    memberNo: memNo,
	                    tel: $('#tel').val(),
	                    city: $("#fCity option:checked").text(),
	                    county: $("#fCounty option:checked").text()
	            }
	        }
	    });
	    
	
	
	
	
	});
	    
});