package cn.tedu.camper.portal.service;

import cn.tedu.camper.portal.model.Shopcar;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tedu.cn
 * @since 2022-08-31
 */
public interface IShopcarService extends IService<Shopcar> {
     void updatequantity(Integer data,Integer id);

     void deletecarproduct(Integer id);

     List<Shopcar> getUserProduct();
}
