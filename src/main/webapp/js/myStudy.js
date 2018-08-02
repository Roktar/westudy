 $(document).ready(function() {
	 
	 
	 
	 $.get("json/review/listall", {}, (data) => {
		 let list_group = $('.mystudy');
		 

		for(var item of data) {
			console.log(item)
			$('<div>'+
            '<span class="gg">'+ item.study.category +' 스터디 </span><a href="groupMain.html">\''+ item.study.name +'\'</a>'+
            '<button class="btn btn-secondary btn-sm ttbtn" data-toggle="modal" data-target="#tmodal">탈퇴</button>'+
            '<a data-toggle="modal" href="#dAD"><button class="btn btn-primary btn-sm review">후기 작성</button></a>'+
            '</div>').appendTo(list_group);
			
		}
	});
 });
	





