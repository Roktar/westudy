/*----------insert----------*/
"use strict"
var imgFiles;
var memNo;
var studyNo;


$(document).ready(function(){
	$.getJSON(serverRoot + '/json/auth/loginstat',{}, res =>{
		console.log("loginData = " + res.no);
		memNo = res.no;
		
	});
	
	$(function() {
	    $('#ad-images-div').click(function (e) {
	        e.preventDefault(); $('#fileupload').click(); 
	    }) 
	});
	
	
	studyNo = window.location.search.substring(1).split("=")[1];
	
	$("#surveylisttab").attr("href", "surveylisttab.html?no="+studyNo);
	$("#calendartab").attr("href", "calendar.html?no="+studyNo);
	$(".mainLink").attr("href", "groupMain.html?no="+studyNo);
	$(".calLink").attr("href", "calendar.html?no="+studyNo);
	$(".surveyLink").attr("href", "surveylisttab.html?no="+studyNo);
	$(".albumLink").attr("href", "photoMain.html?no="+studyNo);
	
	
   $('#fileupload').fileupload({
     url: serverRoot + '/json/photo/add',        // 서버에 요청할 URL
     dataType: 'json',         // 서버가 보낸 응답이 JSON임을 지정하기
     sequentialUploads: true,  // 여러 개의 파일을 업로드 할 때 순서대로 요청하기.
     singleFileUploads: false, // 한 요청에 여러 개의 파일을 전송시키기.
     autoUpload: false,        // 파일을 추가할 때 자동 업로딩 하지 않도록 설정.
     disableImageResize: /Android(?!.*Chrome)|Opera/
           .test(window.navigator && navigator.userAgent), // 안드로이드와 오페라 브라우저는
															// 크기 조정 비활성 시키기
     previewMaxWidth: 80,   // 미리보기 이미지 너비
     previewMaxHeight: 80,  // 미리보기 이미지 높이
     previewCrop: true,      // 미리보기 이미지를 출력할 때 원본에서 지정된 크기로 자르기
     processalways: function(e, data) {
         console.log('fileuploadprocessalways()...');
//         console.log(data.files);
         imgFiles = data.files;
         var imagesDiv = $('#ad-images-div');
         imagesDiv.html("");
         for (var i = 0; i < data.files.length; i++) { 
           try {
             if (data.files[i].preview.toDataURL) {
              var imgWrapper = $('<div>')
                             .attr("data-file-index", i)
                             .addClass('ad-adImgs-wrapper')
                             .click(delImg)
                             .appendTo(imagesDiv);
                  var imgContent = $('<div>')
                              .addClass('ad-adImgs-content')
                              .appendTo(imgWrapper);
                  var imgCover = $('<div>')
                              .addClass('ad-adImgs-cover')
                              .appendTo(imgWrapper);
               $("<img/>").attr('src', data.files[i].preview.toDataURL()).appendTo(imgContent).addClass('ad-adImgs');
             
             }
           } catch (err) {}
         }
         $('#upload-btn').unbind("click");
         $('#upload-btn').click(function() {
             data.submit();
         });
         

     }, 
     done: function(e, data){
    	 console.log("done()");
    	 console.log(data.result);
    	 if (data.result == 1) {
    		 location.href = "photoMain.html?no=" + studyNo;
    	 }
       
     },
     
     submit: function (e, data) { // 서버에 전송하기 직전에 호출된다.
    	 swal({
	    		type: 'success',
	    		title: '사진 업로드 완료',
	    		showConfirmButton: false,
	    		timer: 1000
	    	})
	       console.log('submit()...');
	       data.formData = {
	    	   title: $('#phoTitle').val(),
	    	   memNo: memNo,
	    	   studyNo: studyNo	    	   
	       };
	       
     }
   });
});

function delImg(event){
	event.stopPropagation();
   var wrapperDiv = $(event.currentTarget);
   wrapperDiv.remove();
    var fileIndex = wrapperDiv.attr('data-file-index');
    imgFiles.splice(fileIndex, 1);
}														/*----------insert end----------*/


/*----------list(main)----------*/

var today = new Date();
var yyyy = today.getFullYear();
var MM = (today.getMonth() + 1);
var dd = (today.getDate());
var nowDate = yyyy + "-" + MM + "-" + dd;
var preDate = yyyy + "-" + (MM - 1) + "-" + "01";
var upNowDate;
var upPreDate = yyyy + "-" + (MM - 1) + "-" + "01";

