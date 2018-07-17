
// Dropzone 초기화, myDropzone form 있는 id 위해

Dropzone.options.myDropzone = {

	// 업로드 그림 경로 지정

	url : '@Url.Action("dropZone", "Home")',

	// 파라메터 지정

	// params: {"Data" : xx},

	// 업로드 취소 및 추가 삭제 미리 보기 그림 링크 를 기본 안 추가

	addRemoveLinks : true,

	// 자동 업로드 끔

	autoProcessQueue : false,

	// 최대 업로드 카운트

	maxFiles : 20,

	// 여러 개의 사진 업로드 허용

	uploadMultiple : true,

	// 병렬처리 WebConfig도 같이 수정해줘야함.

	parallelUploads : 20,

	// 최대 업로드 용량 Mb 단위

	maxFilesize : 100,

	method : 'post',

	// 허용 확장자

	acceptedFiles : ".jpeg,.jpg,.png,.gif,.JPEG,.JPG,.PNG,.GIF",

	// 기본 true false 로 주면 아무 동작 못함

	clickable : true,
	

	// 지원하지 않는 IE 10 미만일 경우

	fallback : function() {
		document.getElementById('trueForm').style.display = "none";

		document.getElementById('falseForm').style.display = 'block';
	},

	init : function() {

		var submitButton = document.querySelector("#btnSubmit")

		var allClearButton = document.querySelector("#btnAllClear")

		myDropzone = this; // closure

		// Call the action method to load the images from the server
		// 서버로부터 이미지를 로드하기 위한 액션 메소드 호출
		$.getJSON("/PhotoController").done(function(data) {

			if (data.Data != '') {

				$.each(data.Data, function(index, item) {

					var mockFile = {

						name : item.fileName, // 마우스 오버 네임

						size : item.Size

					};

					myDropzone.emit("addedfile", mockFile);

					myDropzone.emit("thumbnail", mockFile, item.Path);

				});

			}

		});

		// 업로드 버튼 이벤트 등록

		submitButton.addEventListener("click", function() {
			// 다른 폼의 데이터를 Dropzone 안의 히든 폼으로 주고 서브밋 시키면 Form 2개 데이터를 취합할 수 있다.

			$('#hideTxtData').attr('value', $('#txtData').val());

			myDropzone.processQueue();

		});

		// All Clear

		allClearButton.addEventListener("click", function() {

			if (confirm('정말 전체 항목을 삭제하겠습니까?')) {

				// 서버 삭제 로직

				$.ajax({

					url : '@Url.Action("removeAll","Home")',

					type : 'POST',

					success : function(data) {

						if (data.Data == 'OK') {

							myDropzone.removeAllFiles(true); // true 주면 업로드
																// 중인 파일도 다 같이
																// 삭제

						}

					}

				});

			}

		});
		// maxFiles 카운터를 초과하면 경고창

		this.on("maxfilesexceeded", function(data) {

			alert('최대 업로드 파일 수는 20개 입니다.');

		});

		// Dropzone에 이미지가 추가되면 업로드 버튼 활성화

		this.on("addedfile", function() {

			$("#btnSubmit").removeAttr("disabled");

			$("#btnAllClear").removeAttr("disabled");

		});

		// 업로드 완료 후 사건 내가 받아들일 위해 JSON 데이터 형식

		this.on("complete", function(data) {

			if (this.getUploadingFiles().length === 0
					&& this.getQueuedFiles().length === 0) {

				if (data.xhr != undefined) {

					var res = eval('(' + data.xhr.responseText + ')');

					// var res = data;

					var msg;

					if (res.Result) {

						msg = "이미지 업로드 완료( " + res.Count + " )";

					}

					else {

						msg = "업로드 실패: " + res.Message;

					}

					alert(msg);

				}

			}

		});

		// 이미지 삭제

		this.on("removedfile", function(data) {

			// alert(data.name); // 삭제할 파일명

			// 서버 삭제 로직

			$.ajax({

				url : '@Url.Action("removeTarget","Home")',

				type : 'POST',

				data : {
					'PK' : data.name
				}

			});

			if (this.getAcceptedFiles().length === 0) {

				$("#btnSubmit").attr("disabled", true);
				
				$("#btnAllClear").attr("disabled", true);
			
			}

		});

	}

};




