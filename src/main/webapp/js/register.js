var idCheck = 0;
var pwdCheck = 0;
var telChectk = 0;

$(document).ready(function() {
   $("#header").load(serverRoot + "/header.html");
    
    $(signup).click(function() {
        let in_email = $(email);
        let pass = $(password);
        let checkpass = $(checkpassword);
        let nm = $(memname);
        let t = $(tel);
        let city = $("#city option:checked").text();
        let county = $("#county option:checked").text();
        let interest = [];
        
        if( $(in_email).val() == "" || $(pass).val() == "" || $(checkpass).val() == ""
                        || $(nm).val() == "" || $(tel).val() == "" || $(city).val() == "" || $(county).val() == "" ) {
           swal({
              type: 'error',
              title:  '입력되지 않은 항목이 있습니다.'
            })
            return;
        }
        
       

        for(let c of $('input[type=checkbox]')) {
            if($(c).is(":checked"))
                interest.push( decodeURIComponent( $('.buttons label[for=' + $(c).attr("id") + ' ]').text() ));
        }
        
        $.post(serverRoot+"/json/member/add", {
            email:decodeURIComponent( $(email).val() ),
            password:decodeURIComponent($(password).val() ),
            name:decodeURIComponent($(memname).val() ),
            tel:decodeURIComponent($(t).val() ),
            city:decodeURIComponent(city),
            county:decodeURIComponent(county),
            interests:interest
        }, res => {
           console.log("email : " + $(email).val());
           console.log("password : " + $(password).val());
           console.log("name  : " + $(memname).val());
           console.log("tel : " + $(t).val() );
           console.log("city : " + city);
           console.log("county : " + county);
           console.log("interests : " + interest);
           
           location.href= serverRoot + "index.html"
         });
    });
});

