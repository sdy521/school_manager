// 格式化时间  参数为Date类型
function setDateFormat(myDate) {
    var year = myDate.getFullYear();
    var month = myDate.getMonth() + 1;
    month = (month.toString().length == 1) ? ("0" + month) : month;
    var day = myDate.getDate();
    day = (day.toString().length == 1) ? ("0" + day) : day;
    var hours = myDate.getHours();
    hours = (hours.toString().length == 1)?("0"+hours):hours;
    var minutes = myDate.getMinutes();
    minutes = (minutes.toString().length == 1)?("0"+minutes):minutes;
    var seconds = myDate.getSeconds();
    seconds = (seconds.toString().length == 1)?("0"+seconds):seconds;
    var result = year + '-' + month + '-' + day + ' '+ hours + ':' + minutes + ':' + seconds //当前日期
    return result;
}
//获取表单下的值得JSON对象
function getFormJson(frm) {
    var o = {};
    var a = $(frm).serializeArray();//表单对象数组 [{name:xxx,value:xxx},{name:xxx,value:xxx},......]
    $.each(a, function () {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';// ||表示 this.value没有值取后面‘’
        }
    });

    return o;
}
//成功弹窗
function success(msg,substr) {
    swal({
        title: msg,
        text: substr,
        type: "success"
    });
}
//警告弹窗
function warning(msg,substr,fn) {
    swal({
        title: msg,
        text: substr,
        type: "warning",
        // timer:5000,//自动关闭弹窗，如果不关闭时间一到就自动执行function,单位毫秒
        showCancelButton: true,
        closeOnConfirm: false,
        // animation:"slide-from-top",//提示框弹出时的动画效果
        cancelButtonText:"取消",
        confirmButtonText: "是的，我要删除",
        confirmButtonColor: "#ec6c62"
    },fn);
}
function recoverWarning(msg,substr,fn) {
    swal({
        title: msg,
        text: substr,
        type: "warning",
        // timer:5000,//自动关闭弹窗，如果不关闭时间一到就自动执行function,单位毫秒
        showCancelButton: true,
        closeOnConfirm: false,
        // animation:"slide-from-top",//提示框弹出时的动画效果
        cancelButtonText:"取消",
        confirmButtonText: "是的，我要恢复",
        confirmButtonColor: "#f3a50d"
    },fn);
}
function init(msg,substr,fn) {
    swal({
        title: msg,
        text: substr,
        type: "warning",
        // timer:5000,//自动关闭弹窗，如果不关闭时间一到就自动执行function,单位毫秒
        showCancelButton: true,
        closeOnConfirm: false,
        // animation:"slide-from-top",//提示框弹出时的动画效果
        cancelButtonText:"取消",
        confirmButtonText: "是的，我要重置",
        confirmButtonColor: "#0db348"
    },fn);
}
//信息框
function info(msg) {
    swal(msg);
}
//错误弹窗
function error(msg,substr) {
    swal(msg, substr, "error");
}

//switchery
function setSwitchery(switchElement, checkedBool) {
    if ((checkedBool && !switchElement.isChecked()) || (!checkedBool && switchElement.isChecked())) {
        switchElement.setPosition(true);
        switchElement.handleOnchange(true);
    }
}
//用户编辑
function leftModel() {
    $.ajax({
        url:"/leftmodal?id="+$("#left-form").find("input[name='userid']").val(),
        type:"GET",
        dataType:"JSON",
        success:function (r) {
            if(r.code===0){
                var data = r.obj;
                $("#left-form").find("input[name='name']").val(data.name);
                $("#leftModal").modal();
            }
        }
    });
}
function update() {
    var id = $("#left-form").find("input[name='userid']").val();
    var name = $("#left-form").find("input[name='name']").val();
    $.ajax({
        url:"/leftupdate?id="+id+"&name="+name,
        type:"GET",
        dataType:"JSON",
        success:function (r) {
            if(r.code===0){
                $("#leftModal").modal('hide');
                success("修改成功");
            }
        }
    });
}