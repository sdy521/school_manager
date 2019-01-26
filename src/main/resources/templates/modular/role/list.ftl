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
                <h2>权限设置</h2>
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
                    <div class="ibox ">
                        <div class="ibox-content">
                            <div class="bar search-bar">
                                <div class="form-inline">
                                    <input id="name" type="text" class="form-control" placeholder="请输入姓名...">
                                    <button class="btn btn-primary"onclick="Role.search();">搜索</button>
                                    <button class="btn btn-primary"onclick="Role.reset();">重置</button>
                                </div>
                            </div>
                            <div class="jqGrid_wrapper" style="margin-top: 10px;">
                            <#--jqgrid 表格栏-->
                                <table id="grid-table"></table>
                            </div>
                        </div>
                    </div>
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
<#--新增弹框-->
<div class="modal fade" id="roleModal" tabindex="-1" role="dialog" aria-labelledby="modalTitle" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="modalTitle">授权</h4>
            </div>
            <div class="modal-body col-md-offset-3">
                <form class="form-horizontal" id="role-form">
                    <input type="hidden" id="userid" />
                    <div class="bs-example">
                        <div class="btn-group" data-toggle="buttons">
                            <label class="btn btn-primary" id="role1">
                                <input type="radio" name="roleid" value="1"> 管理员
                            </label>
                            <label class="btn btn-primary" id="role2">
                                <input type="radio" name="roleid" value="2"> 教师
                            </label>
                            <label class="btn btn-primary" id="role3">
                                <input type="radio" name="roleid" value="3"> 学生
                            </label>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-primary" onclick="Role.update();">确定</button>
                <button type="button" class="btn btn-sm btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<#include "/templates/layout/commonjs.ftl">
<script src="/static/modular/role/role.js"></script>
</body>
</html>
