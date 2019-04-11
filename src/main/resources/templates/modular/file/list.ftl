<#--模板实例-->
<!DOCTYPE html>
<html>
<head>
<#include "/templates/layout/meta.ftl">
    <link href="/static/css/plugins/jsTree/style.min.css" rel="stylesheet">
</head>
<body>
<div id="wrapper">
    <#include "/templates/layout/left.ftl">
    <div id="page-wrapper" class="gray-bg">
        <#include "/templates/layout/head.ftl">
        <div class="wrapper wrapper-content">
            <div class="row">
                <div class="col-lg-12">
                    <div class="ibox ">
                        <div class="ibox-content">
                            <#--内容-->
                            <div id="jstree_file"></div>
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
    <input type="hidden" id="code"/>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="modalTitle">新增</h4>
            </div>
            <div class="modal-body">
                <input id="input-id" name="file" multiple type="file" data-show-caption="true">
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<#include "/templates/layout/commonjs.ftl">
</body>
<#--jstree-->
<script src="/static/js/plugins/jsTree/jstree.min.js"></script>
<script src="/static/modular/file/file.js"></script>
</html>
