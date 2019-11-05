package com.kys.openapi.keyword.domain.repository;

import com.kys.openapi.keyword.domain.KeyWordHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 키워드 레파지토리
 */
@Repository
public interface KeyWordRepository extends CrudRepository<KeyWordHistory, Long> {
}
