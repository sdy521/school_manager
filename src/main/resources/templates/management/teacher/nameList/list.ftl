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
        <div class="wrapper wrapper-content">

            <table id="grid-table"></table>
            <div id="grid-pager"></div>

        </div>
        <#include "/templates/layout/foot.ftl">
    </div>
</div>
<#include "/templates/layout/commonjs.ftl">
<script src="/static/management/teacher/nameList/nameList.js"></script>
</body>
</html>
