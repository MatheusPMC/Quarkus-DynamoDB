package org.study.adapter.in.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
@Getter
@Setter
@ToString
public class ClientModel {
    private UUID id;

    @JsonProperty("name")
    @org.jetbrains.annotations.NotNull
    private String name;

    @JsonProperty("birthday")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate dateOfBirthday;

    @JsonProperty("gender")
    @Pattern(regexp = "M|F")
    private String gender;
}