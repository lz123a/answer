function login() {
    var uid = $("input[name='uid']").val();
    var psword = $("input[name='psword']").val();
    var idenity = 1;
    $.ajax({
        type:"POST",
        url:'/loginteacher',
        data:{"uid":uid,"psword":psword,"idenity":idenity},
        async:true,
        success: function (data) {
            if(data.success==true){
                $(location).attr('href', 'index');

            }else{
                $(location).attr('href', 'login');
                alert(data.message);
            }
        },
        error: function (data) {
            $(location).attr('href', 'login'); alert("Error"); }
    },'json');
}