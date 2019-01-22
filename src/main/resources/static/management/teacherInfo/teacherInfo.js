 var TeacherInfo = {
    tableId:"#grid-table",
    table:null,
}
TeacherInfo.initJqGrid = function(){
    var tableInstance = $("#grid-table").jqGrid({
        url:'/teacher_info/grid',
        postData:{type:1},
        datatype: "json",
        caption: "老师信息",
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
        colNames:['编号','姓名','性别','创建时间','修改时间','操作'],
        colModel:[
            {name:'id',index:'id', width:80,align:'center',sortable:true,search:false,formatter:'integer',key:true},
            {name:'name',index:'name', width:80,align:'center',sortable:false,search:true, stype:'text'},
            {name:'sex',index:'sex', width:80,align:'center',sortable:false,search:true, stype:'text'},
            {name:'create_time',index:'create_time', width:120,align:'center',sortable:false,search:false,formatter:function (cellValue) {
                    return setDateFormat(new Date(cellValue));
                }},
            {name:'update_time',index:'update_time', width:120,align:'center',sortable:false,search:false,formatter:function (cellValue) {
                    return setDateFormat(new Date(cellValue));
                }},
            {name:'operation',index:'operation', width:130, sortable:false,sortable:false,search:false,formatter:function (cellValue,index,rowObject) {
                    var id = rowObject['id'];
                    var deleted = rowObject['deleted'];
                    var str="";
                    str +="<button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"TeacherInfo.updateModal("+id+");\">修改</button>&nbsp;&nbsp;";
                    str +="<button type=\"button\" class=\"btn btn-danger btn-sm\" onclick=\"TeacherInfo.delete("+id+");\">删除</button>&nbsp;&nbsp;";
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
    });
    return tableInstance;
}
//搜索
 TeacherInfo.search = function () {
    var name = $("#teacherName").val().trim();
    var deleted = $("#deleted").val();
    var params = {};
    params.name = name;
    params.deleted = deleted;
    TeacherInfo.table.setGridParam({
        url:"/teacher_nameList/grid",
        page:1,
        postData:params
    }).trigger("reloadGrid");
 }
 //重置
 TeacherInfo.reset = function(){
    var elem = $("#teacherName");
    elem.val("");
    $("#deleted").val(0);
    var params = {};
    params.name="";
    params.deleted=0;
    TeacherInfo.table.setGridParam({
        url:"/teacher_nameList/grid",
        page:1,
        postData:params
    }).trigger("reloadGrid");
 }
 $(function () {
    TeacherInfo.table = TeacherInfo.initJqGrid();
 });