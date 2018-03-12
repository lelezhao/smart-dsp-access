package cn.zhiyingyun.zone.repository;

import cn.zhiyingyun.zone.domain.DspUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Joy.Zhao on 2017/7/21.
 */
public interface UserRepository extends JpaRepository<DspUser, Integer> {
  DspUser findByAccount(String account);

  Page<DspUser> findAll(Specification<DspUser> specification, Pageable pageable);
}
