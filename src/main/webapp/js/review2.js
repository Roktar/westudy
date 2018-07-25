

var list_group = $('.reviewbox');

var i = 1;


$(document).ready(function() {
	$.get("json/review/list", {name:'ㄴㄴ'}, res => {
		let list_group = $('.reviewbox');

		for(let item in res) {


			$('<li class="list-group-item">' +
					'<a href="#"><img src="img/'+ res[item].pimg +'"  width="65rem" height="65rem" class="rounded-circle prof-img" alt="logo"></a>'+
					'<div id="user_inform">' +
					'<div id="user_name"><a href="#">'+ res[item].member +'</a></div>'+
					'<div class="user_score">'+
					'<select id="review-score'+ i +'">'+
					'<option value="1">1</option>'+
					'<option value="2">2</option>'+
					'<option value="3">3</option>'+
					'<option value="4">4</option>'+
					'<option value="5">5</option>'+
					'</select>'+
					'</div>'+
					'<div class="user_class">'+ res[item].category +'</div>'+
					'</div>'+
					'<form>'+
					'<div class="form-group">'+
					'<pre class="form-control review-form" id="user-review" readonly>'+ res[item].content +'</pre>'+
					'</div>'+
					'</form>'+
					'<span class="review_date">'+ res[item].createdDate +'</span>'+
					'<div class="user_class_info">'+
					'<div class="user_class_name"><a href="groupMain.html">'+ res[item].study +'</a> <span class="study-link">스터디</span></div>'+
					'<img src="img/'+ res[item].img +'"  class="img-fluid rounded-circle study-prof" alt="logo">'+
					'</div>'+
					'<hr class="review-line">'+
			'</li>').appendTo(list_group);



			<!-- 별점 --> 	
			$('#review-score' + i).barrating({
				theme: 'css-stars',
				readonly: true
			})
			$('#review-score' + i).barrating('set', res[item].rating);
			i++;
		}
	});
});
