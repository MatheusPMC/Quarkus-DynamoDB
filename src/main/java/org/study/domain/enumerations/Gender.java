package org.study.domain.enumerations;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public enum Gender {

    MALE('M'),
    FEMALE('F');

    private final Character prefix;

    public static Gender fromPrefix(Character prefix) {
        return Stream.of(Gender.values())
                .filter(gender -> gender.getPrefix().equals(prefix))
                .findFirst().orElse(null);
    }

    public static Character fromGender(Gender gender) {
        return gender.prefix;
    }
}