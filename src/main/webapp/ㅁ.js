$(document).ready(function() {
    
    var no = -1;
     $.get("json/auth/loginstat", {}, res=> {
         console.log("[" + res + "]" + ", " + typeof(res));
         console.log(res.no);
         no = res.no;
         
     }).done(function(data){
         
        $.get("json/joinedMember/"+no, (data) => {
           let mystudy = $('.mystudy');
           let ownstudy = $('.ownstudy');
           
           console.log(data)
           var i = 1;
           var s = 1;
         
           for(var item of data) {
              if (item.grade == 0) {
              $('<div>'+
                    '<span class="gg">'+ item.study.category +' 스터디 </span><a href="groupMain.html"><span id="stdname">\''+ item.study.name +'\'</span></a>'+
                    '<button class="btn btn-secondary btn-sm ttbtn" data-toggle="modal" data-target="#tmodal">탈퇴</button>'+
                    '<a class="identifyingClass" data-toggle="modal" href="#dAD" data-id="my_id_value"><button class="btn btn-primary btn-sm review" id="rbtn">후기 작성</button></a>'+
              '</div>').appendTo(mystudy);
              } else if (item.grade == 1) {
                 $('<div>'+
                        '<span class="gg">'+ item.study.category +' 스터디 </span><a href="groupMain.html"><span id="stdname">\''+ item.study.name +'\'</span></a>'+
                        '<button class="btn btn-secondary btn-sm ttbtn" data-toggle="modal" data-target="#tmodal">탈퇴</button>'+
                        '<a class="identifyingClass" data-toggle="modal" href="#dAD" data-id="my_id_value"><button class="btn btn-primary btn-sm review" id="rbtn">후기 작성</button></a>'+
                  '</div>').appendTo(ownstudy);
              }
           
         
           }
        
           
           $("#rbtn").click(() => {
             console.log(data);
             console.log(i);
             console.log(s);
             var sno = data[i].studyNo;
             var mno = data[i].memNo;
             var ctg = data[i].study.category;
             console.log(sno);
             console.log(mno);
             
             console.log(ctg);
            $("#addBtn").click(() => {
              $.ajax({
                 type : 'POST',
                 url : 'json/review/add',
                 data: {
                    category : ctg,
                    content: $(fContent).val(),
                    rating : $(example).val(),
                    "member.no": mno,
                    "study.no" : sno
                 },
                 
                 success:function(result){
                 location.href = "review2.html";
              }
              
              });
            });
       
         })
           
        
        })
  });
     

    
     
});





]