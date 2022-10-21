package io.gitlab.mihajlonesic.numistagraphql.repository;

import io.gitlab.mihajlonesic.numistagraphql.entity.Mintage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MintageRepository extends JpaRepository<Mintage, Long> {

    @Query("select m " +
           "from Mintage m " +
           "where m.coin.id = :coinId " +
           "order by m.year")
    List<Mintage> findAllMintageByCoin(@Param("coinId") Long coinId);

}