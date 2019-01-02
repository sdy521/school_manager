<!-- Mainly scripts -->
<script src="/static/js/jquery-3.1.1.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="/static/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<#--工具js-->
<script src="/static/management/tool.js"></script>
<!-- Custom and plugin javascript -->
<script src="/static/js/inspinia.js"></script>
<script src="/static/js/plugins/pace/pace.min.js"></script>
<#--jqGrid-->
<script type="text/javascript" src="/static/js/plugins/jqGrid/i18n/grid.locale-cn.js"></script>
<script src="/static/js/plugins/jqGrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="/static/js/plugins/jqGrid/jqGrid.js"></script>
<#--sweetalert-->
<script type="text/javascript" src="/static/js/plugins/sweetalert/sweetalert.min.js"></script>
<#--dropzone-->
<script type="text/javascript" src="/static/js/plugins/dropzone/dropzone.js"></script>
<script>
    $(function () {
        $("div .dropzoneimg").dropzone({
            url: "/upload",//上传文件的地址，
            maxFiles: 1,//最多上传几个图片
            maxFilesize: 5,//图片的大小，单位是M
            addRemoveLinks:true,//是否有删除图片的功能
            dictRemoveFile:"",//删除图片的文字
            acceptedFiles: ".jpg,.jpeg,.png,.gif",//支持的格式
            paramName:'dropimage',//上传的FILE名称，即服务端可以通过此来获取上传的图片，如$_FILES['dropimage']
            init: function() {//初始化是的事件
                this.on("success", function(file) {
                    console.log("File " + file.name + "uploaded");
                });
                this.on("removedfile", function(file) {
                    console.log("File " + file.name + "removed");
                });
            }
        });
    });
</script>
