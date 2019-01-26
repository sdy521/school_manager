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
                <h2>发布公告</h2>
                <ol class="breadcrumb">
                    <li>
                        <a href="/main">首页</a>
                    </li>
                    <li class="active">
                        <strong>编辑</strong>
                    </li>
                </ol>
            </div>
        </div>
        <div class="wrapper wrapper-content">
            <div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>消息通知 <small>发布学校公告.</small></h5>
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                            <div class="form-horizontal">
                                <div class="form-group"><label class="col-sm-2 control-label">标题：</label>
                                    <div class="col-sm-10"><input type="text" id="onlyTitle" class="form-control"></div>
                                </div>
                                <div class="hr-line-dashed"></div>
                                <div class="form-group"><label class="col-sm-2 control-label">内容：</label>
                                    <div id="editor" type="text/plain" style="margin-left:17%;width:90%;height:300px;"></div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-4 col-sm-offset-2">
                                        <#--<button class="btn btn-white" type="submit">Cancel</button>-->
                                        <button class="btn btn-primary" onclick="sendName();">发布</button>
                                    </div>
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
</body>
<script type="text/javascript" charset="utf-8" src="/static/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="/static/ueditor/ueditor.all.min.js"> </script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="/static/ueditor/lang/zh-cn/zh-cn.js"></script>
<#include "/templates/layout/commonjs.ftl"/>
<script src="/static/management/send/send.js"></script>
</html>
