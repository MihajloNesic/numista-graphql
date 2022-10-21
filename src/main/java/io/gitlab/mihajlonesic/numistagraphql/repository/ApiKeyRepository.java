package io.gitlab.mihajlonesic.numistagraphql.repository;

import io.gitlab.mihajlonesic.numistagraphql.entity.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {

    @Query("select ak from ApiKey ak order by ak.id")
    List<ApiKey> findAllApiKeys();

    @Query("select ak from ApiKey ak where ak.authKey = :authKey")
    Optional<ApiKey> findByKey(@Param("authKey") String authKey);

    @Query("select ak from ApiKey ak where ak.authKey = :authKey")
    ApiKey getByKey(@Param("authKey") String authKey);

    @Modifying
    @Transactional
    @Query("update ApiKey ak " +
            "set ak.used = 0 " +
            "where ak.quota is not null and ak.used is not null")
    void resetAllApiKeyUsages();
}
