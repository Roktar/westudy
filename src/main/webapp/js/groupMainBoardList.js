"use strict"

var studyNo = window.location.search.substring(1).split("=")[1];

var totalCount;
var currentBlock = 1;
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




