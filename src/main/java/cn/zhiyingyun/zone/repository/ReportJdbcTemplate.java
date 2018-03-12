package cn.zhiyingyun.zone.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by Joy.Zhao on 2017/7/24.
 */
@org.springframework.stereotype.Repository
public class ReportJdbcTemplate implements Repository<String, Integer> {

  @Autowired
  private JdbcTemplate jdbcTemplate;
}

