package kr.hs.dgsw.java.noticeboard.repository;

import kr.hs.dgsw.java.noticeboard.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<BoardEntity, Integer> {
}
