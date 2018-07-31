$(document).ready(function() {
	$.get("json/review/count" , data =>  {
		var k = 0;
		for(var item of data) {
			$('<div class="list-group-item list-group-item-action cls-list-link" onclick="mkreview('+ 
					k +')">'+ item.study.category +' <span class="review_no">('+ item.count +')</span></div>').appendTo(".cls-list");
			k++;
		}
	});
	
	var stn = 1;
	var end = 3;	
	$.get("json/review/list" , {
		pageNo: stn,
	    pageSize : end,
		category:'IT'
		
			
	   }, data =>  {
		let list_group = $('.reviewbox');
		
		var i = 1;

		for(var item of data) {
			console.log(item);
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
				
		});
	
	$.get("json/review/countOne" , {category:'IT'}, data => {
		let review_count = $('.stdclasstitl2');
		$('<span class="stdclasstitl2">'+ data.study.category +'</span>').appendTo(review_count);
		$('<span class="reviewcount">('+ data.count +')</span>').appendTo(review_count);
	});
	
});


function mkreview(c) {
	let review_count = $('.stdclasstitl2');
	var category = [
		{ct:"IT"}, {ct:"언어"},{ct:"국가시험"},{ct:"기사"},{ct:"금융"},{ct:"취업"},{ct:"토론"},{ct:"기타"}
	]
	$(review_count).empty();
	$(".reviewbox").empty();
	var ctg = category[c].ct;
	var stn = 1;
	var end = 3;
	$.get("json/review/list" , {
		pageNo: stn,
	    pageSize : end,	
		category:ctg
		
	}, data =>  {
		let list_group = $('.reviewbox');
		
		var i = 1;

		for(var item of data) {
			console.log(item);
			$('<li class="list-group-item" id="categ">' +
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
		
		});
	
	$.get("json/review/countOne" , {category:ctg}, data => {
		let review_count = $('.stdclasstitl2');
		$('<span class="stdclasstitl2">'+ data.study.category +'</span>').appendTo(review_count);
		$('<span class="reviewcount">('+ data.count +')</span>').appendTo(review_count);
	});
}




	



$(document).on('click', '.plusbtn', function(c) {
			var totalcount;
			var stn = 2;
	    	var end = 3;	
	    	$.get("json/review/list" , {
	    		pageNo: stn,
	    	    pageSize : end,
	    	    category: 'it'
	    	    
	    	   }, data =>  {
	    		let list_group = $('.reviewbox');
	    		
	    		var i = 1;

	    		for(var item of data) {
	    			console.log(item);
	    			$('<li class="list-group-item" id="categ">' +
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
		    	})
		  	});




	$.get("json/review/countOne", data => {
	var t = 0;
				$('<button type="button" class="btn btn-primary plusbtn" onclick="moreView()">더보기</button>').appendTo(".plusDiv");
	});

