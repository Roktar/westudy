"use strict"

$('#fileupload').fileupload({
  url: '../java/bitcamp/java106/pms/web/json/photo/add',        // 서버에 요청할 URL
  dataType: 'json',         // 서버가 보낸 응답이 JSON임을 지정하기
  sequentialUploads: true,  // 여러 개의 파일을 업로드 할 때 순서대로 요청하기.
  singleFileUploads: false, // 한 요청에 여러 개의 파일을 전송시키기.   
  add: function (e, data) {
    console.log('add()...');
    $.each(data.files, function (index, file) {
        console.log('Added file: ' + file.name);
    });
    $('#btnSubmit').click(function() {
        data.submit(); // submit()을 호출하면, 서버에 데이터를 보내기 전에 submit 이벤트가 발생한다.
        console.log("click");
    });
  },
  done: function (e, data) { // 서버에서 응답이 오면 호출된다. 각 파일 별로 호출된다.
    console.log('done()...');
    console.log(data.result);
    var file = data.result.files;
    $('<p/>').text("name : " + data.result.name).appendTo(document.body);
    
    $.each(data.result.files, function(index, file) {
      $('<p/>').text(file.filename + " : " + file.filesize).appendTo(document.body);
    });
  },
  submit: function (e, data) { // submit 이벤트가 발생했을 때 호출됨. 서버에 전송하기 전에 호출됨.
    console.log('submit()...');
    // data 객체의 formData 프로퍼티에 일반 파라미터 값을 설정한다.
    data.formData = {
        name: $('#name').val(),
        
    };
  }
}); 