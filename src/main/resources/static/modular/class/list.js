 var ClassList = {
    tableId:"#grid-table",
    pagerId:"#grid-pager",
    table:null
}
 ClassList.initJqGrid = function(){
    var tableInstance = $("#grid-table").jqGrid({
        url:'/class/grid',
        postData:{deleted:$("#deleted").val()},
        datatype: "json",
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
        colNames:['编号','班级名称','创建时间','创建人','操作'],
        colModel:[
            {name:'id',index:'id', width:80,align:'center',sortable:true,search:false,formatter:'integer',key:true},
            {name:'name',index:'name', width:80,align:'center',sortable:false,search:true, stype:'text'},
            {name:'create_time',index:'create_time', width:120,align:'center',sortable:false,search:false,formatter:function (cellValue) {
                        return dateFtt("yyyy-MM-dd hh:mm:ss",new Date(cellValue));
                }},
            {name:'createUserName',index:'createUserName', width:80,align:'center',sortable:false,search:true, stype:'text'},
            {name:'operation',index:'operation', width:130, sortable:false,sortable:false,search:false,formatter:function (cellValue,index,rowObject) {
                    var id = rowObject['id'];
                    var deleted = rowObject['deleted'];
                    var str="";
                    if(deleted==0){
                        str +="<button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"ClassList.updateModal("+id+");\">修改</button>&nbsp;&nbsp;";
                        str +="<button type=\"button\" class=\"btn btn-danger btn-sm\" onclick=\"ClassList.delete("+id+");\">删除</button>&nbsp;&nbsp;";
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
    });
    return tableInstance;
}
//搜索
 ClassList.search = function () {
    var name = $("#className").val().trim();
    var deleted = $("#deleted").val();
    var params = {};
    params.name = name;
    params.deleted = deleted;
    ClassList.table.setGridParam({
        url:"/class/grid",
        page:1,
        postData:params
    }).trigger("reloadGrid");
 }
 //重置
 ClassList.reset = function(){
    var elem = $("#teacherName");
    elem.val("");
    $("#deleted").val(0);
    var params = {};
    params.name="";
    params.deleted=0;
    ClassList.table.setGridParam({
        url:"/class/grid",
        page:1,
        postData:params
    }).trigger("reloadGrid");
 }
 //新增
 ClassList.add = function(){
     var elem = $("#createModal");
     elem.find("input[name='name']").val("");
    $("#createModal").modal();
 }
 ClassList.insert = function(){
    var elem = $("#createModal");
    var name = elem.find("input[name='name']").val().trim();
    $.ajax({
        url:"/class/insert?name="+name,
        type:"GET",
        dataType:"JSON",
        success:function (r) {
            if(r.code===0){
                elem.modal('hide');
                success("添加成功");
                ClassList.search();
            }
        }
    });
 }
 //修改
 ClassList.updateModal = function(id){
    $.ajax({
        url:"/class/selectOne?id="+id,
        type:"GET",
        dataType:"JSON",
        success:function (r) {
            if(r.code===0){
                var data = r.obj;
                var elem = $("#updateModal");
                elem.find("input[name='id']").val(data.id);
                elem.find("input[name='name']").val(data.name);
                elem.modal();
            }
        }
    });
 }
 ClassList.update = function(){
     var elem = $("#updateModal");
     var id = elem.find("input[name='id']").val();
     var name = elem.find("input[name='name']").val();
     $.ajax({
         url:"/class/update?id="+id+"&name="+name,
         type:"GET",
         dataType:"JSON",
         success:function (r) {
             if(r.code===0){
                 elem.modal('hide');
                 ClassList.search();
                 success("修改成功");
             }
         }
     });
 }
 //删除
 ClassList.delete = function(id){
    warning("确定要删除吗？","",function () {
        $.ajax({
            url:"/class/delete?id="+id,
            type:"GET",
            dataType:"JSON",
            success:function (r) {
                if(r.code===0){
                    success("删除成功");
                    ClassList.search();
                }
            }
        });
    });
 }
 $(function () {
    ClassList.table = ClassList.initJqGrid();
 });