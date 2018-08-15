  
$(document).ready(function() {
     $("#header").load(serverRoot + "/header.html");
   $.get("json/auth/loginstat", {}, res=> {
       no = res.no;
     }).done(function(data){
   
        console.log(no)
         $.get("json/joinedMember/"+no, (data) => {
            
            console.log(data);
            let mystudy = $('.mystudy');
              let ownstudy = $('.ownstudy');
               for(var item of data) {
                  console.log(item);
                  var _chkHtml = 'onclick="Rwrite('+ item.study.no +', this)">후기 작성';
                  if( item.review != null ){
                     _chkHtml = 'onclick="Rview('+ item.study.no +')">후기 보기';
                  }
               
               if (item.grade == 0) {
               $('<div class="studybox">'+
                     '<span class="gg">'+ item.study.category +' 스터디 </span><a href="groupMain.html?no='+ item.studyNo +'"><span id="stdname">\''+ item.study.name +'\'</span></a>'+
                     '<button class="btn btn-secondary btn-sm ttbtn" data-toggle="modal" data-target="#tmodal">관리</button>'+
                     '<a class="identifyingClass" data-toggle="modal" href="#dAD" data-id="my_id_value"><button id="vBtn" class="btn btn-primary btn-sm review"' + _chkHtml + '</button></a>'+
               '</div>').appendTo(mystudy);
                  
              
              
               
               } else if (item.grade == 1) {
                  
                  var _html = '<div><span class="gg">'+ item.study.category +' 스터디 </span><a href="groupMain.html?no='+ item.studyNo +'">'
                  + '<span id="stdname">\''+ item.study.name +'\'</span></a>'
                  + '<button class="btn btn-secondary btn-sm ttbtn" data-toggle="modal" data-target="#tmodal" onclick="Out('+ item.study.no +')">탈퇴</button>'
                  + '<a class="identifyingClass" data-toggle="modal" href="#dAD" data-id="my_id_value">'
                  + '<button id="vBtn" class="btn btn-primary btn-sm review"'
                  + _chkHtml
                  + '</button></a>'
                  + '</div>'
                  
                  $(_html).appendTo(ownstudy);
                  
               }
           }
               
           
    })
    
    
    $('.modal').on('hidden.bs.modal', function (e) {
        console.log('modal reset');
      $(this).find('form')[0].reset();
      $(fContent).html("");
   
      $('#example').barrating('set', 1);
      $(addBtn).css('display', 'block');
      $(closeBtn).css('display', 'block');
      $(updBtn).css('display', 'none');
      $(delBtn).css('display', 'none');

  });
})
        
        
        
        
});





function Rwrite(stdno, node) {
   console.log('*** Rwrite 작성 ');
   console.log("stdno : " + stdno);
   console.log("memno : " + no);
   $('.modal-title').text('후기 작성');
   console.log(node)
    var stdno = stdno;
    $("#addBtn").click(() => {
           $('#dAD').css("display", "none");
             $.ajax({
                type : 'POST',
                url : 'json/review/add',
                data: {
                   category : this.ctg,
                   content: $(fContent).val(),
                   rating : $(example).val(),
                   "member.no": no,
                   "study.no" : stdno
                }
              }).done(function(){
             
                swal({
                 type: 'success',
                    title: '작성 완료!',
                    showConfirmButton: false,
                    timer: 500
                }).then((result) => {
                    location.href="Mystudy.html";
                   console.log(node);
                   $('.modal-backdrop').remove();
                    $($($($($(node).parent()).parent()).children()[3]).children()).css("display", "block");
                   $(node).css("display", "none");
                })
             }).fail(function() {
                $($($($($(node).parent()).parent()).children()[3]).children()).css("display", "block");
                $(node).css("display", "none");
                console.log(node);
             })
           });
}


function Rview(stdno) {
   console.log('*** Rview 보기 ');
     $(addBtn).css('display', 'none');
     $(closeBtn).css('display', 'none');
     $(updBtn).css('display', 'block');
     $(delBtn).css('display', 'block');
     $('.modal-title').text('내 후기');
   console.log("stdno : " + stdno);
   console.log("memno : " + no);
   $.getJSON("json/review/myReview", {
           studyNo : stdno,
           memNo : no
         }, data => {
         $(fContent).text(data.content);
         $(example).barrating('set', data.rating);
       }).done(function(data){
               console.log(data.no);
               console.log(data);
                    rvwno = data.no;
                    
                  $("#updBtn").click(() => {
                $.post("json/review/update", {
                        content: $(fContent).val(),
                        rating : $(example).val(),
                        no: rvwno
                        
                }, () => {
                   location.href = "Mystudy.html";
                });
             });
                  
                 console.log(rvwno);
                  $("#delBtn").click(() => {
                    $.get("json/review/delete", {
                          no : rvwno
                    }, () => {
                       location.href = "Mystudy.html";
                    });
                 });
                  
            });
   
   
         
      
}



function Out(stdno) {
     const swalWithBootstrapButtons = swal.mixin({
        confirmButtonClass: 'btn btn-primary',
        cancelButtonClass: 'btn btn-secondary',
        buttonsStyling: false,
      })
      swalWithBootstrapButtons({
           title: '스터디를 탈퇴하시겠습니까?',
           type: 'warning',
           showCancelButton: true,
           cancelButtonText: '아니요!',
           confirmButtonText: '네 탈퇴할래요!',
           reverseButtons: true
         }).then((result) => {
           if (result.value) {
                $.ajax({
                   url: 'json/joinedMember/update',
                 type: 'POST',
                 data:{
                             grade : '2',
                             studyNo : stdno,
                             memNo : no
                 }
                    
           })
             swalWithBootstrapButtons({
             title: '탈퇴 완료!',
               text: '새로운 스터디를 찾아보세요!',
               type: 'success'
             }).then(function(){
                location.href="Mystudy.html"
               }
             )
           }  
         })

}