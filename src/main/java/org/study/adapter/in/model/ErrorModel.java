package org.study.adapter.in.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorModel {
    @JsonProperty("code")
    private Integer code;
    @JsonProperty("message")
    private String message;
    @Singular
    @JsonProperty("fields")
    private List<ErrorModelField> fields;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder(setterPrefix = "with")
    public static class ErrorModelField {
        @JsonProperty("name")
        private String name;

        @JsonProperty("value")
        private Object value;
    }
}