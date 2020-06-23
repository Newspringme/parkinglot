package com.cnzk.controller;

import com.cnzk.pojo.TbCoordinate;
import com.cnzk.service.CoorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/CoorController")
public class CoorController {
    @Autowired
    private CoorService coorService;

    @ResponseBody
    @RequestMapping("/getSelfServiceCoor")
    public Object getSelfServiceCoor(String machineNum, HttpServletRequest request) throws Exception{
        machineNum = "self_service_machine_"+machineNum;
        TbCoordinate tbCoordinate = coorService.getSelfServiceCoor(machineNum);
        request.getSession().setAttribute("machine",tbCoordinate);
        return "true";
    }
}
