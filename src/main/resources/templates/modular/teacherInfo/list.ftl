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
                <h2>教师信息</h2>
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
                                    <input id="teacherName" type="text" class="form-control" placeholder="请输入姓名...">
                                    <select id="sex" class="form-control" style="width: 100px;">
                                        <option value="" selected>全部</option>
                                        <option value="1">男</option>
                                        <option value="0">女</option>
                                    </select>
                                    <button class="btn btn-primary"onclick="TeacherInfo.search();">搜索</button>
                                    <button class="btn btn-primary"onclick="TeacherInfo.reset();">重置</button>
                                </div>
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
<#--详情弹框-->
<div class="modal fade" id="detailsModal" tabindex="-1" role="dialog" aria-labelledby="modalTitle" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="modalTitle">详情</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="detail-form">
                    <div class="form-group">
                        <figure style="width: 100px;height: 100px;">
                            <img class="img-circle " src="" alt="未上传头像" width="100px;"/>
                        </figure>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">姓名</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">性别</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="sex">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">年龄</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="age">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">手机号</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="phone">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">住址</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="address">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-primary" data-dismiss="modal">确定</button>
                <#--<button type="button" class="btn btn-sm btn-default" data-dismiss="modal">关闭</button>-->
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<#include "/templates/layout/commonjs.ftl">
<script src="/static/modular/teacherInfo/teacherInfo.js"></script>
</body>
</html>
