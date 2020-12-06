package com.croacker.buyersclub.repo;

import com.croacker.buyersclub.domain.CashCheckLine;
import org.springframework.data.repository.CrudRepository;

public interface CheckLineRepo extends CrudRepository<CashCheckLine, Long> {
}

