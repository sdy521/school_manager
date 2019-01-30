<!DOCTYPE html>
<html>
<head>
<#include "/templates/layout/meta.ftl">
    <!-- fontIconPicker Style -->
    <link rel="stylesheet" href="/static/plugins/font-awesome-jquery-fontIconPicker/jquery-fontIconPicker/css/jquery.fonticonpicker.min.css" />
    <link rel="stylesheet" href="/static/plugins/font-awesome-jquery-fontIconPicker/jquery-fontIconPicker/themes/bootstrap-theme/jquery.fonticonpicker.bootstrap.min.css" />
    <!-- Font -->
    <link rel="stylesheet" type="text/css" href="/static/plugins/font-awesome-jquery-fontIconPicker/font-awesome/css/font-awesome.min.css" />
    <!-- treegrid -->
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
                <h2>菜单设置</h2>
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
                                <button class="btn btn-sm btn-primary" onclick="Menu.create();">新增</button>
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
<#--新增弹框-->
<div class="modal fade" id="createModal" tabindex="-1" role="dialog" aria-labelledby="modalTitle" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="modalTitle">新增</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="create-form">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">菜单名称</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">url</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="url">
                            <span class="help-block m-b-none">注：如果是一级菜单，url填#</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">code</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="code">
                            <span class="help-block m-b-none">注：code必须唯一</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">父菜单</label>
                        <div class="col-sm-9">
                            <input type="text" id="create-menu-input" class="tree-input form-control" readonly="readonly" name="pcode" data-code="">
                            <span class="help-block m-b-none">注：如果是一级菜单则不选</span>
                            <div id="create-menu-panel" class="tree-panel">

                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">图标</label>
                        <div class="col-sm-9">
                            <input type="text" id="picker" name="icon"/>
                            <span class="help-block m-b-none">注：不选默认为<i class="fa fa-th-large"></i></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">权限</label>
                        <div class="col-sm-9">
                            <select name="type" class="form-control" style="width: 100px;">
                                <option value="0" selected>管理员</option>
                                <option value="1">教师</option>
                                <option value="2">学生</option>
                            </select>
                            <span class="help-block m-b-none">注：管理员能看到全部菜单，其他权限只能看到该权限下的菜单</span>
                        </div>
                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-primary" onclick="Menu.insert();">确定</button>
                <button type="button" class="btn btn-sm btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<#--编辑弹框-->
<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="modalTitle" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="modalTitle">新增</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="update-form">
                    <input type="hidden" class="form-control" name="id">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">菜单名称</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">url</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="url">
                            <span class="help-block m-b-none">注：如果是一级菜单，url填#</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">code</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="code">
                            <span class="help-block m-b-none">注：code必须唯一</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">父菜单</label>
                        <div class="col-sm-9">
                            <input type="text" id="update-menu-input" class="tree-input form-control" readonly="readonly" name="pcode" data-code="">
                            <span class="help-block m-b-none">注：如果是一级菜单则不选</span>
                            <div id="update-menu-panel" class="tree-panel">

                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">图标</label>
                        <div class="col-sm-9">
                            <input type="hidden" name="iconbackup"/>
                            <i id="fa" class=""></i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <input type="text" id="picker2" name="icon"/>
                            <span class="help-block m-b-none">注：左边为当前使用图标</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">权限</label>
                        <div class="col-sm-9">
                            <select name="type" class="form-control" style="width: 100px;">
                                <option value="0" selected>管理员</option>
                                <option value="1">教师</option>
                                <option value="2">学生</option>
                            </select>
                            <span class="help-block m-b-none">注：管理员能看到全部菜单，其他权限只能看到该权限下的菜单</span>
                        </div>
                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-primary" onclick="Menu.update();">确定</button>
                <button type="button" class="btn btn-sm btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<#include "/templates/layout/commonjs.ftl">
<#--这是bootstrap treegrid用到的js-->
<script src="/static/js/plugins/jquery-treegrid/js/jquery.treegrid.min.js"></script>
<script src="/static/js/plugins/jquery-treegrid/js/jquery.treegrid.bootstrap3.js"></script>
<script src="/static/js/plugins/jquery-treegrid/extension/jquery.treegrid.extension.js"></script>
<script src="/static/js/plugins/jquery-treegrid/tree-table-object.js"></script>
<#--jstree-->
<script src="/static/js/plugins/jsTree/jstree.min.js"></script>
<script src="/static/modular/memu/memu.js"></script>
<!-- icon picker -->
<script type="text/javascript" src="/static/plugins/font-awesome-jquery-fontIconPicker/jquery-fontIconPicker/jquery.fonticonpicker.min.js"></script>
<!-- font-awesome icon source -->
<script type="text/javascript" src="/static/plugins/font-awesome-jquery-fontIconPicker/fa-icon-source.js" ></script>
</body>
</html>
