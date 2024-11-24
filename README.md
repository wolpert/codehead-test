# Test utilities

## Immutable/Jackson JSON tests

Tests the ability for your **Immutables** objects to be JSON friendly. Validates you have all the jackson
annotations setup correctly. It can handle complex structures as well as simple ones. Allows for custom
ObjectMapper to be used for the tests as well. See the state machine project for usage examples.

I've seen variations of this from different places and made my own version that uses junit5 and assertJ.

Java 11 Compatible.

## Status
![CodeHead-Test Build](https://github.com/wolpert/codehead-test/actions/workflows/gradle.yml/badge.svg)

## Release

| Library       | Purpose                           | Version                                                                                   |
|---------------|-----------------------------------|-------------------------------------------------------------------------------------------|
| codehead-test | Core Library                      | ![codehead-test](https://img.shields.io/maven-central/v/com.codeheadsystems/codehead-test) |

gradle
```groovy
dependencies {
    testImplementation 'com.codeheadsystems:codehead-test:VERSION'
}
```

## Usage

```java
@Value.Immutable
@JsonSerialize(as = ImmutableStandardImmutableModel.class)
@JsonDeserialize(builder = ImmutableStandardImmutableModel.Builder.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public interface StandardImmutableModel {
    @JsonProperty("someString")
    String someString();
}

public class StandardImmutableModelTest extends BaseJacksonTest<StandardImmutableModel> {
    @Override
    protected Class<StandardImmutableModel> getBaseClass() {
        return StandardImmutableModel.class;
    }
    @Override
    protected StandardImmutableModel getInstance() {
        return ImmutableStandardImmutableModel.builder()
            .someString("this string")
            .build();
    }
}
```

## Features

* Works with optional, @nullable fields.
* Compatible with @JsonIgnore.
* Handles maps/lists/sets as well.
* Jupiter and AssertJ focused.
* Polymorphic testing. Ensure your base class can marshal to your implementing one
via implementing  `getPolymorphicBaseClass()`
