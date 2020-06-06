package com.cnzk.mapper;



import com.cnzk.pojo.TbMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface MenuMapper {
//    找二级菜单
    public List<TbMenu> findMenu(Integer rolesid);
//    找一级菜单
    public List<TbMenu> findMenuSon(Integer rolesid);
//    找所有的一级菜单
    public List<TbMenu> findAllMenuSon();
//    找所有的二级菜单
    public List<TbMenu> findAllMenus();
}
