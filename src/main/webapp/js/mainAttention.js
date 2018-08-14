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
                     $('#s4name').text(data[3].study.name);
                     $('#s5name').text(data[4].study.name);
                     $('#s6name').text(data[5].study.name);
                     
                     
                     $('#s1img').attr('src', 'files/' + data[0].study.picture + '_350x350.jpg');
                     $('#s2img').attr('src', 'files/' + data[1].study.picture + '_350x350.jpg');
                     $('#s3img').attr('src', 'files/' + data[2].study.picture + '_350x350.jpg');
                     $('#s4img').attr('src', 'files/' + data[3].study.picture + '_350x350.jpg');
                     $('#s5img').attr('src', 'files/' + data[4].study.picture + '_350x350.jpg');
                     $('#s6img').attr('src', 'files/' + data[5].study.picture + '_350x350.jpg'); 
                     
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