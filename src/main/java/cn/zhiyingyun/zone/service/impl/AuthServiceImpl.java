package cn.zhiyingyun.zone.service.impl;

import cn.zhiyingyun.zone.dto.TokenInfo;
import cn.zhiyingyun.zone.exception.UnauthorizedException;
import cn.zhiyingyun.zone.repository.UserRepository;
import cn.zhiyingyun.zone.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by kalrey on 17/6/22.
 */
@Service
public class AuthServiceImpl implements IAuthService {

  private final Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
  @Autowired
  private UserRepository userRepository;

  @Value("${auth.token.expire.seconds}")
  private Integer expireSeconds;
  @Value("${spring.application.name}")
  private String applicationName;

  @Autowired
  private StringRedisTemplate stringRedisTemplate;

  public Integer getCacheValueAsInteger(String key) {
    try {
      return Integer.valueOf(stringRedisTemplate.opsForValue().get(key));
    } catch (NumberFormatException e) {
      return null;
    }
  }

  public boolean resetExpire(String key, long secondExpire) {
    return stringRedisTemplate.expire(key, secondExpire, TimeUnit.SECONDS);
  }

  public void setCacheValueWithExpire(String key, Integer value, long secondExpire) {
    stringRedisTemplate.opsForValue().set(key, value.toString(), secondExpire, TimeUnit.SECONDS);
  }


  @Override
  public TokenInfo login(String login, String passwordMd5) throws UnauthorizedException {

    DspUser userProfile = userRepository.findByAccount(login);

    if (userProfile == null) {
      throw new UnauthorizedException("用户不存在");
    }

    if (!userProfile.getPassword().equals(passwordMd5)) {
      throw new UnauthorizedException("密码不正确");
    }

    TokenInfo tokenInfo = new TokenInfo();
    tokenInfo.setToken(generateToken(userProfile.getId(), expireSeconds));
    tokenInfo.setId(userProfile.getId());
    tokenInfo.setName(userProfile.getCompanyName());
    tokenInfo.setAccount(userProfile.getAccount());

    return tokenInfo;
  }

  @Override
  public String generateToken(Integer userId, long secondExpire) {
    String token = md5PasswordEncoder.encodePassword(applicationName + userId.toString(), Long.valueOf(System.currentTimeMillis()));
    setCacheValueWithExpire(token, userId, secondExpire);
    return token;
  }

  @Override
  public Integer parseToken(String token) {
    Integer value = getCacheValueAsInteger(token);
    if (value != null) {
      resetExpire(token, expireSeconds);
    }
    return value;
  }
}
