package cn.zhiyingyun.zone.filter;

import cn.zhiyingyun.zone.common.JsonResult;
import cn.zhiyingyun.zone.service.IAuthService;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by kalrey on 17/4/13.
 */

@Component
public class AuthFilter implements Filter {
  final static org.slf4j.Logger logger = getLogger(AuthFilter.class);

  @Autowired
  private IAuthService authService;

  @Value("${filter.auth.exclude}")
  private String[] excludeUrls;
  @Value("${filter.auth.manage}")
  private String[] manageUrls;
  @Value("${auth.manage.token}")
  private String manageToken;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
    HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
    String path = httpServletRequest.getServletPath();

    for (String excludeUrl : excludeUrls) {

      if (path.equals(excludeUrl)) {
        filterChain.doFilter(servletRequest, servletResponse);
        return;
      }
    }

    String mgnToken = httpServletRequest.getHeader("X-Manager-Token");

    for (String manageUrl : manageUrls) {
      if (path.startsWith(manageUrl) && StringUtils.isNotEmpty(mgnToken)) {
        if (mgnToken.equals(manageToken)) {
          httpServletRequest.setAttribute("X-User-Id", 0);
          filterChain.doFilter(servletRequest, servletResponse);
          return;
        }
      }
    }

    String token = httpServletRequest.getHeader("X-Zone-Token");

    if (token == null) {
      respondResult(httpServletResponse, HttpStatus.UNAUTHORIZED, null, null);
      return;
    } else {
      Integer userId = authService.parseToken(token);

      if (userId == null) {
        respondResult(httpServletResponse, HttpStatus.UNAUTHORIZED, null, null);
        return;
      } else {
        httpServletRequest.setAttribute("X-User-Id", userId);
        filterChain.doFilter(servletRequest, servletResponse);
      }
    }
  }

  void respondResult(HttpServletResponse httpServletResponse, HttpStatus statusCode, String message, Object data) throws IOException {
    JsonResult jsonResult = new JsonResult();
    jsonResult.setCode(statusCode);
    if (message == null) {
      jsonResult.setMessage(statusCode.getReasonPhrase());
    } else {
      jsonResult.setMessage(message);
    }

    jsonResult.setData(data);
    httpServletResponse.setStatus(HttpStatus.OK.value());
    httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
    PrintWriter out = httpServletResponse.getWriter();

    out.println(JSONObject.toJSONString(jsonResult, true));

    out.close();
  }

  @Override
  public void destroy() {

  }
}
