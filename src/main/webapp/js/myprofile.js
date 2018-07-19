	var no = location.href.split("?")[1].split("=")[1];
	
	$.getJSON(serverRoot + "/myProfile.html?" + no, function(data) {
        $(fNo).text(data.no); 
        $(fName).text(data.name); 
        $(fEmail).text(data.email); 
        $(fPassword).text(data.password); 
        $(fBirthDate).text(data.birthDate); 
        $(fGender).text(data.gender); 
        $(fLocation).text(data.location); 
        $(fProfile).text(data.profile); 
	});
	
	$("#updBtn").click(() => {
		$.post(serverRoot + "/myProfile/update", {
			no: $(fNo).val(),
			email: $(fEmail).val(),
			password: $(fPassword).val()
		}
	});
	