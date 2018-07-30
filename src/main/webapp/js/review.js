var rv = [
	 {no: 1, pimg:"ee.jpg", img:"review-img.jpg", content: "정보처리기사 준비 혼자하기 너무너무너무 막막막했는데 스터디원들하고 같이 준비해서 합격할 수 있었습니다. 감사합니다 !!!!", rating: 4, createdDate: "2018-07-24", category: "JAVA", member: "장하은", study:"자바스터디"},
	 {no: 2, pimg:"ee.jpg", img:"study2.jpeg", content: "위스터디최고임다", rating: 5, createdDate: "2018-07-25", category: "정처기", member: "문선민", study:"정처기스터디"},
	 {no: 3, pimg:"ee.jpg", img:"review-img.jpg", content: "위스터디최고", rating: 4, createdDate: "2018-07-24", category: "JAVA", member: "장하은", study:"자바스터디"},
	 {no: 4, pimg:"ee.jpg", img:"study2.jpeg", content: "위스터디최고임다", rating: 5, createdDate: "2018-07-25", category: "정처기", member: "문선민", study:"정처기스터디"},
	 {no: 5, pimg:"ee.jpg", img:"review-img.jpg", content: "위스터디최고", rating: 4, createdDate: "2018-07-24", category: "JAVA", member: "장하은", study:"자바스터디"},
	 {no: 6, pimg:"ee.jpg", img:"study2.jpeg", content: "위스터디최고임다", rating: 5, createdDate: "2018-07-25", category: "정처기", member: "문선민", study:"정처기스터디"},
 ]
  
 var list_group = $('.reviewbox');
 
 var i = 1;
 
 
 
 
 
 for(var item of rv) {
	 $('<li class="list-group-item">' +
        '<a href="#"><img src="img/'+ item.pimg +'"  width="50rem" height="50rem" class="rounded-circle prof-img" alt="logo"></a>'+
        '<div id="user_inform">' +
            '<div id="user_name"><a href="#">'+ item.member +'</a></div>'+
            '<div class="user_score">'+
                '<select id="review-score'+ i +'">'+
                  '<option value="1">1</option>'+
                  '<option value="2">2</option>'+
                  '<option value="3">3</option>'+
                  '<option value="4">4</option>'+
                  '<option value="5">5</option>'+
                '</select>'+
            '</div>'+
            '<div class="user_class">'+ item.category +'</div>'+
        '</div>'+
        '<form>'+
            '<div class="form-group">'+
                '<textarea class="form-control review-form" id="user-review" rows="4" readonly>'+ item.content +'</textarea>'+
            '</div>'+
        '</form>'+
        '<span class="review_date">'+ item.createdDate +'</span>'+
            '<div class="user_class_info">'+
                '<div class="user_class_name"><a href="groupMain.html">'+ item.study +'</a> <span class="study-link">스터디</span></div>'+
                    '<img src="img/'+ item.img +'"  class="img-fluid rounded-circle study-prof" alt="logo">'+
            '</div>'+
            '<hr class="review-line">'+
    '</li>').appendTo(list_group);
	
		 <!-- 별점 --> 	
		 $('#review-score' + i).barrating({
		      theme: 'css-stars',
		      readonly: true
		    });
		
	 i++;
 }
