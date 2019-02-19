 var StudentList = {
    tableId:"#grid-table",
    pagerId:"#grid-pager",
    table:null,
    myswitchery:null,
    myswitchery2:null,
    myswitchery3:null
}
 StudentList.initJqGrid = function(){
    var tableInstance = $("#grid-table").jqGrid({
        url:'/student_nameList/grid',
        postData:{deleted:$("#deleted").val()},
        datatype: "json",
        caption: "名单",
        jsonReader:{
            root: function (data) {
                return data.obj.rows;
            },   // 实际模型数据
            page: function (data) {
                return data.obj.page;
            },   // 当前页码
            total: function (data) {
                return data.obj.total;
            }, // 页码总数
            records: function (data) {
                return data.obj.records;
            }, // 数据总数
            repeatitems:false
        },
        colNames:['编号','姓名'/*,'密码'*/,'创建时间','修改时间',/* '状态',*/'安全权限','操作'],
        colModel:[
            {name:'id',index:'id', width:80,align:'center',sortable:true,search:false,formatter:'integer',key:true},
            {name:'name',index:'name', width:80,align:'center',sortable:false,search:true, stype:'text'},
            // {name:'password',index:'password', width:100,align:'center',sortable:false,search:false},
            {name:'createTime',index:'createTime', width:120,align:'center',sortable:false,search:false,formatter:function (cellValue) {
                    return setDateFormat(new Date(cellValue));
                }},
            {name:'updateTime',index:'updateTime', width:120,align:'center',sortable:false,search:false,formatter:function (cellValue) {
                    return setDateFormat(new Date(cellValue));
                }},
            /*{name:'deleted',index:'deleted', width:80,align:'center',sortable:false,search:false,formatter:function (cellValue) {
                    if(cellValue==0){
                        return "<span class=\"label label-primary\">未删除</span>";
                    }else {
                        return "<span class=\"label label-danger\">已删除</span>";
                    }
                }},*/
            {name:'enable',index:'enable', width:80,align:'center',sortable:false,search:false,formatter:function (cellValue) {
                    if(cellValue==1){
                        return "<span class=\"label label-primary\">已激活</span>";
                    }else {
                        return "<span class=\"label label-danger\">未激活</span>";
                    }
                }},
            {name:'operation',index:'operation', width:130, sortable:false,sortable:false,search:false,formatter:function (cellValue,index,rowObject) {
                    var id = rowObject['id'];
                    var deleted = rowObject['deleted'];
                    var str="";
                    if(deleted==0){
                        str +="<button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"StudentList.updateModal("+id+");\">修改</button>&nbsp;&nbsp;";
                        str +="<button type=\"button\" class=\"btn btn-danger btn-sm\" onclick=\"StudentList.delete("+id+");\">删除</button>&nbsp;&nbsp;";
                        str +="<button type=\"button\" class=\"btn btn-success btn-sm\" onclick=\"StudentList.initpassword("+id+");\">密码重置</button>";
                    }else{
                        str +="<button type=\"button\" class=\"btn btn-warning btn-sm\" onclick=\"StudentList.recover("+id+");\">恢复</button>";
                    }
                    return str;
                }}
        ],
        page: 1,    //初始页码
        rowNum:10,
        rowList:[10,20,30],
        pager: '#grid-pager',
        autowidth: true,
        height:380,
        viewrecords: true,
        sortorder:'desc'
        //loadonce:true//为true时，不请求后台，直接从界面上排序
        // sortorder:"desc",//此属性只能用于loadonce为true时
        // multiselect: false,
        // multiselectWidth: 25,
        /*loadComplete : function() {
            var grid = $("#grid-table");
            var ids = grid.getDataIDs();
            for (var i = 0; i < ids.length; i++) {
                grid.setRowData (ids[i], false, {height: 40}); //设置成你要设定的固定行高
            }
        }*/
    });
    // StudentList.jqSearch();
    return tableInstance;
}
 //jqGrid自带搜索
