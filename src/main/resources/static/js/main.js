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
        $("#resultDiv").replaceWith(fragment);
    });
}