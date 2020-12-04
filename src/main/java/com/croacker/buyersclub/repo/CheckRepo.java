package com.croacker.buyersclub.repo;

import com.croacker.buyersclub.domain.Check;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CheckRepo extends CrudRepository<Check, Long> {

    List<Check> findByDeletedIsFalse(Pageable pageable);

}

