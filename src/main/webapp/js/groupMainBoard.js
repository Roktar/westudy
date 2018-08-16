"use strict"
var upFiles;
var memNo;
var dataMemNo;
var studyNo = window.location.search.substring(1).split("=")[1];

var detailView = $('.detail-view');
var inputView = $('.input-view');
var dataTitle;
var dataContent;
var dataView;
var fileDiv = $('#filesOne-div');
var count = 0;
var tbType;
if (tbType == ""){
	tbType = 0;
} else {
	tbType = 1;
}
// paging
var data;
var totalCount;
var countList;
var pageNum;
var contentNum = 10;
var startPage = 1;
var endPage = 5;
var prev = false;
var next = true;
var currentBlock = 1;
var lastBlock;

$(document).ready(function(){
	$.getJSON('json/auth/loginstat',{}, res =>{
		memNo = res.no;
		
	});
	
	$(".modal-content").on("shown.bs.modal", function() {
		dataMemNo = $(this).memNo;
		console.log(dataMemNo);
	});
	
	$(function() {
	    $('#files-div').click(function (e) {
	        e.preventDefault(); 
	        $('#fileupload').click(); 
	    }) 
	});
	
	$(function() {
	    $('#delBtn').click(function (e) {
	        e.preventDefault(); 
	        $('#filesWrap').click(); 
	    }) 
	});
	
   $('#fileupload').fileupload({
     url: 'json/FreeBoard/add',        // 서버에 요청할 URL
     dataType: 'json',         // 서버가 보낸 응답이 JSON임을 지정하기
     sequentialUploads: true,  // 여러 개의 파일을 업로드 할 때 순서대로 요청하기.
     singleFileUploads: false, // 한 요청에 여러 개의 파일을 전송시키기.
     autoUpload: false,        // 파일을 추가할 때 자동 업로딩 하지 않도록 설정.
     add: function (e, data) {
       console.log('add()...');
       upFiles = data.files;
       var fileDiv = $('#files-div');
       fileDiv.html("");
       
       for (var i = 0; i < data.files.length; i++) {
		   var fileWrapper = $('<div id="filesWrap">')
		   				   .attr("upFiles-index", i)
		   				   .addClass('fileWrap')
		   				   .click(doDel)
		   				   .appendTo(fileDiv);
		   $('<p style="display: inline-block;">' + data.files[i].name + '</p>').appendTo(fileWrapper);
		   $('<button id="delBtn" class="btn btn-secondary btn-sm" style="float: right;">삭제</button>')
		   .appendTo(fileWrapper);
      };

       $('#upload-btn').unbind("click");
       $('#upload-btn').click(function() {
    	   
           data.submit();
       });
       
       
     },
     done: function(e, data){
    	 console.log("done()");
    	 if (data.result == 1) {
    		 calcCurrentBlock(1);
    	 }
       
     },
     
     submit: function (e, data) { // 서버에 전송하기 직전에 호출된다.
    	 swal({
	    		type: 'success',
	    		title: '게시물 업로드 완료',
	    		showConfirmButton: false,
	    		timer: 2000
	    	})
	       data.formData = {
	    	   title: $('#boaTitle').val(),
	    	   content: $('#boaContent').val(),
	    	   memNo: memNo,
	    	   studyNo: studyNo	    	   
	       };
     }
   });
});

function doDel(event){
	   event.stopPropagation();
	   var wrapperDiv = $(event.currentTarget);
	   wrapperDiv.remove();
	   
	   var fileIndex = wrapperDiv.attr("upFiles-index");
	   upFiles.splice(fileIndex, 1);
	   if(fileIndex == 0){
		   count = 0;
	   }
	    
	    
	}
/*----------insert end----------*/



/*----------view----------*/

