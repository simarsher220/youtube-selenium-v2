package org.codejudge.sb.dao.api;

import org.codejudge.sb.entity.SentimentV2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppV2Repository extends JpaRepository<SentimentV2, Integer> {
}
