package org.study.domain.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.*;
import org.study.domain.enumerations.Gender;
import org.study.domain.mapper.GenderConverter;
import org.study.domain.mapper.LocalDateConverter;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
@ToString
@Getter
@Setter
@DynamoDBTable(tableName = "Clients")
public class ClientEntity {
    private static final long serialVersionUID = 1L;

    @DynamoDBHashKey(attributeName = "Id")
    @DynamoDBAutoGeneratedKey
    private UUID id;

    @DynamoDBAttribute(attributeName = "Name")
    private String name;

    @DynamoDBAttribute(attributeName = "Birthday")
    @DynamoDBTypeConverted(converter = LocalDateConverter.class)
    private LocalDate dateOfBirthday;

    @DynamoDBAttribute(attributeName = "Gender")
    @DynamoDBTypeConverted(converter = GenderConverter.class)
    private Gender gender;
}