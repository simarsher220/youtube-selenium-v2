package org.codejudge.sb.dao.api;

import org.codejudge.sb.entity.Sentiment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRepository extends JpaRepository<Sentiment, Integer> {

    @Query(value = "SELECT s FROM Sentiment s where s.id = :id")
    Sentiment getById(@Param("id") Integer id);
}
