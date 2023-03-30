package cn.tedu.camper.portal.controller;


import cn.tedu.camper.portal.Vo.R;
import cn.tedu.camper.portal.Vo.userVo;
import cn.tedu.camper.portal.service.ServiceException;
import cn.tedu.camper.portal.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tedu.cn
 * @since 2022-08-18
 */
@RestController
@RequestMapping("/v1/user")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @GetMapping("/me")
    public R<userVo> getcurrentUserVo(){
        userVo uservo= userService.getcurrentUserVo();
        if(uservo==null){
            return R.failed(ServiceException.notFound("加載失敗"));
        }
        return R.ok(uservo);
    }

}
