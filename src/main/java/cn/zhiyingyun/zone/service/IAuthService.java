package cn.zhiyingyun.zone.service;

import cn.zhiyingyun.zone.dto.TokenInfo;
import cn.zhiyingyun.zone.exception.UnauthorizedException;

/**
 * Created by Joy.Zhao on 17/6/22.
 */
public interface IAuthService {
  /**
   * 登录
   *
   * @param userName
   * @param passwordMd5
   * @return
   * @throws UnauthorizedException
   */
  TokenInfo login(String userName, String passwordMd5) throws UnauthorizedException;

  /**
   * 生成token
   *
   * @param userId
   * @param secondExpire
   * @return
   */
  String generateToken(Integer userId, long secondExpire);

  /**
   * @param token
   * @return
   */
  Integer parseToken(String token);
}
