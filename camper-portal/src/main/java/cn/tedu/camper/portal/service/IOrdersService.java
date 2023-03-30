package cn.tedu.camper.portal.service;

import cn.tedu.camper.portal.Vo.orderVo;
import cn.tedu.camper.portal.model.City;
import cn.tedu.camper.portal.model.Cityrange;
import cn.tedu.camper.portal.model.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tedu.cn
 * @since 2022-09-03
 */
public interface IOrdersService extends IService<Orders> {

   void insert(orderVo orderVo);
   //根據城市type 找到相對應的區
   City getcitytype(String name);
   List<Cityrange> getrange(Integer type);
   List<City> getcity();
}
