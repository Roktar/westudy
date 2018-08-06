function readURL(input) {
 
    if (input.files && input.files[0]) {
        var reader = new FileReader();
 
        reader.onload = function (e) {
            $('#image_section').attr('src', e.target.result);
        }
 
        reader.readAsDataURL(input.files[0]);
    }
}
 
$("#imgInput").change(function(){
    readURL(this);
});

// 체크박스 전체선택

$(document).ready(function(){
    //최상단 체크박스 클릭
    $("#checkall").click(function(){
        //클릭되었으면
        if($("#checkall").prop("checked")){
            //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 true로 정의
            $("input[name=chk]").prop("checked",true);
            //클릭이 안되있으면
        }else{
            //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 false로 정의
            $("input[name=chk]").prop("checked",false);
        }
    })
})



        $(document).ready(function(){
    //최상단 체크박스 클릭
    $("#checkall2").click(function(){
        //클릭되었으면
        if($("#checkall2").prop("checked")){
            //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 true로 정의
            $("input[name=chk2]").prop("checked",true);
            //클릭이 안되있으면
        }else{
            //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 false로 정의
            $("input[name=chk2]").prop("checked",false);
        }
    })
})


var list_group = $('.memberbox');


var i = 1;


$(document).ready(function() {
	$.get("json/joinedMember/list", {}, (data) => {
		let list_group = $('.memberbox');
		
		for(var item of data) {
			$('<tr>' +
			'<td>'+
			'<input class="form-check-input position-static chk" type="checkbox" id="blankCheckbox" value="option1" aria-label="..." name="chk2">'+
			'</td>'+
			'<td>'+item.member.mname+'</td>'+
			'<td>'+item.member.email+'</td>'+
			'<td>'+item.registedDate+'</td>'+
			'</tr>').appendTo(list_group);
			
			i++;
		}
	});
});