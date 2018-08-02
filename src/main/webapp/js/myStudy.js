
  
 var list_group = $('.mystudy');
 

 $(document).ready(function() {
	 $.get("json/review/list", {}, (data) => {

		for(var item of rv) {
			console.log(item)
			$('<div>'+
            '<span class="gg">'+ item.category +' 스터디 </span><a href="groupMain.html">\''+ item.study +'\'</a>'+
            '<button class="btn btn-secondary btn-sm ttbtn" data-toggle="modal" data-target="#tmodal">탈퇴</button>'+
            '<a data-toggle="modal" href="#dAD"><button class="btn btn-primary btn-sm review">후기 작성</button></a>'+
            '</div>').appendTo(list_group);
			
		}
	 }
 }
	