// 관심사 체크 제한
function count_ck(obj){
    var chkbox = document.getElementsByName("chk");
    var chkCnt = 0;
    for(var i = 0; i < chkbox.length; i++){
        if(chkbox[i].checked){
            chkCnt++;
        }
    }
    if(chkCnt>3){
       swal({
           type: 'error',
           title:  '3개 이상 선택할 수 없습니다.'
         })
        obj.checked = false;
        return false;
    }
}
    
    
function changes(fr) {
    if(fr==1) {
        //뿌려줄값을 배열로정렬
        num = new Array("서울 전체","강남구","강동구", "강북구", "강서구", 
                "관악구", "광진구", "구로구", "금천구", "노원구",
                "도봉구", "동대문구", "동작구", "마포구", "서대문구",
                "서초구", "성동구", "성북구", "송파구", "양천구",
                "영등포구", "용산구", "은평구", "종로구", "중구", "중랑구");
        vnum = new Array(
                "1","2","3","4","5","6","7","8","9","10",
                "11","12","13","14","15","16","17","18","19","20",
                "21","22","23","24","25","26");
    } else if(fr==2) {
        num = new Array("부산광역시 전체", "강서구", "금정구", "기장군", "남구",
                        "동구", "동래구", "부산진구", "북구", "사상구",
                        "사하구", "서구", "수영구", "연제구", "영도구",
                        "중구", "해운대구");
        vnum = new Array(
                "1","2","3","4","5","6","7","8","9","10",
                "11","12","13","14","15","16","17");
    } else if(fr==3) {
        num = new Array("대구광역시 전체", "남구", "달서구", "달성군", "동구",
                "북구", "서구", "수성구", "중구");
        vnum = new Array(
                "1","2","3","4","5","6","7","8","9");
    } else if(fr==4) {
        num = new Array("인천광역시 전체", "강화군", "계양구", "남동구", "동구",
                "미추홀구", "부평구", "서구", "연수구", "옹진군",
                "중구");
        vnum = new Array(
                "1","2","3","4","5","6","7","8","9","10",
                "11");
    } else if(fr==5) {
        num = new Array("광주광역시 전체", "광산구", "남구", "동구", "북구",
                "서구");
        vnum = new Array("1","2","3","4","5","6");
    } else if(fr==6) {
        num = new Array("대전광역시 전체", "대덕구", "동구", "서구", "유성구",
        "중구");
        vnum = new Array("1","2","3","4","5","6");
    } else if(fr==7) {
        num = new Array("울산광역시 전체", "남구", "동구", "북구", "울주군",
        "중구");
        vnum = new Array("1","2","3","4","5","6");
    } else if(fr==8) {
        num = new Array("세종특별자치시 전체");
        vnum = new Array("1");
    } else if(fr==9) {
        num = new Array("경기도 전체","가평군","고양시 덕양구", "고양시 일산동구", "고양시 일산서구", 
                "과천시", "광명시", "광주시", "구리시", "군포시",
                "김포시", "남양주시", "동두천시", "부천시", "성남시 분당구",
                "성남시 수정구", "성남시 중원구", "수원시 권선구", "수원시 영통구", "수원시 장안구",
                "수원시 팔달구", "시흥시", "안산시 단원구", "안산시 상록구", "안성시", 
                "안양시 동안구", "안양시 만안구", "양주시", "양평군", "여주시",
                "연천군", "오산시", "용인시 기흥구", "용인시 수지구", "용인시 처인구",
                "의왕시", "의정부시", "이천시", "파주시", "평택시",
                "포천시", "하남시", "화성시");
        vnum = new Array("1","2","3","4","5","6","7","8","9","10",
                "11","12","13","14","15","16","17","18","19","20",
                "21","22","23","24","25","26","27","28","29","30",
                "31","32","33","34","35","36","37","38","39","40",
                "43", "44", "45");
    } else if(fr==10) {
        num = new Array("강원도 전체", "강릉시", "고성군", "동해시", "삼척시", 
                "속초시", "양구군", "양양군", "영월군", "원주시",
                "인제군", "정선군", "철원군", "춘천시", "태백시",
                "평창군", "홍천군", "화천군", "횡성군");
        vnum = new Array("1","2","3","4","5","6","7","8","9","10",
                "11","12","13","14","15","16","17","18","19");
    } else if(fr==11) {
        num = new Array("충청북도 전체", "괴산군", "단양군", "보은군", "영동군", 
                "옥천군", "음성군", "제천시", "증평군", "진천군",
                "청주시 상당구", "청주시 서원구", "청주시 청원구", "청주시 흥덕구", "충주시");
        vnum = new Array("1","2","3","4","5","6","7","8","9","10",
                "11","12","13","14","15");
    } else if(fr==12) {
        num = new Array("충청남도 전체", "계룡시", "공주시", "금산군", "논산시", 
                "당진시", "보령시", "부여군", "서산시", "서천군",
                "아산시", "예산군", "천안시 동남구", "천안시 서북구", "청양군",
                "태안군", "홍성군");
        vnum = new Array("1","2","3","4","5","6","7","8","9","10",
                "11","12","13","14","15","16","17");
    } else if(fr==13) {
        num = new Array("전라북도", "고창군", "군산시", "김제시", "남원시", 
                "무주군", "부안군", "순창군", "완주군", "익산시",
                "임실군", "장수군", "전주시 덕진구", "전주시 완산구", "정읍시",
                "진안군");
        vnum = new Array("1","2","3","4","5","6","7","8","9","10",
                "11","12","13","14","15","16");
    } else if(fr==14) {
        num = new Array("전라남도", "강진군", "고흥군", "곡성군", "광양시", 
                "구례군", "나주시", "담양군", "목포시", "무안군",
                "보성군", "순천시", "신안군", "여수시", "영광군",
                "영암군", "완도군", "장성군", "장흥군", "진도군",
                "함평군", "해남군", "화순군");
        vnum = new Array("1","2","3","4","5","6","7","8","9","10",
                "11","12","13","14","15","16","17","18","19","20",
                "21","22","23");
    } else if(fr==15) {
        num = new Array("경상북도", "경산시", "경주시", "고령군", "구미시", 
                "군위군", "김천시", "문경시", "봉화군", "상주시",
                "성주군", "안동시", "영덕군", "영양군", "영주시",
                "영천시", "예천군", "울릉군", "울진군", "의성군",
                "청도군", "청송군", "칠곡군", "포항시 남구", "포항시 북구");
        vnum = new Array("1","2","3","4","5","6","7","8","9","10",
                "11","12","13","14","15","16","17","18","19","20",
                "21","22","23","24","25");
    } else if(fr==16) {
        num = new Array("경상남도", "거제시", "거창군", "고성군", "김해시",
                "남해군", "밀양시", "사천시", "산청군", "양산시",
                "의령군", "진주시", "창년군", "창원시 마산합포구", "창원시 마산회원구",
                "창원시 성산구", "창원시 의창구", "창원시 진해구", "통영시", "하동군",
                "함안군", "함양군", "합천군");
        vnum = new Array("1","2","3","4","5","6","7","8","9","10",
                "11","12","13","14","15","16","17","18","19","20",
                "21","22","23");
    } else if(fr==17) {
        num = new Array("제주특별자치도", "서귀포시", "제주시");
        vnum = new Array("1","2");
    }
  
  // 셀렉트안의 리스트를 기본값으로 한다..
  for(i=0; i<form.option2.length; i++) {
    form.option2.options[0] = null;
  }
  //포문을 이용하여 두번째(test2)셀렉트 박스에 값을 뿌려줍니당)
  for(i=0;i < num.length;i++) {
    document.form.option2.options[i] = new Option(num[i],vnum[i]);
  }
  
  checkPwd();
  checkId();
}

// 비밀번호 확인
function checkPwd(){
   let inputed = $(password).val();
   let reinputed = $(checkpassword).val();

   if(inputed != reinputed){
      $(".repwd").css("border", "1px solid red");
      $("#chkMsg").css("display", "block");
      $("#signup").prop("disabled", true);
   } else if (inputed == reinputed){
      $(".repwd").css("border", "1px solid #ced4da");
      $("#chkMsg").css("display", "none");
      pwdCheck= 1 ;
      if(idCheck == 1 && pwdCheck == 1){
         $("#signup").prop("disabled", false);
         $("#signup").css("background-color", "#EF6C00");
      }
   }
}
//ID 중복확인
function checkId(){
   var inputed = $("#email").val();
   $.ajax({
      url:"/FinalProject/json/member/checkId",
      method:"POST",
      data:{"email" : inputed},
      dataType:"json",
      success:function(data){
         if(inputed=="" && data=='0'){ // 데이터 입력X
            $(".repwd").css("border", "1px solid red");
            $("#idMsg").css("display", "block");
            $("#signup").prop("disabled", true);
            idCheck = 0;
         } else if(data == '0'){ // 이메일중복X
        	 $("#idMsg").css("display", "none");
            idCheck = 1;
            if(idCheck == 1 && pwdCheck == 1){
               $("#signup").prop("disabled", false);
               $("#signup").css("background-color", "#EF6C00");
            }
         } else if(data == '1'){ // 이메일중복
            $(".repwd").css("border", "1px solid red");
            $("#idMsg").css("display", "block");
            $("#signup").prop("disabled", true);
            idCheck = 0;
         }
         }
   });
}