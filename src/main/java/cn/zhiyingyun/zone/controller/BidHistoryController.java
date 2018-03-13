package cn.zhiyingyun.zone.controller;

import cn.zhiyingyun.zone.common.CommonPage;
import cn.zhiyingyun.zone.common.PageUtil;
import cn.zhiyingyun.zone.dto.BidHistoryDto;
import cn.zhiyingyun.zone.dto.page.PageBidHistoryPageQueryDto;
import cn.zhiyingyun.zone.service.IDspBidHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/history", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class BidHistoryController extends BaseController {

  @Autowired
  private IDspBidHistoryService dspBidHistoryService;

  @GetMapping(value = "/page")
  public CommonPage<BidHistoryDto> pageHistory(PageBidHistoryPageQueryDto queryDto, @PageableDefault(size = 10, page = 0, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
    PageRequest pageRequest = PageUtil.createPageRequest(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort());

    initBasePageQuery(queryDto);

    CommonPage<BidHistoryDto> page = dspBidHistoryService.pageHistory(queryDto, pageRequest);

    return page;
  }
}
