<!DOCTYPE html>
<html>
<head>
<#include "/templates/layout/meta.ftl">
    <link href="/static/css/plugins/jquery-treegrid/css/jquery.treegrid.css" rel="stylesheet">
    <link href="/static/css/plugins/jsTree/style.min.css" rel="stylesheet">
    <style>
        .tree-panel {
            position: absolute;
            z-index: 999;
            display: none;
            border: 1px solid #e5e6e7;
            left: 15px;
            top: 32px;
            overflow-y: auto;
            background-color: #fafbfc;
            margin-top: 2px;
        }

        .tree-input {
            background-color: #ffffff !important;
        }
    </style>
</head>
<body>
<div id="wrapper">
    <#include "/templates/layout/left.ftl">
    <div id="page-wrapper" class="gray-bg">
        <#include "/templates/layout/head.ftl">
        <div class="row wrapper border-bottom white-bg page-heading">
            <div class="col-lg-9">
                <h2>菜单管理</h2>
                <ol class="breadcrumb">
                    <li>
                        <a href="/main">首页</a>
                    </li>
                    <li class="active">
                        <strong>列表</strong>
                    </li>
                </ol>
            </div>
            <div class="col-lg-2">

            </div>
        </div>
        <div class="wrapper wrapper-content">
            <div class="row">
                <div class="col-lg-12">
                    <div class="ibox ">
                        <div class="ibox-content">

                            <div class="bar operate-bar">
                                <button class="btn btn-sm btn-primary" onclick="Menu.create()">新增</button>
                            </div>

                            <div class="bar table-bar">
                                <table id="menuTable"></table>
                            </div>


                        </div>
                    </div>
                </div>
            </div>
        </div>
        <#include "/templates/layout/foot.ftl">
    </div>
</div>
<#include "/templates/layout/commonjs.ftl">
<#--这是bootstrap treegrid用到的js-->
<script src="/static/js/plugins/jquery-treegrid/js/jquery.treegrid.min.js"></script>
<script src="/static/js/plugins/jquery-treegrid/js/jquery.treegrid.bootstrap3.js"></script>
<script src="/static/js/plugins/jquery-treegrid/extension/jquery.treegrid.extension.js"></script>
<script src="/static/js/plugins/jquery-treegrid/tree-table-object.js"></script>
<#--jstree-->
<script src="/static/js/plugins/jsTree/jstree.min.js"></script>
<script src="/static/management/memu/memu.js"></script>
</body>
</html>
