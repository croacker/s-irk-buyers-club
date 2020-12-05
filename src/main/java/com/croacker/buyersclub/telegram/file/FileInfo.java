package com.croacker.buyersclub.telegram.file;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FileInfo {

    @JsonProperty("ok")
    private boolean ok;

    @JsonProperty("result")
    private FileInfo result;

}
