package cn.zhiyingyun.zone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@SpringBootApplication
@EnableTransactionManagement
public class SmartZoneBootApplication extends WebMvcConfigurerAdapter {

  public static void main(String[] args) {

    SpringApplication.run(SmartZoneBootApplication.class, args);
  }

}
