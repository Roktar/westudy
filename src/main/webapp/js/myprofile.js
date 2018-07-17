if (location.href.split("?").length > 1) {
	var tags = $(".input-view");
    tags.css("display", "none");

    var no = location.href.split("?")[1].split("=")[1];
	
    $.getJSON(serverRoot + "/json/member/" + no, function(data) {
        $(fNo).val(data.no); 
        $(fName).val(data.name); 
        $(fEmail).val(data.email); 
        $(fPassword).val(data.password); 
        $(fBirthDate).val(data.birth); 
        $(fGender).val(data.gender); 
        $(fLocation).val(data.interestedLocation); 
        $(fProfile).val(data.introduction); 
    });
	
    $("#updBtn").click(() => {
        $.post(serverRoot + "/json/member/update", {
            no: $(fNo).val(),
            email: $(fEmail).val(),
            password: $(fPassword).val()
        });
    })
}
