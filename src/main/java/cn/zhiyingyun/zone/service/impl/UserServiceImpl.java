package cn.zhiyingyun.zone.service.impl;

import cn.zhiyingyun.zone.repository.UserRepository;
import cn.zhiyingyun.zone.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Joy.Zhao on 2017/7/28.
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserRepository, DspUser> implements IUserService {
  @Autowired
  private UserRepository userRepository;
}
