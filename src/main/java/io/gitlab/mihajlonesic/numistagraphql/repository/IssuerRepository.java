package io.gitlab.mihajlonesic.numistagraphql.repository;

import io.gitlab.mihajlonesic.numistagraphql.entity.Issuer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssuerRepository extends JpaRepository<Issuer, Long> {

    @Query("select i " +
           "from Issuer i " +
           "order by i.name")
    List<Issuer> findAllIssuers();

}