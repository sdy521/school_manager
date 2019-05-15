var Login={
    tableId:"#grid-table",
    pagerId:"#grid-pager",
    table:null,
}
Login.initJqGrid = function(){
    var tableInstance = $("#grid-table").jqGrid({
        url:'/log/loginGrid',
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
        colNames:['编号','用户名','用户类别','登入地址','说明','操作时间'],
        colModel:[
            {name:'id',index:'id', width:80,align:'center',sortable:false,search:false,formatter:'integer',key:true},
            {name:'userName',index:'userName', width:80,align:'center',sortable:false,search:true, stype:'text'},
            {name:'userType',index:'userType', width:80,align:'center',sortable:false,search:true, stype:'text',formatter:function (cellValue) {
                    if(cellValue==0){
                        return "管理员";
                    }else if(cellValue==1){
                        return "教师";
                    }else{
                        return "学生";
                    }
                }},
            {name:'ip',index:'ip', width:80,align:'center',sortable:false,search:true, stype:'text'},
            {name:'msg',index:'msg', width:80,align:'center',sortable:false,search:true, stype:'text'},
            {name:'createTime',index:'createTime', width:120,align:'center',sortable:false,search:false,formatter:function (cellValue) {
                    return dateFtt("yyyy-MM-dd hh:mm:ss",new Date(cellValue));
                }}
        ],
        page: 1,    //初始页码
        rowNum:10,
        rowList:[10,20,30],
        pager: '#grid-pager',
        autowidth: true,
        height:300,
        viewrecords: true,
        sortorder:'desc'
    });
    return tableInstance;
}

$(function () {
    Login.table = Login.initJqGrid();
});