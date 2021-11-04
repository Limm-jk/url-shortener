function dataSend() {

    var data=$("#input").val();

    var messageDTO={
        url:data
    };

    $.ajax({
        url: "/shorten",
        data: messageDTO,
        type:"POST",
    }).done(function (fragment) {
        top.alert("Short Url이 성공적으로 생성되었습니다.");
        $("#resultDiv").replaceWith(fragment);
    });
}