StudentList.jqSearch = function(){
    var options={};
    options.autosearch = true;
    $("#grid-table").jqGrid('filterToolbar',options);
}
//搜索
 StudentList.search = function () {
    var name = $("#teacherName").val().trim();
    var deleted = $("#deleted").val();
    var params = {};
    params.name = name;
    params.deleted = deleted;
    StudentList.table.setGridParam({
        url:"/student_nameList/grid",
        page:1,
        postData:params
    }).trigger("reloadGrid");
 }
 //重置
 StudentList.reset = function(){
    var elem = $("#teacherName");
    elem.val("");
    $("#deleted").val(0);
    var params = {};
    params.name="";
    params.deleted=0;
    StudentList.table.setGridParam({
        url:"/student_nameList/grid",
        page:1,
        postData:params
    }).trigger("reloadGrid");
 }
 //新增
 StudentList.add = function(){
     var elem = $("#createModal");
     elem.find("input[name='name']").val("");
     elem.find("input[name='password']").val("");
    $("#createModal").modal();
 }
 StudentList.insert = function(){
    var elem = $("#createModal");
    var params = {};
    params.name = elem.find("input[name='name']").val().trim();
    params.password = elem.find("input[name='password']").val().trim();
    params.enable = document.querySelector("#createModal .js-switch").checked;
    $.ajax({
        url:"/student_nameList/insert",
        data:JSON.stringify(params),
        type:"POST",
        dataType:"JSON",
        contentType:"application/json;charset=utf8",
        success:function (r) {
            if(r.code===0){
                elem.modal('hide');
                success("添加成功");
                StudentList.search();
            }
        }
    });
 }
 //修改
 StudentList.updateModal = function(id){
    $.ajax({
        url:"/student_nameList/selectOne?id="+id,
        type:"GET",
        dataType:"JSON",
        success:function (r) {
            if(r.code===0){
                var data = r.obj;
                var elem = $("#updateModal");
                elem.find("input[name='id']").val(data.id);
                elem.find("input[name='name']").val(data.name);
                elem.find("input[name='password']").val(data.password);
                setSwitchery(StudentList.myswitchery2, data.enable);
                elem.modal();
            }
        }
    });
 }
 StudentList.update = function(){
     var elem = $("#updateModal");
     var params={};
     params.id = elem.find("input[name='id']").val();
     params.name = elem.find("input[name='name']").val();
     params.password = elem.find("input[name='password']").val();
     params.enable = document.querySelector("#updateModal .js-switch").checked;
     $.ajax({
         url:"/teacher_nameList/update",
         type:"POST",
         dataType:"JSON",
         data:JSON.stringify(params),
         contentType:"application/json;charset=utf8",
         success:function (r) {
             if(r.code===0){
                 elem.modal('hide');
                 StudentList.search();
                 success("修改成功");
             }
         }
     });
 }
 //删除
 StudentList.delete = function(id){
    warning("确定要删除吗？","",function () {
        $.ajax({
            url:"/student_nameList/delete?id="+id,
            type:"GET",
            dataType:"JSON",
            success:function (r) {
                if(r.code===0){
                    success("删除成功");
                    StudentList.search();
                }
            }
        });
    });
 }
 //恢复
 StudentList.recover = function(id){
    recoverWarning("确定要恢复吗？","",function () {
        $.ajax({
            url:"/student_nameList/recover?id="+id,
            type:"GET",
            dataType:"JSON",
            success:function (r) {
                if(r.code===0){
                    success("恢复成功");
                    StudentList.search();
                }
            }
        });
    })
 }
 //重置密码
 StudentList.initpassword = function(id){
     init("确定要重置密码吗？","",function () {
        $.ajax({
            url:"/student_nameList/initpassword?id="+id,
            type:"GET",
            dataType:"JSON",
            success:function (r) {
                if(r.code===0){
                    success("密码重置成功");
                }
            }
        });
    });
 }
 //批量导入
 StudentList.importExcel = function(){
    $("#importModal").modal();
 }
 StudentList.import = function(){
    var formData = new FormData();
    formData.append("file",$("#fileinput")[0].files[0]);
    formData.append("enable",document.querySelector('#importModal .js-switch').checked);
    $.ajax({
        url:"/student_nameList/importExcel",
        type:"POST",
        dataType:"JSON",
        contentType:false,
        processData:false,//是否序列化
        data:formData,
        catch:false,
        success:function (r) {
            if(r.code===0){
                $("#importModal").modal('hide');
                StudentList.search();
                success("导入完成");
                $("#fileinput").fileinput('refresh').fileinput('enable');//清空上传文件框并设置可用
                setSwitchery(StudentList.myswitchery3,false);
            }
        }
    });
 }
 //下载模板
 StudentList.downloadTemplate = function(){
    window.open("/student_nameList/downloadTemplate");
 }
 $(function () {
    StudentList.table = StudentList.initJqGrid();
    //switchery
    StudentList.myswitchery = new Switchery(document.querySelector('#createModal .js-switch'),{color:'#1AB394'});
    StudentList.myswitchery2 = new Switchery(document.querySelector('#updateModal .js-switch'),{color:'#1AB394'});
    StudentList.myswitchery3 = new Switchery(document.querySelector('#importModal .js-switch'),{color:'#1AB394'});
    //bootstrap fileinput
     $("#fileinput").fileinput({
         language: 'zh', //设置语言
         uploadUrl: "/upload", //上传的地址
         allowedFileExtensions: ['xls'],//接收的文件后缀
         uploadAsync: true, //默认异步上传
         showUpload: false, //是否显示上传按钮
         showRemove : false, //显示移除按钮
         showPreview : false, //是否显示预览
         showCaption: true,//是否显示标题
         browseClass: "btn btn-primary", //按钮样式
         dropZoneEnabled: false,//是否显示拖拽区域
         maxFileCount: 1, //表示允许同时上传的最大文件个数
         enctype: 'multipart/form-data',
         validateInitialCount:true
     });
 });