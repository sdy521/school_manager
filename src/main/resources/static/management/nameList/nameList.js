 var NameList = {
    tableId:"#grid-table",
    pagerId:"#grid-pager",
    table:null,
}
NameList.initJqGrid = function(){
    var tableInstance = $("#grid-table").jqGrid({
        url:'/teacher_nameList/grid',
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
        colNames:['编号','姓名','密码','创建时间','修改时间', '状态','操作'],
        colModel:[
            {name:'id',index:'id', width:80,align:'center',sortable:true,search:false,formatter:'integer',key:true},
            {name:'name',index:'name', width:80,align:'center',sortable:false,search:true, stype:'text'},
            {name:'password',index:'password', width:100,align:'center',sortable:false,search:false},
            {name:'createTime',index:'createTime', width:120,align:'center',sortable:false,search:false,formatter:function (cellValue) {
                    return setDateFormat(new Date(cellValue));
                }},
            {name:'updateTime',index:'updateTime', width:120,align:'center',sortable:false,search:false,formatter:function (cellValue) {
                    return setDateFormat(new Date(cellValue));
                }},
            {name:'deleted',index:'deleted', width:80,align:'center',sortable:false,search:false,formatter:function (cellValue) {
                    if(cellValue==0){
                        return "未删除";
                    }else {
                        return "已删除";
                    }
                }},
            {name:'operation',index:'operation', width:100, sortable:false,align:'center',sortable:false,search:false,formatter:function (cellValue,index,rowObject) {
                    var id = rowObject['id'];
                    var deleted = rowObject['deleted'];
                    var str="";
                    if(deleted==0){
                        str +="<button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"NameList.updateModal("+id+");\">修改</button>&nbsp;&nbsp;";
                        str +="<button type=\"button\" class=\"btn btn-danger btn-sm\" onclick=\"NameList.delete("+id+");\">删除</button>";
                    }else{
                        str +="<button type=\"button\" class=\"btn btn-warning btn-sm\" onclick=\"NameList.recover("+id+");\">恢复</button>";
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
    // NameList.jqSearch();
    return tableInstance;
}
 //jqGrid自带搜索
NameList.jqSearch = function(){
    var options={};
    options.autosearch = true;
    $("#grid-table").jqGrid('filterToolbar',options);
}
//搜索
 NameList.search = function () {
    var name = $("#teacherName").val().trim();
    var deleted = $("#deleted").val();
    var params = {};
    params.name = name;
    params.deleted = deleted;
    NameList.table.setGridParam({
        url:"/teacher_nameList/grid",
        page:1,
        postData:params
    }).trigger("reloadGrid");
 }
 //重置
 NameList.reset = function(){
    var elem = $("#teacherName");
    elem.val("");
    $("#deleted").val(0);
    var params = {};
    params.name="";
    params.deleted=0;
    NameList.table.setGridParam({
        url:"/teacher_nameList/grid",
        page:1,
        postData:params
    }).trigger("reloadGrid");
 }
 //新增
 NameList.add = function(){
     var elem = $("#createModal");
     elem.find("input[name='name']").val("");
     elem.find("input[name='password']").val("");
    $("#createModal").modal();
 }
 NameList.insert = function(){
    var elem = $("#createModal");
    var params = {};
    params.name = elem.find("input[name='name']").val().trim();
    params.password = elem.find("input[name='password']").val().trim();
    $.ajax({
        url:"/teacher_nameList/insert",
        data:JSON.stringify(params),
        type:"POST",
        dataType:"JSON",
        contentType:"application/json;charset=utf8",
        success:function (r) {
            if(r.code===0){
                elem.modal('hide');
                success("添加成功");
                NameList.search();
            }
        }
    });
 }
 //修改
 NameList.updateModal = function(id){
    $.ajax({
        url:"/teacher_nameList/selectOne?id="+id,
        type:"GET",
        dataType:"JSON",
        success:function (r) {
            if(r.code===0){
                var data = r.obj;
                var elem = $("#updateModal");
                elem.find("input[name='id']").val(data.id);
                elem.find("input[name='name']").val(data.name);
                elem.find("input[name='password']").val(data.password);
                elem.modal();
            }
        }
    });
 }
 NameList.update = function(){
     var elem = $("#updateModal");
     var params={};
     params.id = elem.find("input[name='id']").val();
     params.name = elem.find("input[name='name']").val();
     params.password = elem.find("input[name='password']").val();
     $.ajax({
         url:"/teacher_nameList/update",
         type:"POST",
         dataType:"JSON",
         data:JSON.stringify(params),
         contentType:"application/json;charset=utf8",
         success:function (r) {
             if(r.code===0){
                 elem.modal('hide');
                 NameList.search();
                 success("修改成功");
             }
         }
     });
 }
 //删除
 NameList.delete = function(id){
    warning("确定要删除吗？","",function () {
        $.ajax({
            url:"/teacher_nameList/delete?id="+id,
            type:"GET",
            dataType:"JSON",
            success:function (r) {
                if(r.code===0){
                    success("删除成功");
                    NameList.search();
                }
            }
        });
    });
 }
 //恢复
 NameList.recover = function(id){
    recoverWarning("确定要恢复吗？","",function () {
        $.ajax({
            url:"/teacher_nameList/recover?id="+id,
            type:"GET",
            dataType:"JSON",
            success:function (r) {
                if(r.code===0){
                    success("恢复成功");
                    NameList.search();
                }
            }
        });
    })
 }
 $(function () {
    NameList.table = NameList.initJqGrid();
 });