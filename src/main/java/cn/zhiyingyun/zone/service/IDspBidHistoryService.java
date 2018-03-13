package cn.zhiyingyun.zone.service;

import cn.zhiyingyun.zone.common.CommonPage;
import cn.zhiyingyun.zone.domain.DspBidHistory;
import cn.zhiyingyun.zone.dto.BidHistoryDto;
import cn.zhiyingyun.zone.dto.page.PageBidHistoryPageQueryDto;
import org.springframework.data.domain.PageRequest;

public interface IDspBidHistoryService extends IBaseService<DspBidHistory> {

  CommonPage<BidHistoryDto> pageHistory(PageBidHistoryPageQueryDto queryDto, PageRequest pageRequest);
}
