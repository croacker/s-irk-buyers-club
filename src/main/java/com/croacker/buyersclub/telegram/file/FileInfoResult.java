package com.croacker.buyersclub.telegram.file;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileInfoResult {

    @JsonProperty("file_id")
    private String FileId;

    @JsonProperty("file_unique_id")
    private String FileUniqueId;

    @JsonProperty("file_size")
    private String FileSize;

    @JsonProperty("file_path")
    private String FilePath;

}
