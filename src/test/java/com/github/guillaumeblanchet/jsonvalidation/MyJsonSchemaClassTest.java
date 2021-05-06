package com.github.guillaumeblanchet.jsonvalidation;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.SpecVersion.VersionFlag;
import com.networknt.schema.ValidationMessage;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class MyJsonSchemaClassTest {

  @Test
  public void validateFromJsonSchema() throws JsonProcessingException {
    JsonSchema schema = getJsonSchema("#MyJsonClassSchema.json");
    String json =
             "{\n"
            + "  \"myProperty\": [\"a value\"]\n"
            + "}";
    Set<ValidationMessage> errors = schema.validate(new ObjectMapper().readTree(json));
    assertThat(errors).isEmpty();
  }

  protected JsonSchema getJsonSchema(String schemaId) {
    JsonSchemaFactory factory = JsonSchemaFactory.getInstance(VersionFlag.V7);
    return factory.getSchema(
        "{\n"
            + "  \"$schema\": \"http://json-schema.org/draft-07/schema#\",\n"
            + "  \"$id\": \"#MyJsonClassSchema.json\",\n"
            + "  \"type\": \"object\",\n"
            + "  \"properties\": {\n"
            + "    \"myProperty\": {\n"
            + "      \"oneOf\": [\n"
            + "        {\n"
            + "          \"type\": \"string\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"type\": \"array\",\n"
            + "          \"items\": [\n"
            + "            {\n"
            + "              \"type\": \"string\"\n"
            + "            }\n"
            + "          ]\n"
            + "        }\n"
            + "      ]\n"
            + "    }\n"
            + "  }\n"
            + "}"
    );
  }
}