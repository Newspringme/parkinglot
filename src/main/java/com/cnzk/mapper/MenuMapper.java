package com.cnzk.mapper;



import com.cnzk.pojo.MenuVo;
import com.cnzk.pojo.TbMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface MenuMapper {

    //分角色查对应的所有的菜单
    public List<TbMenu> queryRoleAllMenu(Integer roleId);

}
