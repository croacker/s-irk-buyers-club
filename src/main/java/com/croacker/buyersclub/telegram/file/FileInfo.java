package com.croacker.buyersclub.telegram.file;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileInfo {

    @JsonProperty("ok")
    private boolean ok;

    @JsonProperty("result")
    private FileInfoResult result;

}
