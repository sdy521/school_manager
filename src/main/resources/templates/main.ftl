<!DOCTYPE html>
<html>
<head>
<#include "/templates/layout/meta.ftl">
<#--可拖拽面板-->
<link href="/static/js/plugins/jquery-ui/jquery-ui.min.css" rel="stylesheet">
</head>
<body>
<div id="wrapper">
    <#include "/templates/layout/left.ftl">
    <div id="page-wrapper" class="gray-bg">
        <#include "/templates/layout/head.ftl">
        <div class="wrapper wrapper-content">

            <div class="wrapper wrapper-content  animated fadeInRight">
                <div class="resizable-panels">
                    <#if listnotice??>
                        <#list listnotice as notice>
                            <div class="ibox" style="width: 100%;height: 50px;">
                                <div class="ibox-title">
                                    <h5>${notice.title}</h5>
                                    <div class="ibox-tools">
                                        <label class="label">${notice.createTime?string('yyyy-MM-dd')}</label>
                                    </div>
                                </div>
                                <div class="ibox-content">
                                   ${notice.content}
                                </div>
                            </div>
                        </#list>
                    </#if>
                    <div class="clearfix"></div>
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
<script>
    $(document).ready(function () {

        $(".ibox").resizable({
            helper: "ui-resizable-helper",
            grid: 20
        });

    });
</script>
</html>
