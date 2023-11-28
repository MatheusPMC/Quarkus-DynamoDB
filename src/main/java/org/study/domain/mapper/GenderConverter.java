package org.study.domain.mapper;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import org.study.domain.enumerations.Gender;

public class GenderConverter implements DynamoDBTypeConverter<String, Gender> {

    @Override
    public String convert(Gender gender) {
        if (gender == null)
            return null;
        return gender.getPrefix().toString();
    }

    @Override
    public Gender unconvert(String value) {
        if (value == null)
            return null;
        return Gender.fromPrefix(value.charAt(0));
    }
}