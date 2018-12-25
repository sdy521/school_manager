package com.study.study_manager.core;

public class JSONResult extends Result{
    private Object obj;

    public JSONResult(){

    }
    public JSONResult(Object obj){
        super(0,"操作成功");
        this.obj = obj;
    }
    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
