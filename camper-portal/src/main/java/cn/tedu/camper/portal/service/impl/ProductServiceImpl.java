package cn.tedu.camper.portal.service.impl;

import cn.tedu.camper.portal.model.Product;
import cn.tedu.camper.portal.mapper.ProductMapper;
import cn.tedu.camper.portal.service.IProductService;
import cn.tedu.camper.portal.service.ServiceException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tedu.cn
 * @since 2022-08-18
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {
    @Autowired
    ProductMapper productMapper;


    //查詢所有商品品項
    @Override
    public PageInfo<Product> getproducts(Integer pagenum , Integer pagesize) {
        if(pagenum==null || pagesize==null){
            throw ServiceException.unprocessableEntry("參數不得為空");
        }
        PageHelper.startPage(pagenum,pagesize);
        List<Product> list = productMapper.getproducts();
        log.debug("找到所有產品列表");
        return new PageInfo<>(list);
    }


    //查詢TYPE為1的商品品項
    @Override
    public PageInfo<Product> getproductsType_1(Integer pagenum, Integer pagesize) {
        if(pagenum==null || pagesize==null){
            throw ServiceException.unprocessableEntry("參數不得為空");
        }
        PageHelper.startPage(pagenum,pagesize);
        QueryWrapper query = new QueryWrapper();
        query.eq("type",1);
        List<Product> list = productMapper.selectList(query);
        return new PageInfo<>(list);
    }


    //查詢TYPE為2的商品品項
    @Override
    public PageInfo<Product> getproductsType_2(Integer pagenum, Integer pagesize) {
        if(pagenum==null || pagesize==null){
            throw ServiceException.unprocessableEntry("參數不得為空");
        }
        PageHelper.startPage(pagenum,pagesize);
        QueryWrapper query = new QueryWrapper();
        query.eq("type",2);
        List<Product> list = productMapper.selectList(query);
        return new PageInfo<>(list);
    }


    //查詢TYPE為3的商品品項
    @Override
    public PageInfo<Product> getproductsType_3(Integer pagenum, Integer pagesize) {
        if(pagenum==null || pagesize==null){
            throw ServiceException.unprocessableEntry("參數不得為空");
        }
        PageHelper.startPage(pagenum,pagesize);
        QueryWrapper query = new QueryWrapper();
        query.eq("type",3);
        List<Product> list = productMapper.selectList(query);
        return new PageInfo<>(list);
    }

    //查詢熱門商品
    @Override
    public List<Product> getHotProduct() {
        List<Product> list = productMapper.getHotproduct();
        list.forEach(product -> System.out.println(product));
        return list;
    }

    //搜尋找到的商品
    @Override
    public PageInfo<Product> getsearch(Integer pagenum, Integer pagesize, String key) {
        PageHelper.startPage(pagenum,pagesize);
        List<Product> list = productMapper.getsearchproduct(key);
        return new PageInfo<>(list);
    }

    //利用前端給予的商品ID 查詢該商品資訊
    @Override
    public Product getdetail(Integer id) {
        if(id==null){
            throw new ServiceException("id不得為空");
        }
        Product product=productMapper.selectById(id);
        String img = product.getImg();
        String[] arr = img.split(",");
        product.setImage(arr);
        if(product==null){
            throw ServiceException.busy();
        }
        return product;
    }



}

