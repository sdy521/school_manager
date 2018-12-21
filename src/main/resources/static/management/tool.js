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