/**************************************时间格式化处理************************************/
function dateFtt(fmt,date)
{
    var o = {
        "M+" : date.getMonth()+1,                 //月份
        "d+" : date.getDate(),                    //日
        "h+" : date.getHours(),                   //小时
        "m+" : date.getMinutes(),                 //分
        "s+" : date.getSeconds(),                 //秒
        "q+" : Math.floor((date.getMonth()+3)/3), //季度
        "S"  : date.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt))
        fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    return fmt;
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
//警告弹窗
function warningCommon(msg,substr,fn) {
    swal({
        title: msg,
        text: substr,
        type: "warning",
        // timer:5000,//自动关闭弹窗，如果不关闭时间一到就自动执行function,单位毫秒
        showCancelButton: true,
        closeOnConfirm: false,
        // animation:"slide-from-top",//提示框弹出时的动画效果
        cancelButtonText:"取消",
        confirmButtonText: "确定",
        confirmButtonColor: "#d6030a"
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
        confirmButtonColor: "#1768b3"
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
        url:"/leftmodal",
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
//上传头像
function upImage() {
    $("#uploadModal").modal();
}
function destroyDropZone() {
    $("#uploadModal").modal('hide');
}