package com.cnzk.mapper;



import com.cnzk.pojo.MenuVo;
import com.cnzk.pojo.TbMenu;
import com.cnzk.pojo.TbRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface MenuMapper {

    //分角色查对应的所有的菜单
    List<TbMenu> queryRoleAllMenu(Integer roleId);

    //    修改权限显示所有菜单
    List<TbMenu> queryAllMenu();

}
