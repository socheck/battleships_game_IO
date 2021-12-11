import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;

public class myJson {
    private static  ObjectMapper objectMapper = getDefaultObjectMapper();
    private static ObjectMapper getDefaultObjectMapper(){
        ObjectMapper defaultObjectMapper = new ObjectMapper();
//      if from our API we get more information than is required in class it will not cause errors
        defaultObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        defaultObjectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//        defaultObjectMapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
        return defaultObjectMapper;
    }
//    String --> JsonNode (part of Jackson library)
    public static JsonNode parse(String src) throws IOException {
        return objectMapper.readTree(src);
    }

//    String --> some Class
    public static <A> A fromJson(JsonNode node, Class<A> clazz) throws JsonProcessingException {
        return objectMapper.treeToValue(node, clazz);
    }


//    some Class --> JSON (node which can be use like JS Object [name_od_node.get("attribute_name")])
    public static JsonNode toJson(Object a){
        return objectMapper.valueToTree(a);
    }

    public static String strigify(JsonNode node) throws JsonProcessingException {
        ObjectWriter objectWriter = objectMapper.writer();

        return objectWriter.writeValueAsString(node);
    }
//========================================================================
    public static String strigifyMy(Object a) throws JsonProcessingException {
        JsonNode node = toJson(a);
        ObjectWriter objectWriter = objectMapper.writer();

        return objectWriter.writeValueAsString(node);
    }

    public static String prettyPrintMy(Object a) throws JsonProcessingException {
        JsonNode node = toJson(a);
        ObjectWriter objectWriter = objectMapper.writer();
        objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT);

        return objectWriter.writeValueAsString(node);
    }


    //    String --> some Class
    public static <A> A fromJsonMy(String src, Class<A> clazz) throws IOException {
        return objectMapper.treeToValue(objectMapper.readTree(src), clazz);
    }
//==========================================================================
    public static String prettyPrint(JsonNode node) throws JsonProcessingException {
        ObjectWriter objectWriter = objectMapper.writer();
        objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT);

        return objectWriter.writeValueAsString(node);
    }

}
