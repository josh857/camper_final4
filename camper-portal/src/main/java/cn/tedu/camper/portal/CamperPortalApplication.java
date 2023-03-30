package cn.tedu.camper.portal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("cn.tedu.camper.portal.mapper")
@SpringBootApplication
public class CamperPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(CamperPortalApplication.class, args);
    }

}
