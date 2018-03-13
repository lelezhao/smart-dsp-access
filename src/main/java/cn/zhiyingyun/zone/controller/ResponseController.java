package cn.zhiyingyun.zone.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/response", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ResponseController extends BaseController {
}
