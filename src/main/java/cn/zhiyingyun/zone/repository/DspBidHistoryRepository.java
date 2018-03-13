package cn.zhiyingyun.zone.repository;

import cn.zhiyingyun.zone.domain.DspBidHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DspBidHistoryRepository extends JpaRepository<DspBidHistory, Integer> {

  Page<DspBidHistory> findAll(Specification<DspBidHistory> specification, Pageable pageable);
}