function showBoa(no) {
	
	$.ajax({
        url: "json/FreeBoard/" + no + "?studyNo=" + studyNo,

        type: "GET",

        dataType: "json",
        
        success: function(data){
        	
    		inputView.css('display','none');

    		$("#modalBoaTitle").html(data.title);
    		$("#modalBoaCont").html(data.content);
    		$('<div>'+'<a id="fileIconBtn" href="boardFiles/' + data.file + '">'+
    				'<img src="img/fileIcon.png" class="fileIcon">'+data.originFile +
    		'</a>'+'</div>').appendTo('#modalBoaFile');
    		
    		dataTitle = data.title;
    		dataContent = data.content;
    		dataView = data;
    		dataMemNo = data.memNo;
    		console.log(data);
    		$("#delete-btn").attr("onclick", "del(" + data.no + ")");
        }
	
	});
	
};
/*----------view end----------*/

/*------ update start ------*/
$(document).ready(function(){
	$((memNo == dataMemNo ? '<button id="delete-btn" class="btn btn-secondary detail-view" onclick="del(' + dataMemNo + ');">삭제</button>' : '') +
      	  (memNo == dataMemNo ? '<button id="update-btn" class="btn btn-default detail-view">수정</button>' : '')).appendTo('#boaOneFoot')
      	

	$('modalBoaFile').click(function(){
		$('#fileIconBtn').click();
	})
	    
	
	$('#update-btn').click(function(){
		inputView.css("display", "block");
	    detailView.css("display", "none");
	    
	    $('#boaOneTitle').val(dataTitle);
	    $('#boaOneContent').val(dataContent);
	    
	    $('#update-btn').css("display","none");
	    $('#delete-btn').css("display","none");
	    $('<button id="closeBtn" class="btn btn-secondary input-view" data-dismiss="modal">취소</button>').appendTo('#boaOneFoot');
	    $('<button id="updateOn-btn" class="btn btn-default input-view" type="button" data-dismiss="modal">수정</button>').appendTo('#boaOneFoot');
	    
	    
	    $(function() {
		    $('#filesOne-div').click(function (e) {
		        e.preventDefault(); 
		        $('#fileuploadOne').click(); 
		    }) 
		});
	    
	    $('#fileuploadOne').fileupload({
	        url: 'json/FreeBoard/update?no='+dataView.no,       // 서버에 요청할 URL
	        dataType: 'json',         		 // 서버가 보낸 응답이 JSON임을 지정하기
	        sequentialUploads: true, 		 // 여러 개의 파일을 업로드 할 때 순서대로 요청하기.
	        singleFileUploads: false, 		 // 한 요청에 여러 개의 파일을 전송시키기.
			autoUpload: false,        		 // 파일을 추가할 때 자동 업로딩 하지 않도록 설정.
			add: function (e, data) {
			    $('#fileWari').css("display", "none");   
				console.log('add()...');
			       upFiles = data.files;
			       var fileDiv = $('#filesOne-div');
			       fileDiv.html("");
			       
			       for (var i = 0; i < data.files.length; i++) {
					   var fileWrapper = $('<div id="filesWrap">')
					   				   .attr("upFiles-index", i)
					   				   .addClass('fileWrap')
					   				   .click(doDel)
					   				   .appendTo(fileDiv);
					   $('<p style="display: inline-block;">' + data.files[i].name + '</p>').appendTo(fileWrapper);
					   $('<button id="delBtn" class="btn btn-secondary" style="float: right;">삭제</button>')
					   .appendTo(fileWrapper);
			      };

			       $('#updateOn-btn').unbind("click");
			       $('#updateOn-btn').click(function() {
			           data.submit();
			       });
			       
			       
			     },
	        done: function(e, data){
	       	 console.log("done()");
	       	 if (data.result == 1) {
	       		calcCurrentBlock(1);
	       	 }
	          
	        },
	        
	        submit: function (e, data) { // 서버에 전송하기 직전에 호출된다.
	   	       console.log('submit()...');
	   	       data.formData = {
	   	    	   title: $('#boaOneTitle').val(),
	   	    	   content: $('#boaOneContent').val(),
	   	    	   memNo: memNo,
	   	    	   studyNo: studyNo	    	   
	   	       };
	        }
	      });
	});
});

