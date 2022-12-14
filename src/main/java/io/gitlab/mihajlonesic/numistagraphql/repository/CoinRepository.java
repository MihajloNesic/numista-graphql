package io.gitlab.mihajlonesic.numistagraphql.repository;

import io.gitlab.mihajlonesic.numistagraphql.entity.Coin;
import io.gitlab.mihajlonesic.numistagraphql.entity.domain.Composition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoinRepository extends JpaRepository<Coin, Long> {

    @Query("select c " +
           "from Coin c " +
           "where c.id = :coinId")
    Optional<Coin> findById(@Param("coinId") Long coinId);

    @Query("select c " +
           "from Coin c " +
           "order by c.title")
    List<Coin> findAllCoins();

    Page<Coin> findByIssuerId(Long issuerId, Pageable pageable);

    Page<Coin> findByIssuerIdAndCompositionIn(Long issuerId, List<Composition> compositionList, Pageable pageable);

    Page<Coin> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}