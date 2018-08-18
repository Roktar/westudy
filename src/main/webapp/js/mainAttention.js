 $(document).ready(function () {
		    /*$("#header").load(serverRoot + "/header.html");*/
      
        	$.get("json/auth/loginstat", {}, res=> {
               memno = res.no; 

             $.getJSON("json/joinedMember/interList", {memNo : memno}, (data) => {
                      console.log(data);
                     
                      /* 관심스터디이름 */
                     $('#s1name').text(data[0].study.name);
                     $('#s2name').text(data[1].study.name);
                     $('#s3name').text(data[2].study.name);
                    
                     
                     $('#s1img').attr('src', 'img/studyImgs/' + data[0].study.picture + '_300x300.jpg');
                     $('#s2img').attr('src', 'img/studyImgs/' + data[1].study.picture + '_300x300.jpg');
                     $('#s3img').attr('src', 'img/studyImgs/' + data[2].study.picture + '_300x300.jpg');
                     
                     
                  /*     /* 관심스터디해시태그 
                     $('.s1tag').text(data[0].hashtag);
                     $('.s2tag').text(data[1].hashtag);
                     $('.s3tag').text(data[2].hashtag);
                     $('.s4tag').text(data[3].hashtag);
                     $('.s5tag').text(data[4].hashtag);
                     $('.s6tag').text(data[5].hashtag); */
                      
                  })
        	})
 })