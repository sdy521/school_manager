<!DOCTYPE html>
<html>
<head>
<#include "/templates/layout/meta.ftl">
<#--可拖拽面板-->
<link href="/static/js/plugins/jquery-ui/jquery-ui.min.css" rel="stylesheet">
<#--日期-->
<link href="/static/css/plugins/datatimepicker/bootstrap-datetimepicker.min.css" rel="stylesheet">
</head>
<body>
<div id="wrapper">
    <#include "/templates/layout/left.ftl">
    <div id="page-wrapper" class="gray-bg">
        <#include "/templates/layout/head.ftl">
        <div class="wrapper wrapper-content">
            <div class="row">
                <div class="col-lg-12">
                    <div class="bar search-bar">
                        <div class="form-inline">
                            <div class="form-group">
                                <#--<label>&nbsp;&nbsp;日期：</label>-->
                                &nbsp;&nbsp;
                                <div class="input-group date">
                                        <span class="input-group-addon">
                                            <i class="fa fa-calendar"></i>
                                        </span>
                                    <input type="text" class="form-control" id="dateTime" style="width: 120px;"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="animated fadeInRight">
                        <div class="resizable-panels">
                            <#if listnotice??>
                                <#list listnotice as notice>
                                    <div class="ibox" style="width: 100%;">
                                        <div class="ibox-title">
                                            <h5>${notice.title}</h5>
                                            <div class="ibox-tools">
                                                <label class="label">${notice.ucreateTime?string('yyyy-MM-dd hh:mm:ss')}</label>
                                            </div>
                                        </div>
                                        <div class="ibox-content">
                                            ${notice.content}
                                        </div>
                                    </div>
                                </#list>
                            </#if>
                            <#--<div class="clearfix"></div>-->
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <#include "/templates/layout/foot.ftl">
    </div>
</div>
</body>
<#include "/templates/layout/commonjs.ftl">
<#--可拖拽面板-->
<script src="/static/js/plugins/jquery-ui/jquery-ui.min.js"></script>
<#--日期-->
<script src="/static/js/plugins/datatimepicker/bootstrap-datetimepicker.min.js"></script>
<script>
    //拖拽面板
    $(function () {

        $(".ibox").resizable({
            helper: "ui-resizable-helper",
            grid: 20// 设置调整大小时候x y 轴每次移动多少像素。
        });

        //日期控件
        initDateTimePicker();
    });
    //初始化日期插件
    function initDateTimePicker() {
        var dateTimePickerOption = {
            minView:'month',
            format: 'yyyy-mm-dd',
            todayBtn: true,
            autoclose: true,
            minuteStep: 1
        };
        var dateTime = $('#dateTime');
        //开始日期默认今天零点
        var today = new Date();
        dateTime.val(dateFtt("yyyy-MM-dd",today));
        dateTime.datetimepicker(dateTimePickerOption);
        //结束日期默认明天零点
        dateTimePickerOption.initialDate = today;
    }
    $("#dateTime").on("change",function () {
        var dateTime = $("#dateTime").val();
       $.ajax({
           url:"/selectNotice?dateTime="+dateTime,
           type:"GET",
           dataType:"JSON",
           success:function (r) {
               if(r.code===0){
                   $(".resizable-panels").empty();
                   var data = r.obj;
                   $.each(data,function (i,item) {
                       $(".resizable-panels").append(
                               " <div class=\"ibox\" style=\"width: 100%;\">\n" +
                               "    <div class=\"ibox-title\">\n" +
                               "        <h5>"+item.title+"</h5>\n" +
                               "        <div class=\"ibox-tools\">\n" +
                               "            <label class=\"label\">"+dateFtt("yyyy-MM-dd hh:mm:ss",new Date(item.ucreateTime))+"</label>\n" +
                               "        </div>\n" +
                               "     </div>\n" +
                               "     <div class=\"ibox-content\">"+item.content+"</div>\n" +
                               "  </div>");
                   });
                   $(".ibox").resizable({
                       helper: "ui-resizable-helper",
                       grid: 20
                   });
               }
           }
       });
    });
</script>
</html>
