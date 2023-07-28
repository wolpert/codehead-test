/*
 *   Copyright (c) 2022. Ned Wolpert <ned.wolpert@gmail.com>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */

package com.codeheadsystems.test.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Nullable;
import org.immutables.value.Value;

/**
 * The interface Standard immutable model.
 */
@Value.Immutable
@JsonSerialize(as = ImmutableStandardImmutableModel.class)
@JsonDeserialize(builder = ImmutableStandardImmutableModel.Builder.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public interface StandardImmutableModel {

  /**
   * Some string string.
   *
   * @return the string
   */
  String someString();

  /**
   * Some weird string string.
   *
   * @return the string
   */
  @JsonProperty("some unique String")
  String someWeirdString();

  /**
   * Some int int.
   *
   * @return the int
   */
  @JsonProperty("someInt")
  int someInt();

  /**
   * Bunch of other string list.
   *
   * @return the list
   */
  @JsonProperty("bunchOfString annotated")
  List<String> bunchOfOtherString();

  /**
   * Bunch of string list.
   *
   * @return the list
   */
  List<String> bunchOfString();

  /**
   * Nullable string string.
   *
   * @return the string
   */
  @JsonProperty("nullableString")
  @Nullable
  String nullableString();

  /**
   * Optional string optional.
   *
   * @return the optional
   */
  @JsonProperty("optionalString")
  Optional<String> optionalString();

  /**
   * A map map.
   *
   * @return the map
   */
  @JsonProperty("aMap")
  Map<String, String> aMap();

  /**
   * Default string string.
   *
   * @return the string
   */
  @JsonProperty("defaultString")
  default String defaultString() {
    return "defaultString";
  }
}
