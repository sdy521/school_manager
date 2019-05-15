<#--模板实例-->
<!DOCTYPE html>
<html>
<head>
<#include "/templates/layout/meta.ftl">
</head>
<body>
<div id="wrapper">
    <#include "/templates/layout/left.ftl">
    <div id="page-wrapper" class="gray-bg">
        <#include "/templates/layout/head.ftl">
        <div class="row wrapper border-bottom white-bg page-heading">
            <div class="col-lg-9">
                <h2>登入日志</h2>
                <ol class="breadcrumb">
                    <li>
                        <a href="/main">首页</a>
                    </li>
                    <li class="active">
                        <strong>列表</strong>
                    </li>
                </ol>
            </div>
        </div>
        <div class="wrapper wrapper-content">
            <div class="row">
                <div class="col-lg-12">
                    <div class="ibox">
                        <table id="grid-table"></table>
                        <div id="grid-pager"></div>
                    </div>
                </div>
            </div>
        </div>
        <#include "/templates/layout/foot.ftl">
    </div>
</div>
<#include "/templates/layout/commonjs.ftl">
<script src="/static/modular/log/login.js"></script>
</body>
</html>
