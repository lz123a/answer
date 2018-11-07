
function finishOd(xx) {
    var x = xx.name;
    //alert(place+" "+tid);
    $.ajax({
        type: 'post',
        url: "/finishOd",
        data:{time:x},
        dataType: "JSON",
        async:true,
        success:function (data) {
            if(data.success==true){
                alert('完成答疑');
                window.location.reload()
                //  $(location).reload();
            }else{
                alert(data.message);
            }
        }
    })
}