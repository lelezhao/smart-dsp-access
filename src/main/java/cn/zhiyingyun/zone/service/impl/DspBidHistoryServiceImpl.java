package cn.zhiyingyun.zone.service.impl;

import cn.zhiyingyun.zone.common.BeanUtils;
import cn.zhiyingyun.zone.common.CommonPage;
import cn.zhiyingyun.zone.domain.DspBidHistory;
import cn.zhiyingyun.zone.dto.BidHistoryDto;
import cn.zhiyingyun.zone.dto.page.PageBidHistoryPageQueryDto;
import cn.zhiyingyun.zone.repository.DspBidHistoryRepository;
import cn.zhiyingyun.zone.repository.criteria.Criteria;
import cn.zhiyingyun.zone.repository.criteria.Restrictions;
import cn.zhiyingyun.zone.service.IDspBidHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DspBidHistoryServiceImpl extends BaseServiceImpl<DspBidHistoryRepository, DspBidHistory> implements IDspBidHistoryService {

  @Autowired
  private DspBidHistoryRepository dspBidHistoryRepository;

  @Override
  public CommonPage<BidHistoryDto> pageHistory(PageBidHistoryPageQueryDto queryDto, PageRequest pageRequest) {

    Criteria<DspBidHistory> criteria = new Criteria<>();

    criteria.add(Restrictions.equal("userId", queryDto.getUserId()));

    Page<DspBidHistory> historyPage = dspBidHistoryRepository.findAll(criteria, pageRequest);

    List<DspBidHistory> histories = historyPage.getContent();

    List<BidHistoryDto> historyDtos = new ArrayList<>();

    for (DspBidHistory dspBidHistory : histories) {
      BidHistoryDto bidHistoryDto = new BidHistoryDto();

      BeanUtils.copyProperties(dspBidHistory, bidHistoryDto);

      historyDtos.add(bidHistoryDto);
    }

    CommonPage<BidHistoryDto> dtoPage = buildCommonPage(historyDtos, historyPage);

    return dtoPage;
  }
}
