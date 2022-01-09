package przykladoweKlasy.do_wywalenia;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import db.MyJson;
import przykladoweKlasy.do_wywalenia.AuthorPOJO;
import przykladoweKlasy.do_wywalenia.BookPOJO;
import przykladoweKlasy.do_wywalenia.SimpleTestCaseJsonPOJO;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MyJsonTest {
    private String jsonSource = "{  \"title\": \"to jest mój tytuł\",  \"author\":  \"Paweł Gasz\"}";

    private String authorScenario = "{\n" +
            "  \"authorName\": \"Paweł\",\n" +
            "  \"books\": [\n" +
            "    {\n" +
            "      \"title\": \"title1\",\n" +
            "      \"inPrint\": true,\n" +
            "      \"saleAmount\": 100\n" +
            "    },\n" +
            "    {\n" +
            "      \"title\": \"title2\",\n" +
            "      \"inPrint\": false,\n" +
            "      \"saleAmount\": 2000\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    @org.junit.jupiter.api.Test
    void parse() throws IOException {


            JsonNode node = MyJson.parse(jsonSource);
            System.out.println(node.get("title").asText());
            assertEquals(node.get("title").asText(), "to jest mój tytuł");

    }

    @org.junit.jupiter.api.Test
    void fromJson() throws IOException {


        JsonNode node = MyJson.parse(jsonSource);
        SimpleTestCaseJsonPOJO pojo = MyJson.fromJson(node, SimpleTestCaseJsonPOJO.class);
        System.out.println("title from object: " + pojo.getTitle());
        assertEquals(pojo.getTitle(), "to jest mój tytuł");

    }
    @org.junit.jupiter.api.Test
    void toJson() throws IOException {

        SimpleTestCaseJsonPOJO pojo = new SimpleTestCaseJsonPOJO();
        pojo.setTitle("testowy tytuł");
        pojo.setAuthor("ktoś");
        JsonNode node = MyJson.toJson(pojo);
        System.out.println("tutaj"+node.toString()); // --> JSON całości
        System.out.println(node.get("title"));

        assertEquals(node.get("title").asText(), "testowy tytuł");

    }

    @org.junit.jupiter.api.Test
    void strigify() throws JsonProcessingException {

        SimpleTestCaseJsonPOJO pojo = new SimpleTestCaseJsonPOJO();
        pojo.setTitle("testowy tytuł2");
        JsonNode node = MyJson.toJson(pojo);
        System.out.println(MyJson.strigify(node));


//        assertEquals(node.get("title").asText(), "testowy tytuł");

    }

    @org.junit.jupiter.api.Test
    void strigifyMy() throws JsonProcessingException {

        SimpleTestCaseJsonPOJO pojo = new SimpleTestCaseJsonPOJO();
        pojo.setTitle("moja funkcja stringify");
        System.out.println(MyJson.strigifyMy(pojo));

    }
    @org.junit.jupiter.api.Test
    void prettyPrint() throws JsonProcessingException {

        SimpleTestCaseJsonPOJO pojo = new SimpleTestCaseJsonPOJO();
        pojo.setTitle("testowy tytuł2");
        JsonNode node = MyJson.toJson(pojo);
        System.out.println(MyJson.prettyPrint(node));

    }
    @org.junit.jupiter.api.Test
    void prettyPrintMy() throws JsonProcessingException {

        SimpleTestCaseJsonPOJO pojo = new SimpleTestCaseJsonPOJO();
        pojo.setTitle("testowy tytuł2");
        System.out.println(MyJson.prettyPrintMy(pojo));
    }

    @org.junit.jupiter.api.Test
    void authorScenarioMy() throws IOException {
        AuthorPOJO author1 = MyJson.fromJsonMy(authorScenario, AuthorPOJO.class);

        System.out.println(author1.toString());
    }

    @org.junit.jupiter.api.Test
    void authorScenario() throws IOException {
        JsonNode node = MyJson.parse(authorScenario);
        AuthorPOJO pojo = MyJson.fromJson(node, AuthorPOJO.class);

        System.out.println("Author:  " + pojo.getAuthorName());
        for (BookPOJO bP: pojo.getBooks()){
            System.out.println("Book: "+ bP.getTitle());
            System.out.println("Sprzedaż: "+ bP.getSaleAmount());
            System.out.println("Cy można drukować: "+ bP.isInPrint());
            System.out.println("Book: "+ bP.toString());

        }
    }

}