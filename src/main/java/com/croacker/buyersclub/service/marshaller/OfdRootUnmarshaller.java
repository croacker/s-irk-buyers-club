package com.croacker.buyersclub.service.marshaller;

import com.croacker.buyersclub.service.ofd.OfdRoot;
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
public class OfdRootUnmarshaller implements Function<String, List<OfdRoot>> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<OfdRoot> apply(String str) {
        List<OfdRoot> result = Collections.emptyList();
        try {
            result = mapper.readValue(str, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }

}
