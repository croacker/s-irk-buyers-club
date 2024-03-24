package com.croacker.buyersclub.repo;

import com.croacker.buyersclub.domain.CashCheck;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.List;
import java.util.Optional;

public interface CheckRepo extends ReactiveCrudRepository<CashCheck, Long> {

    List<CashCheck> findByDeletedIsFalse(Pageable pageable);

    Optional <CashCheck> findByKktRegIdAndFiscalDriveNumberAndFiscalDocumentNumber(String kktRegId,
                                                                                   String fiscalDriveNumber,
                                                                                   String fiscalDocumentNumber);

}

