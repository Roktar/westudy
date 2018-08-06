/*----------insert----------*/
"use strict"
var studyNo;

$(document).ready(function(){

    $.ajax({
        url: serverRoot + "/json/photo/list?nowDate="+nowDate+"&"+"preDate="+preDate+"&studyNo="+studyNo,
        type: "GET",

        dataType: "json",

        success: function(data){            
            $.each(data, function(index, data){
                var item = data;
                $('<div class="card">' +
                        '<div class="hvrbox">' + 
                            '<img class="card-img-top" src="files/'+ item.photo +'_350x350.jpg" alt="Card image cap">' +
                                '<div class="card-body">'+
                                    '<p class="card-text">'+ item.title +'</p>'+
                                    '<p class="card-text"><small class="text-muted">'+ item.createdDate +'</small></p>'+
                                '</div>'+    
                        '</div>'+
                    '</div>').appendTo('#photoGM');        
            });
            
            
        }
    
    });   

});											
/*----------list(main) end----------*/




















