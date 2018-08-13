/*----------list(main) end----------*/
"use strict"
var studyNo = window.location.search.substring(1).split("=")[1];;

$(document).ready(function(){
	$('<a href="photoMain.html?no='+ studyNo +'" class="btn btn-sm photoBtn">더보기</a>').appendTo('#photoBox');

    $.ajax({
        url: "json/photo/groupList?studyNo="+studyNo,
        type: "GET",

        dataType: "json",

        success: function(data){            
            $.each(data, function(index, data){
                var item = data;
                
                $('<div class="card">' +
                    '<img class="card-img-top" src="files/'+ item.photo +'_380x380.jpg" alt="Card image cap">' +
                        '<div class="card-body">'+
                            '<p class="card-text">'+ item.title +'</p>'+
                            '<p class="card-text"><small class="text-muted">'+ item.createdDate +'</small></p>'+
                        '</div>'+
                    '</div>').appendTo('#photoGM');        
            });
            
            
        }
    
    });   

});											
/*----------list(main) end----------*/




