$(document).ready(function() {
    let isEnd = false;
    
    $(function(){
    	
        $(window).scroll(function(){
        	
            let $window = $(this);

            let scrollTop = $window.scrollTop();

            let windowHeight = $window.height();

            let documentHeight = $(document).height();

            if(scrollTop + windowHeight + 230 > documentHeight){
            		this.upNowDate = this.upPreDate;
            		if( (MM - 1) < 1  ) {
            			yyyy--;
            			MM = 13;
            		}
            		this.upPreDate = yyyy + "-" + (--MM) + "-" + "01";
            		fetchList2();
            	}; 
      })    
      fetchList();
})
var $msnry = $('#grid_container').masonry({
	itemSelector : '.grid-item',
	columnWidth : 210,
	gutter : 15,
	percentPosition: true,
	//                horizontalOrder: true,
	transitionDuration: '0.3s'
});

let fetchList = function(){
	var elem = [];
    if(isEnd == true){
         return;
    }
    
    
//    let startNo = $(".grid-item").last().data("no") || 0;
    

    $.ajax({
        url: serverRoot + "/json/photo/list?nowDate="+nowDate+"&"+"preDate="+preDate+"&studyNo="+studyNo,
        type: "GET",

        dataType: "json",

        success: function(data){
        	console.log(data);
        	if(data == "null"){
        		$("<p> 사진을 등록해주세요</p>").appendTo("#grid_container");
        	}
            let length = data.length;

            if( length < 5 ){ 
                    isEnd = true;
            }
            
            
            $.each(data, function(index, data){
                var item = data;
                var no = item.no;
//                console.log(data);
                
                elem[index] = $('<div class="grid-item">' +
                        '<div class="hvrbox">' +
                            '<a id="clickModal" onclick="showImg('+item.no+');" data-toggle="modal" href="#viewModal">' + 
                            '<img class="listImg hvrbox-layer_bottom" src="files/'+ item.photo +'_350x350.jpg">' +
                                '<div class="hvrbox-layer_top hvrbox-layer_slidedown">'+
                                    '<div class="hvrbox-text" style="color:white; top:30%">'+ item.title +'</div>'+
                                    '<div class="hvrbox-text hvrbox-date" style="color:white; top:60%;">'+ item.createdDate +'</div>'+
                                    (memNo == data.memNo ? '<button id="delBtn" class="btn btn-secondary btn-sm" onclick="doDep(event,' + item.no + ');">삭제</button>' : '') +
                                    '</div>'+
                                '</a>' +
                            '</div>'+
                        '</div>');        
            });
            

            for(var i of elem){
            	$msnry.append(i).masonry('appended', i);
            	var $grid = $('#grid_container').imagesLoaded(function() {
            		$msnry.masonry('layout');
            	});
            }
            
            
        }
    
    });   
    
}

let fetchList2 = function(){
	var elem = [];
	
    if(isEnd == true){
        isEnd = false;
         return;
    }
    console.log(upNowDate);
    console.log(upPreDate);
    
    $.ajax({
        url: serverRoot + "/json/photo/list?nowDate="+upNowDate+"&"+"preDate="+upPreDate+"&studyNo="+studyNo,

        type: "GET",

        dataType: "json",

        success: function(data){
        	console.log(data);
            let length = data.length;  
            
            if( length < 5 ){ 
                    isEnd = true;
            }
            
            
            $.each(data, function(index, data){
            	console.log(data);
                var item = data;
                
                elem[index] = $('<div class="grid-item">' +
                    '<div class="hvrbox">' +
                        '<a id="clickModal" onclick="showImg('+item.no+');" data-toggle="modal" href="#viewModal">' + 
                        '<img class="listImg hvrbox-layer_bottom" src="files/'+ item.photo +'_350x350.jpg">' +
                            '<div class="hvrbox-layer_top hvrbox-layer_slidedown">'+
                                '<div class="hvrbox-text" style="color:white; top:30%">'+ item.title +'</div>'+
                                '<div class="hvrbox-text hvrbox-date" style="color:white; top:60%;">'+ item.createdDate +'</div>'+
                                (memNo == data.memNo ? '<button id="delBtn" class="btn btn-secondary btn-sm" onclick="doDep(event,' + item.no + ');">삭제</button>' : '') +
                                '</div>'+
                            '</a>' +
                        '</div>'+
                    '</div>');        
            
            });
            
//            console.log(elem);
            
            for(var i of elem){
            	$msnry.append(i).masonry('appended', i);
            	var $grid = $('#grid_container').imagesLoaded(function() {
            		$msnry.masonry('layout');
            	});
            }
        }
    });   
    
}

});											
/*----------list(main) end----------*/


/*----------view----------*/

function showImg(no) {
	 
	$.ajax({
        url: serverRoot + "/json/photo/" + no + "?studyNo="+studyNo,

        type: "GET",

        dataType: "json",
        
        success: function(data){
        	console.log(data.photo);
        	$("#modalTitle").html(data.title);
        	$("#modalImg").attr("src", "files/"+ data.photo +"_380x380.jpg");	
        }
	
	})
}

/*----------view end----------*/	

/*----------delete----------*/
/*function doDep(event, no) {
   event.stopPropagation();

   if (window.confirm("삭제하시겠습니까?") == false) 
	   	return;
	   
	   $.get(serverRoot + "/json/photo/delete"+ no, () => {
	   	location.href = "photoMain.html?no="+ studyNo;
   });

}*/



function doDep(event, no) {
	  event.stopPropagation();
	 const swalWithBootstrapButtons = swal.mixin({
	        confirmButtonClass: 'btn btn-primary oBtn',
	        cancelButtonClass: 'btn btn-secondary cBtn',
	        buttonsStyling: false,
	      })
	      swalWithBootstrapButtons({
	           title: '삭제 하시겠습니까?',
	           type: 'warning',
	           showCancelButton: true,
	           confirmButtonText: '네 삭제할래요!',
	           cancelButtonText: '아니요!',
	           reverseButtons: true
	         }).then((result) => {
	 
	    if (result.value) {
		     
		   $.get(serverRoot + "/json/photo/delete"+ no, () => {
		   
	   })
	   	swal({
	   		type: 'success',
             title: '삭제 완료!',
             showConfirmButton: false,
             timer: 700
             }).then((result) => {
            		location.href = "photoMain.html?no="+ studyNo;
               }
             )
	    	}
	      })
}

/*----------delete end----------*/








