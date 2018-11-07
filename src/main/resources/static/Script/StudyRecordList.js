var tid;
function agree(xx) {
    tid = xx.name;
    $('#mymodal').modal();
}

function agreeOd() {
    var place = $('#place').val();
    alert(place+" "+tid);
    $.ajax({
        type: 'post',
        url: "/agreeOd",
        data:{time:tid,place:place},
        dataType: "JSON",
        async:true,
        success:function (data) {
            if(data.success==true){
                alert('成功回复预约');
                window.location.reload()
                //  $(location).reload();
            }else{
                alert(data.message);
            }
        }
    })
}




var pageIndex = 1; //当前加载页数
var hasData = true; //是否还有数据可以加载
var yearData = ""; //年份
var monthData = ""; //月.日
var $dt = null; //追加数据的jq对象
function loadData() {
    if (hasData) {
        var stuId = $("#stuId").val();
        if (stuId.length > 1) {
            $.ajax({
                url: "StudyGetData.ashx",
                type: "post",
                data: "stuId=" + stuId + "&pageIndex=" + pageIndex,
                success: function (data) {
                    pageIndex++;
                    var s1 = data.split('$1$')[0];
                    if (s1 == "0") {
                        hasData = false;
                    }
                    var s2 = data.split('$1$')[1];
                    showData(s2);
                }
            });
        }
    }
}
function showData(str) {
    var s3 = str.split('$3$');
    for (i = 0; i < s3.length; i++) {
        var s4 = s3[i].split('$2$');
        if (s4[0] != yearData) {
            var t = "<li><h2 class='first'><a href='#nogo'>" + s4[0] + "年</a></h2></li>";
            $("#hData").append(t);
            yearData = s4[0];
        }
        if (s4[1] != monthData) {
            var $li = $("<li class='green'><h3>" + s4[1] + "<span>" + yearData + "</span></h3></li>");
            var $div = $("<div class='lc_right'></div>");
            var $dl = $("<dl></dl>");
            var $tdiv = $("<div class='time_ico'></div>");
            $dt = $("<dt></dt>");
            $dt.appendTo($dl);
            $tdiv.appendTo($dl);
            $dl.appendTo($div);
            $div.appendTo($li);
            $li.appendTo($("#hData"));
            monthData = s4[1];
        }
        if ($dt) {
            var pathA = s4[6];
            var $span;
            if (pathA.length < 1) {
                $span = $("<span>" + s4[2] + " " + s4[3] + "<a>" + s4[4] + "</a>  " + s4[5] + "</span>");
            } else {
                $span = $("<span>" + s4[2] + " " + s4[3] + "<a href='" + pathA + "' target='_blank'>" + s4[4] + "</a>  " + s4[5] + "</span>");
            }
            $dt.append($span);
        }
    }
}