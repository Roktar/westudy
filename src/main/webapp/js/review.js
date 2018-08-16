$(document).ready(function() {
	$("#header").load(serverRoot + "/header.html");
	var mno = -1;
    $.get("json/auth/loginstat", {}, res=> {
        console.log("[" + res + "]" + ", " + typeof(res));
        console.log(res);
        console.log(res.no);
        mno = res.no;
        
    }).done(function(data){
  	   
   
	$.get("json/review/count" , data =>  {
		var k = 0;
		for(var item of data) {
			$('<div class="list-group-item list-group-item-action cls-list-link" onclick="mkreview('+ 
					k +')">'+ item.study.category +' <span class="review_no">('+ item.count +')</span></div>').appendTo(".cls-list");
			k++;
		}
	});
	

	
	
	
	var stn = 1;
	var end = 10;	
	$.get("json/review/list" , {
		pageNo: stn,
	    pageSize : end,
		category:'IT'
			
	   }, data =>  {
		let list_group = $('.reviewbox');
		
		var i = 1;

		for(var item of data) {
			console.log(item);
			console.log(mno);
			console.log("photo = "+item.member.photo);
			$('<li class="list-group-item">' +
					'<div class="mprof-img"><img src="img/'+ item.member.photo +'_350x350.jpg" alt="profile"></div>'+
					'<div id="user_inform">' +
/*					(mno == item.member.no ? '<button class="btn" id="delBtn" onclick="doDep(event,' + item.no + ');"></button>' : '') +
*/					'<div id="user_name"><a href="#">'+ item.member.name +'</a></div>'+
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
					'<div class="study-img"><img src="img/studyImgs/'+ item.study.picture +'_300x300.jpg" alt="study-profile"></div>'+
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
		$('<button type="button" class="btn btn-primary plusbtn" onclick="moreView(0)">더보기</button>').appendTo(".plusDiv");

		});
	
	$.get("json/review/countOne" , {category:'IT'}, data => {
		let review_count = $('.stdclasstitl2');
		$('<span class="stdclasstitl2">'+ data.study.category +'</span>').appendTo(review_count);
		$('<span class="reviewcount">('+ data.count +')</span>').appendTo(review_count);
	});
	
});
    
})
     




function mkreview(c) {
	
	setTimeout(function() {
	
	let review_count = $('.stdclasstitl2');
	var category = [
		{ct:"IT"}, {ct:"언어"},{ct:"국가시험"},{ct:"기사"},{ct:"금융"},{ct:"취업"},{ct:"토론"},{ct:"기타"}
	]
	$(review_count).empty();
	$(".reviewbox").empty();
	st = 2;
	console.log("mkreview_st: "+st);
	var ctg = category[c].ct;
	var stn = 1;
	var end = 10;
	
	var mno = -1;
    $.get("json/auth/loginstat", {}, res=> {
        console.log("[" + res + "]" + ", " + typeof(res));
        console.log(res.no);
        mno = res.no;
        
    }).done(function(data){
	
	
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
						'<div class="mprof-img"><img src="img/'+ item.member.photo +'_350x350.jpg" alt="profile"></div>'+						
						'<div id="user_inform">' +
/*						(mno == item.member.no ? '<button id="delBtn" class="btn btn-default btn-sm" onclick="doDep(event,' + item.no + ');">삭제</button>' : '') +
*/						'<div id="user_name"><a href="#">'+ item.member.name +'</a></div>'+
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
						'<div class="study-img"><img src="img/studyImgs/'+ item.study.picture +'_300x300.jpg" alt="study-profile"></div>'+
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
			$('.plusDiv').empty();
			$('<button type="button" class="btn btn-primary plusbtn" onclick="moreView('+ c +')">더보기</button>').appendTo(".plusDiv");
			});
		
		
		$.get("json/review/countOne" , {category:ctg}, data => {
			let review_count = $('.stdclasstitl2');
			$('<span class="stdclasstitl2">'+ data.study.category +'</span>').appendTo(review_count);
			$('<span class="reviewcount">('+ data.count +')</span>').appendTo(review_count);
		});
    })
	}, 500);
}




/*더보기*/	
var st = 2;
var ed = 10;	
var s = 10;

function moreView(c) {
	
	setTimeout(function() {
	var category = [
		{ct:"IT"}, {ct:"언어"},{ct:"국가시험"},{ct:"기사"},{ct:"금융"},{ct:"취업"},{ct:"토론"},{ct:"기타"}
	]
	var ctg = category[c].ct;
	
	var mno = -1;
    $.get("json/auth/loginstat", {}, res=> {
        console.log("[" + res + "]" + ", " + typeof(res));
        console.log(res.no);
        mno = res.no;
        
    }).done(function(data){
	
		    	$.get("json/review/list" , {
		    		pageNo: st,
		    	    pageSize : ed,
		    	    category:ctg
		    	    
		    	   }, data =>  {
		    		let list_group = $('.reviewbox');
		    		
		    		var i = 1;
		    		
		    	
		    		
		    		for(var item of data) {
		    			console.log(item);
		    			$('<li class="list-group-item" id="categ">' +
		    					'<div class="mprof-img"><img src="img/'+ item.member.photo +'_350x350.jpg" alt="profile"></div>'+		    					
		    					'<div id="user_inform">' +
/*		    					(mno == item.member.no ? '<button id="delBtn" class="btn btn-default btn-sm" onclick="doDep(event,' + item.no + ');">삭제</button>' : '') +
*/		    					'<div id="user_name"><a href="#">'+ item.member.name +'</a></div>'+
		    					'<div class="user_score">'+
		    					'<select id="review-score'+ s +'">'+
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
		    					'<div class="study-img"><img src="img/studyImgs/'+ item.study.picture +'_300x300.jpg" alt="study-profile"></div>'+
		    					'</div>'+
		    					'<hr class="review-line">'+
		    					
		    			'</li>').appendTo(list_group);
		    			
		    			
		    			
		    			/*별점 */ 	
		    			$('#review-score' + s).barrating({
		    				theme: 'css-stars',
		    				readonly: true
		    			})
		    			$('#review-score' + s).barrating('set', item.rating);
		    			s++;
		      			}
			    	})
			    	s++;
		    		console.log("moreview_s: "+s);
			    	st++;
			    	console.log("moreview_st: "+st);
			    	
    			})
				}, 700);
		  	}
	




/*delete 버튼
function doDep(event, no) {
	event.stopPropagation();
	const swalWithBootstrapButtons = swal.mixin({
		  confirmButtonClass: 'btn btn-primary',
		  cancelButtonClass: 'btn btn-secondary',
		  buttonsStyling: false,
		})
   
   swalWithBootstrapButtons({
    					  title: '후기를 삭제하시겠습니까?',
    					  type: 'question',
    					  showCancelButton: true,
    					  confirmButtonText: '삭제',
    					  cancelButtonText: '아니요!',
    					  reverseButtons: true		
   		}).then((result) => {	
		   if (result.value) {
			   $.ajax({
		        	url: 'json/review/delete',
					type: 'GET',
					data:{
	                      no : no 
					}
		    }).done(function(){
					swal({
			    	   type: 'success',
	                   title: '삭제 완료!',
	                   showConfirmButton: false,
	                   timer: 700
					 }).then((result) => {
			    			location.href="review.html";
			    	 })
		       }
		    )
		  }  
		})
	  }	*/
	  

