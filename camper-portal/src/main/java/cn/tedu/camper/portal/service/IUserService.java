package cn.tedu.camper.portal.service;

import cn.tedu.camper.portal.Vo.changeVo;
import cn.tedu.camper.portal.Vo.registerVo;
import cn.tedu.camper.portal.Vo.userVo;
import cn.tedu.camper.portal.model.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tedu.cn
 * @since 2022-08-18
 */
public interface IUserService extends IService<User> {

    UserDetails getUserDetails(String username);



    void register(registerVo registervo);

    userVo getcurrentUserVo();


     String currentUsername();

     void change(changeVo changevo,Integer id);

    void update(String address,String nickname,Integer phone,Integer id);

}
