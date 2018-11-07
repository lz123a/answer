function submitTime() {
    var t = document.getElementById('test5').value;
    $.ajax({
        type: 'post',
        url: "/addTime",
        data:{time:t},
        dataType: "JSON",
        async:true,
        success:function (data) {
            if(data.success==true){
                alert('成功添加时间');
                window.location.reload()
        //        $(location).reload();
            }else{
                alert(data.message);
            }
        }
    })
    alert(t);
}
function del(xx) {

    alert(x);
}

function delTime(xx) {
    var x = xx.name;
    $.ajax({
        type: 'post',
        url: "/delTime",
        data:{time:x},
        dataType: "JSON",
        async:true,
        success:function (data) {
            if(data.success==true){
                alert('成功删除预约');
                window.location.reload()
              //  $(location).reload();
            }else{
                alert(data.message);
            }
        }
    })
    alert(t);
}


function initOneTeacherTime(teacherTime,state,tbodyhtml,ii) {
    trhtml = "<tr>" +"<td>"+ii+"</td>"+"<td>"+teacherTime+"</td>";
    if(state==0){
        trhtml += "<td>未预约</td>"+"<td> <button type='button' class='btn btn-danger'>删除</button> </td>"+"</tr>";
    }
    tbodyhtml += trhtml;

}