package com.github.guillaumeblanchet.jsonvalidation;

import static org.assertj.core.api.Assertions.assertThat;

import com.jayway.jsonpath.JsonPath;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MyJsonPathClassTest {

  private String validJson;
  private Validator validator;

  @Test
  public void validateWithJsonPath() {
    List<String> value =
        JsonPath.read(validJson, "$..myProperty.myPropertyValue");
    assertThat(value).hasSize(1).first().isEqualTo("a value");
  }

  @BeforeEach
  void setUp() {
    this.validator = new Validator();
    validJson =
        "{\n"
            + "  \"myProperty\": {\n"
            + "    \"myProperty\": {\n"
            + "      \"myPropertyValue\": \"a value\"\n"
            + "    }\n"
            + "  }\n"
            + "}";
  }

  static class Validator {
    String validate(String json) {
      List<String> values = JsonPath
          .parse(json).read("$..['myProperty']['myPropertyValue']");
      if (values.isEmpty()) {
        return "There is no myPropertyValue under myProperty";
      }
      return "ok!";
    }
  }

  @Test
  public void validateAnInvalidity() {
    validJson = JsonPath
        .parse(this.validJson)
        .delete("$..['myProperty']['myPropertyValue']")
        .jsonString();
    assertThat(this.validator.validate(validJson))
        .isEqualTo("There is no myPropertyValue under myProperty");
  }
}
