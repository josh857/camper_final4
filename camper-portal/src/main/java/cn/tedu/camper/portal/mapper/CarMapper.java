package cn.tedu.camper.portal.mapper;

import cn.tedu.camper.portal.model.Car;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
* <p>
    *  Mapper 接口
    * </p>
*
* @author tedu.cn
* @since 2022-08-25
*/
    @Repository
    public interface CarMapper extends BaseMapper<Car> {
        //搜尋商品是否存在於當前用戶
        @Select("select * from car where userid=#{userid} AND productid=#{productid}")
             Car getproductid(Integer userid, Integer productid);


    }
