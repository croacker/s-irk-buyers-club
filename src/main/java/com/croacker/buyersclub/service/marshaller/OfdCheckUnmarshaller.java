package com.croacker.buyersclub.service.marshaller;

import com.croacker.buyersclub.service.ofd.OfdCheck;
import com.croacker.buyersclub.service.ofd.OfdRoot;
import com.croacker.buyersclub.service.ofd.excerpt.OfdCheckExcerpt;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

@Component
@Slf4j
public class OfdCheckUnmarshaller implements Function<String, List<OfdCheck>> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<OfdCheck> apply(String str) {
        List<OfdCheck> result = Collections.emptyList();
        try {
            result = mapper.readValue(str, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }

}
