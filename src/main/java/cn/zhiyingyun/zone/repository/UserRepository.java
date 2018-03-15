package cn.zhiyingyun.zone.repository;

import cn.zhiyingyun.zone.domain.DspUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Joy.Zhao on 2017/7/21.
 */
public interface UserRepository extends JpaRepository<DspUser, Integer> {
  DspUser findByAccount(String account);
}
