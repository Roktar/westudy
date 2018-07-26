

var list_group = $('.reviewbox');


var i = 1;


$(document).ready(function() {
	$.get("json/review/list", {category:'언어'}, (data) => {
		let list_group = $('.reviewbox');

		for(var item of data) {
			$('<li class="list-group-item">' +
					'<a href="#"><img src="img/'+ item.pimg +'"  width="65rem" height="65rem" class="rounded-circle prof-img" alt="profile"></a>'+
					'<div id="user_inform">' +
					'<div id="user_name"><a href="#">'+ item.member.name +'</a></div>'+
					'<div class="user_score">'+
					'<select id="review-score'+ i +'">'+
					'<option value="1">1</option>'+
					'<option value="2">2</option>'+
					'<option value="3">3</option>'+
					'<option value="4">4</option>'+
					'<option value="5">5</option>'+
					'</select>'+
					'</div>'+
					'<div class="user_class">'+ item.study.category +'</div>'+
					'</div>'+
					'<form>'+
					'<div class="form-group">'+
					'<pre class="form-control review-form" id="user-review" readonly>'+ item.content +'</pre>'+
					'</div>'+
					'</form>'+
					'<span class="review_date">'+ item.createdDate +'</span>'+
					'<div class="user_class_info">'+
					'<div class="user_class_name"><a href="groupMain.html">'+ item.study.name +'</a> <span class="study-link">스터디</span></div>'+
					'<img src="img/'+ item.img +'"  class="img-fluid rounded-circle study-prof" alt="study-profile">'+
					'</div>'+
					'<hr class="review-line">'+
			'</li>').appendTo(list_group);

			/*별점 */ 	
			$('#review-score' + i).barrating({
				theme: 'css-stars',
				readonly: true
			})
			$('#review-score' + i).barrating('set', item.rating);
			i++;
		}
		console.log(data);
		let review_count = $('.stdclasstitl2');
		$('<span class="reviewcount">('+ item.count +')</span>').appendTo(review_count);
		
	});
});




