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
//信息框
function info(msg) {
    swal(msg);
}
//错误弹窗
function error(msg,substr) {
    swal(msg, substr, "error");
}