/*----------Delete start----------*/
function del(no) {
   if (window.confirm("삭제하시겠습니까?") == false) 
	   	return;
	   
	   $.get("json/FreeBoard/delete"+ no, () => {
	   	location.href = "groupMain.html?no=" + studyNo;
   });

}
/*----------Delete end----------*/


//*----------list start----------*/

var totalCount;
var currentBlock = 1;
var prePage;
var nextPage;
var preBlock = false;
var nextBlock;
var pageSize = 10;
var pageNo;
var startPage = 1;
var endPage = 5;
var lastPage;
var pageCount = 1;
var tbType = 0;
$(function(){
	$('#searchBtn').click(function(){
		if($('input[name="search"]').val() != null){
			tbType = 1
		} else{
			tbType = 0;
		}	
		$('#countListBtn').html('');
		$('#boardTable').html('');
		
		$.ajax({
			url: "json/FreeBoard/" + (tbType == 0 ? "getCount?" : "getSearchCount?title="+ $('input[name="search"]').val()+"&") + "studyNo=" + studyNo,
			type: "GET",
			dataType: "json",
			success: function(data){
				console.log(pageCount);
				calcCurrentBlock(1);
				calcTotal(data);
				currentBlock = 1;
	        	calcStartPage(currentBlock);
	        	calcLastPage(data)
	        	if (data % 10 > 0){
	        		endPage = (data/10) +1;
	        	} else {
	        		endPage = data/10;
	        	}
	        	if(data > 50){
	        		calcPreNext(currentBlock);
	        	} else {
	        		preBlock = false;
	        		nextBlock = false;
	        	}
	        	
	        	if (endPage > 5){
	        		endPage = 5;
	        	}
	        	
	        	if(preBlock == true){
					
	        		$('<li class="page-item">'+
	        			  '<a class="page-link" onClick="redRaw(preBlock, (pageCount-5), endPage, currentBlock)"> < </a>'+
	        		  '</li>').appendTo('#countListBtn');
	    		}
					
					for(var pageCount = 1; pageCount <= endPage; pageCount++) {
						$('<li class="page-item countBtn">'+
				  			 '<a class="page-link" onClick="calcCurrentBlock('+ pageCount +')">'+ pageCount +'</a>'+
						  '</li>').appendTo('#countListBtn');
				}
					pageCount++;
					console.log(pageCount);
				if(nextBlock == true){
	        		$('<li class="page-item">'+
	        				 '<a class="page-link" onClick="redRaw(nextBlock, pageCount, endPage, currentBlock)"> > </a>'+
	        	       '</li>').appendTo('#countListBtn');
	    		}
				
			}
				
		})
});
	
	$.ajax({
    	url: "json/FreeBoard/" + (tbType == 0 ? "getCount?" : "getSearchCount?title="+ $('input[name="search"]').val()+"&") + "studyNo=" + studyNo,
        
        type: "GET",

        dataType: "json",

        success: function(data){
        	console.log(data);
//        	$('#countListBtn').html('');
        	pageCount = 1;
        	calcTotal(data);
        	calcStartPage(currentBlock);
        	calcLastPage(data)
        	calcEndPage(lastPage, currentBlock ,data);
        	calcPreNext(currentBlock);
        	if(totalCount > 1){
        		if(preBlock == true){
	        		$('<li class="page-item">'+
	        			  '<a class="page-link" onClick="redRaw(preBlock, pageCount, (endPage - 5), currentBlock)"> < </a>'+
	        		  '</li>').appendTo('#countListBtn');
        		}
        		for(pageCount = 1; pageCount <= endPage; pageCount++) {
        		 $('<li class="page-item countBtn">'+
        				 '<a class="page-link" onClick="calcCurrentBlock('+ pageCount +')">'+ pageCount +'</a>'+
	               '</li>').appendTo('#countListBtn');
        		}
        		if(nextBlock == true){
	        		$('<li class="page-item">'+
	        				 '<a class="page-link" onClick="redRaw(nextBlock, pageCount, endPage, currentBlock)"> > </a>'+
	        	       '</li>').appendTo('#countListBtn');
        		}
        	}
        		
        	
        	calcCurrentBlock(1)
        }
    });
});


