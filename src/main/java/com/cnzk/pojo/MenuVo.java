package com.cnzk.pojo;

import java.util.List;

public class MenuVo {

    private Long id;

    private Long pid;

    private String title;

    private String icon;

    private String href;

    private String target;

    //节点是否初始展开，默认 false 	Boolean 	true
    private Boolean spread = false;

    //节点是否初始为选中状态（如果开启复选框的话），默认 false
    private Boolean checked = false;

    private List<MenuVo> child;

    private List<MenuVo> children;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public List<MenuVo> getChildren() {
        return children;
    }

    public void setChildren(List<MenuVo> children) {
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

    public List<MenuVo> getChild() {
        return child;
    }

    public void setChild(List<MenuVo> child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return "MenuVo{" +
                "id=" + id +
                ", pid=" + pid +
                ", title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                ", href='" + href + '\'' +
                ", target='" + target + '\'' +
                ", spread=" + spread +
                ", checked=" + checked +
                ", child=" + child +
                ", children=" + children +
                '}';
    }
}