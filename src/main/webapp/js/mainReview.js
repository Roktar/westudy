$(document).ready(function() {

	$.get("json/review/listall" , {}, data =>  {
		let list_group = $('.reviewBox');
		console.log(data);
		for(var item of data) {
					$('<li class="l-col">'+
                        '<div class="study">'+
                        	'<div class="study-icon">'+
                            '<img src="img/'+ item.member.photo +'_350x350.jpg">'+
                            '</div>'+
                            '<h2>'+ item.study.name +'</h2>'+
                            '<p>'+ item.content +'</p>'+
                            '<div class="study-hover">'+
                                '<a href="groupMain.html?no='+ item.studyNo +'"><span>스터디 둘러보기</span></a>'+
                            '</div>'+
                        '</div>'+
                    '</li>').appendTo(list_group);
                  
		}
	});
});