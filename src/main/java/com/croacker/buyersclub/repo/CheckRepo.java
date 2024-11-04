package com.croacker.buyersclub.repo;

import com.croacker.buyersclub.domain.CashCheck;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface CheckRepo extends ReactiveCrudRepository<CashCheck, Long> {

    Flux<CashCheck> findByDeletedIsFalse(Pageable pageable);

    Mono<CashCheck> findByKktRegIdAndFiscalDriveNumberAndFiscalDocumentNumber(
            String kktRegId,
            String fiscalDriveNumber,
            String fiscalDocumentNumber
    );

}

