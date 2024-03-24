package com.croacker.buyersclub.repo;

import com.croacker.buyersclub.domain.CashCheckLine;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CheckLineRepo extends ReactiveCrudRepository<CashCheckLine, Long> {
}

