package com.cnzk.pojo;

import java.util.List;

//权限树用到的类
public class TreeData {
    //节点唯一索引值，用于对指定节点进行各类操作 	String/Number 	任意唯一的字符或数字
    private Integer id;

    private Integer pid;

    //节点标题 	String 	未命名
    private String title;

    //节点是否初始展开，默认 false 	Boolean 	true
    private Boolean spread = false;

    //节点是否初始为选中状态（如果开启复选框的话），默认 false
    private Boolean checked = false;

    //子节点。支持设定选项同父节点 	Array 	[{title: '子节点1', id: '111'}]
    private List<TreeData> children;

    public TreeData() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public List<TreeData> getChildren() {
        return children;
    }

    public void setChildren(List<TreeData> children) {
        this.children = children;
    }

    public Boolean getSpread() {
        return spread;
    }

    public void setSpread(Boolean spread) {
        this.spread = spread;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "TreeData{" +
                "id=" + id +
                ", pid=" + pid +
                ", title='" + title + '\'' +
                ", spread=" + spread +
                ", checked=" + checked +
                ", children=" + children +
                '}';
    }
}

