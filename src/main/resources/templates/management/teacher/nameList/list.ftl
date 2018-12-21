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
                <h2>教师名单</h2>
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
            <div style="background: white">
                <table id="grid-table"></table>
                <div id="grid-pager"></div>
            </div>
        </div>
        <#include "/templates/layout/foot.ftl">
    </div>
</div>
<#include "/templates/layout/commonjs.ftl">
<script src="/static/management/teacher/nameList/nameList.js"></script>
</body>
</html>
