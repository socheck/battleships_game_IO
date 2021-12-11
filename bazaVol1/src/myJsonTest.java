import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import przykladoweKlasy.AuthorPOJO;
import przykladoweKlasy.BookPOJO;
import przykladoweKlasy.SimpleTestCaseJsonPOJO;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class myJsonTest {
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


            JsonNode node = myJson.parse(jsonSource);
            System.out.println(node.get("title").asText());
            assertEquals(node.get("title").asText(), "to jest mój tytuł");

    }

    @org.junit.jupiter.api.Test
    void fromJson() throws IOException {


        JsonNode node = myJson.parse(jsonSource);
        SimpleTestCaseJsonPOJO pojo = myJson.fromJson(node, SimpleTestCaseJsonPOJO.class);
        System.out.println("title from object: " + pojo.getTitle());
        assertEquals(pojo.getTitle(), "to jest mój tytuł");

    }
    @org.junit.jupiter.api.Test
    void toJson() throws IOException {

        SimpleTestCaseJsonPOJO pojo = new SimpleTestCaseJsonPOJO();
        pojo.setTitle("testowy tytuł");
        pojo.setAuthor("ktoś");
        JsonNode node = myJson.toJson(pojo);
        System.out.println("tutaj"+node.toString()); // --> JSON całości
        System.out.println(node.get("title"));

        assertEquals(node.get("title").asText(), "testowy tytuł");

    }

    @org.junit.jupiter.api.Test
    void strigify() throws JsonProcessingException {

        SimpleTestCaseJsonPOJO pojo = new SimpleTestCaseJsonPOJO();
        pojo.setTitle("testowy tytuł2");
        JsonNode node = myJson.toJson(pojo);
        System.out.println(myJson.strigify(node));


//        assertEquals(node.get("title").asText(), "testowy tytuł");

    }

    @org.junit.jupiter.api.Test
    void strigifyMy() throws JsonProcessingException {

        SimpleTestCaseJsonPOJO pojo = new SimpleTestCaseJsonPOJO();
        pojo.setTitle("moja funkcja stringify");
        System.out.println(myJson.strigifyMy(pojo));

    }
    @org.junit.jupiter.api.Test
    void prettyPrint() throws JsonProcessingException {

        SimpleTestCaseJsonPOJO pojo = new SimpleTestCaseJsonPOJO();
        pojo.setTitle("testowy tytuł2");
        JsonNode node = myJson.toJson(pojo);
        System.out.println(myJson.prettyPrint(node));

    }
    @org.junit.jupiter.api.Test
    void prettyPrintMy() throws JsonProcessingException {

        SimpleTestCaseJsonPOJO pojo = new SimpleTestCaseJsonPOJO();
        pojo.setTitle("testowy tytuł2");
        System.out.println(myJson.prettyPrintMy(pojo));
    }

    @org.junit.jupiter.api.Test
    void authorScenarioMy() throws IOException {
        AuthorPOJO author1 = myJson.fromJsonMy(authorScenario, AuthorPOJO.class);

        System.out.println(author1.toString());
    }

    @org.junit.jupiter.api.Test
    void authorScenario() throws IOException {
        JsonNode node = myJson.parse(authorScenario);
        AuthorPOJO pojo = myJson.fromJson(node, AuthorPOJO.class);

        System.out.println("Author:  " + pojo.getAuthorName());
        for (BookPOJO bP: pojo.getBooks()){
            System.out.println("Book: "+ bP.getTitle());
            System.out.println("Sprzedaż: "+ bP.getSaleAmount());
            System.out.println("Cy można drukować: "+ bP.isInPrint());
            System.out.println("Book: "+ bP.toString());

        }
    }

}