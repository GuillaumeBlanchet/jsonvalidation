package com.github.guillaumeblanchet.jsonvalidation;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

public class MyJsonClassTest {

  public static class MyJsonClass {

    private String myProperty;

    public String getMyProperty() {
      return myProperty;
    }
  }

  @Test
  public void validateMyJsonClass()
      throws JsonProcessingException {
    String json =
              "{\n"
            + "  \"myProperty\": \"a value\"\n"
            + "}";
    MyJsonClass myJsonClass = new ObjectMapper()
        .readValue(json, MyJsonClass.class);
    assertThat(myJsonClass.getMyProperty())
        .isEqualTo("a value");
  }

  public static class MyNestedJsonClass {

    private MyNestedJsonClass myProperty;

    public String getMyPropertyValue() {
      return myPropertyValue;
    }

    private String myPropertyValue;

    public MyNestedJsonClass getMyProperty() {
      return myProperty;
    }
  }

  @Test
  public void validateMyNestedJsonClass()
      throws JsonProcessingException {
    String json =
            "{\n"
                + "  \"myProperty\": {\n"
                + "    \"myProperty\": {\n"
                + "      \"myPropertyValue\": \"a value\"\n"
                + "    }\n"
                + "  }\n"
                + "}";
    MyNestedJsonClass myNestedJsonClass = new ObjectMapper()
        .readValue(json, MyNestedJsonClass.class);
    assertThat(myNestedJsonClass.getMyProperty().getMyProperty().getMyPropertyValue())
        .isEqualTo("a value");
  }
}