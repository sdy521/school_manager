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
        <#--内容-->
        <div class="wrapper wrapper-content">
            <input type="hidden" id="nginxurl" value="${nginxurl}">
            <div class="row">
                <div class="col-lg-12">
                    <div class="ibox">
                        <div class="form-group">
                            <div class="col-sm-12">
                                <input id="input-id" name="file" multiple type="file" data-show-caption="true">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="wrapper wrapper-content">
            <div class="row">
                <div class="col-lg-12">
                    <div class="ibox-content">
                        <button class="btn btn-success" style="margin-bottom: 10px;" onclick="WordPdf.downloadPdfZip();">导出pdf</button>
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
</body>
<script src="/static/modular/wordConverterPdf/wordConverterPdf.js"></script>
</html>