function redRaw(blockType,pageCount, endPage, currentBlock){
	calcCurrentBlock(pageCount);
	
	console.log("pageSZ"+pageCount);
	
		if(lastPage != null){
			$('#countListBtn').html('');
			calcStartPage(currentBlock);
			calcPreNext(pageCount);

			if(preBlock == true){
				
        		$('<li class="page-item">'+
        			  '<a class="page-link" onClick="redRaw(preBlock, (pageCount-5), endPage, currentBlock)"> < </a>'+
        		  '</li>').appendTo('#countListBtn');
    		}
			if(pageCount - lastPage > currentBlock - pageCount ){
				
				for(var count = 0; count < lastPage; count++) {
					$('<li class="page-item countBtn">'+
			  			 '<a class="page-link" onClick="calcCurrentBlock('+ pageCount +')">'+ pageCount +'</a>'+
					  '</li>').appendTo('#countListBtn');
					pageCount++;
				}
				
			} else {
				for(var count = 0; count < endPage; count++) {
					$('<li class="page-item countBtn">'+
			  			 '<a class="page-link" onClick="calcCurrentBlock('+ pageCount +')">'+ pageCount +'</a>'+
					  '</li>').appendTo('#countListBtn');
					pageCount++;
			}
				
			}
			if(nextBlock == true){
        		$('<li class="page-item">'+
        				 '<a class="page-link" onClick="redRaw(nextBlock, pageCount, endPage, currentBlock)"> > </a>'+
        	       '</li>').appendTo('#countListBtn');
    		}
			
		}
	
}
function calcPreNext(pageCount){
	if (lastPage < pageCount) {
		preBlock = true;
		nextBlock = false;
	} else if (pageCount > 0 && currentBlock < 6){
		preBlock = false;
		nextBlock = true;
	} else {
		preBlock = true;
		nextBlock = true;
	}

}
function calcTotal(data){
	
	if (data % 10 > 0){
		totalCount = (data/10)+1;
		
	}else{
		totalCount = data / 10;
	}
	
}

function calcCurrentBlock(pageCount){
	console.log("언제 호출되는거야..");
	console.log(pageCount);
	if (pageCount % 5 > 0){
		currentBlock = (pageCount % 5) + 1;
		
	}else{
		currentBlock = pageCount;
	}
	
	$.ajax({
      url: "json/FreeBoard/" + (tbType == 0 ?  "list?" : "search?title="+ $('input[name="search"]').val()+ "&") + "pageNo=" + pageCount + "&pageSize=10&studyNo=" + studyNo,
      
      type: "GET",

      dataType: "json",

      success: function(data){
      	
      	$('#boardTable').html('');
      	
          $.each(data, function(index, data){
          
              if(data != null){
              	
                  $('<tr>' +
                      '<th scope="row">'+ data.no +'</th>' +
                          '<td>'+
                              '<a href="#boardOne" data-toggle="modal" onclick="showBoa('+data.no+')">' + data.title + '</a>'+
                              '</td>'+
                          '<td>' + data.member.name + '</td>'+
                      '<td>' + data.createdDate + '</td>'+
  	                    '<td>'+
  		                    '<a href="boardFiles/' + data.file + '">'+
  		                    	'<img src="img/fileIcon.png" class="fileIcon">'+
  		                    '</a>'+
  	                    '</td>'+
                      '</tr>').appendTo('#boardTable');
              }
              
          });

          
          
      }
  
  });
	
}

function calcStartPage(currentBlock){
	console.log("여기로들어온 current = ", currentBlock);
	startPage = (currentBlock * 5) - 4;
}

function calcLastPage(data){
	if(data / (5 * pageSize) > 0){
		lastPage = (5 * pageSize) + 1;
		
	}
	lastPage = data / (5 * pageSize);
//	console.log(lastPage);
}

function calcEndPage(lastPage, currentBlock, data){
	if (lastPage < currentBlock){
		
	 	endPage = calcTotal(data);
		
	} else {
		console.log(startPage);
		endPage = startPage + 4 ;
	}
}



	
/*----------list end----------*/










