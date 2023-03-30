package cn.tedu.camper.portal.service;

import cn.tedu.camper.portal.Vo.CarVo;
import cn.tedu.camper.portal.model.Car;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tedu.cn
 * @since 2022-08-25
 */
public interface ICarService extends IService<Car> {
    String gotocar(Integer id);

    CarVo getrows();


}
