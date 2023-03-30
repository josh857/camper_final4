package cn.tedu.camper.portal.controller;

import cn.tedu.camper.portal.Vo.R;
import cn.tedu.camper.portal.Vo.changeVo;
import cn.tedu.camper.portal.Vo.registerVo;
import cn.tedu.camper.portal.Vo.userVo;
import cn.tedu.camper.portal.mapper.CarMapper;
import cn.tedu.camper.portal.mapper.OrdersMapper;
import cn.tedu.camper.portal.mapper.ShopcarMapper;
import cn.tedu.camper.portal.mapper.UserMapper;
import cn.tedu.camper.portal.model.Orders;
import cn.tedu.camper.portal.model.Shopcar;
import cn.tedu.camper.portal.model.User;
import cn.tedu.camper.portal.service.ServiceException;
import cn.tedu.camper.portal.service.impl.ShopcarServiceImpl;
import cn.tedu.camper.portal.service.impl.UserServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Slf4j
public class SysController {
@Autowired
    UserServiceImpl userService;
@Autowired
    CarMapper carMapper;
@Autowired
    UserMapper userMapper;
@Autowired
ShopcarMapper shopcarMapper;
@Autowired
    ShopcarServiceImpl shopcarService;
@Autowired
    OrdersMapper ordersMapper;

BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @GetMapping("/login.html")
    ModelAndView login(){
        return new ModelAndView("login");
    }


    @GetMapping("/register.html")
    ModelAndView register(){
        return new ModelAndView("register");
    }

    @GetMapping("/index.html")
    ModelAndView index(){
        return new ModelAndView("index");
    }

    @GetMapping("/index_type1.html")
    ModelAndView index_type1(){
        return new ModelAndView("index_type1");
    }

    @GetMapping("/index_type2.html")
    ModelAndView index_type2(){
        return new ModelAndView("index_type2");
    }

    @GetMapping("/index_type3.html")
    ModelAndView index_type3(){
        return new ModelAndView("index_type3");
    }

    @GetMapping("/changePassword.html")
    ModelAndView change(){
        return new ModelAndView("changePassword");
    }
    @GetMapping("/shopList.html")
    ModelAndView shopList(){
        return new ModelAndView("shopList");
    }


   //串接結帳動作
    @GetMapping("/payedshopList.html")
    ModelAndView payed_shopList(){

        String username = userService.currentUsername();
        User user = userMapper.finduserByUsername(username);

        Orders orders = new Orders();
        List<Shopcar> list = shopcarService.getUserProduct();
        for(Shopcar shopCar: list) {
            orders.setUsername(user.getNickname());
            orders.setUserId(user.getId());
            orders.setUpdatetime(LocalDateTime.now());
            orders.setCreatetime(LocalDateTime.now());
            orders.setAddress(user.getAddress());
            orders.setProductId(shopCar.getProductid());
            Integer price= shopCar.getPrice()*shopCar.getQuantity();
            orders.setPrice(price);
            orders.setPhone(Integer.parseInt(user.getPhone()));
            orders.setQuantity(shopCar.getQuantity());
            ordersMapper.insert(orders);
            log.debug("存取成功");
        }
        QueryWrapper<Shopcar> query = new QueryWrapper<>();
        query.eq("userid",user.getId());
        shopcarMapper.delete(query);
        log.debug("支付完畢!刪除購物車資料");
        return new ModelAndView("payedshopList");

    }



    @GetMapping("/product.html")
    ModelAndView detail(){
        return new ModelAndView("product");
    }
    @GetMapping("/car.html")
    ModelAndView car(){
        return new ModelAndView("car");
    }



    //顯示購物車內商品訊息
    @GetMapping("/orders.html")
    ModelAndView orders(){
        String username=userService.currentUsername();
        User user = userMapper.finduserByUsername(username);
        QueryWrapper<Shopcar> query = new QueryWrapper<>();
        query.eq("userid" ,user.getId());
        List<Shopcar> list = shopcarMapper.selectList(query);
        if(list.isEmpty()){
            throw  new ServiceException("請先加入商品至購物車");
        }
        return new ModelAndView("order");
    }


    //註冊功能
    @PostMapping("/register")
    public R register (@Validated registerVo registerVo, BindingResult result){
        if(result.hasErrors()){
            String error = result.getFieldError().getDefaultMessage();
            log.info("表單驗證錯誤{}",error);
            return R.unproecsableEntity(error);
        }
        if(!registerVo.getPassword().equals(registerVo.getConfirm())){
            log.info("確認密碼不一致{}");
            return R.unproecsableEntity("確認密碼不一致");

        }
       try {
           userService.register(registerVo);
           return R.created("註冊成功");
       }catch (ServiceException e){
           log.error("註冊失敗",e);
           return R.failed(e);
       }
    }


    //更改密碼功能
    @PostMapping("/change/{id}")
    public R change(changeVo changevo, @PathVariable Integer id){
        String username = userService.currentUsername();
        User user = userMapper.finduserByUsername(username);
        if(id==null){
            return R.notFound("請給id");
        }
        if (changevo==null){
            return R.unproecsableEntity("請輸入完整參數");
        }
        log.debug("原密碼:{}",user.getPassword());
        if(changevo.getOldpassword().equals(user.getPassword())){
            return R.unproecsableEntity("原密碼輸入錯誤");
        }
        userService.change(changevo,id);
        return R.ok("更改完成");

    }




}
