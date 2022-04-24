package code.adapt;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TextProcessingTest {
    @Test
    public void first_challenge() {
        String text = "Hello, this is an example for you to practice. You should grab\n";
        text += "this text and make it as your test case.";
        Processor processor = new ProcessorImpl();
        List<String> expectedListTopOne = Arrays.asList("you", "this");
        List<String> expectedListTopTwo = Arrays.asList("your", "to", "text", "test", "should", "practice", "make", "it");

        processor.analyse(text);

        List<Map.Entry<String, Integer>> list = processor.getResults();
        assertEquals(21, processor.getProcessedWords());
        assertCorrectOutputNotConsideringOrders(list, expectedListTopOne, expectedListTopTwo);
    }

    private void assertCorrectOutputNotConsideringOrders(List<Map.Entry<String, Integer>> list, List<String> expectedListTopOne, List<String> expectedListTopTwo) {
        List<String> listTopOne = list.stream().filter(p -> p.getValue() == 2).map(p -> p.getKey()).collect(Collectors.toList());
        List<String> listTopTwo = list.stream().filter(p -> p.getValue() == 1).map(p -> p.getKey()).collect(Collectors.toList());
        assertTrue(listTopOne.containsAll(expectedListTopOne));
        assertTrue(listTopTwo.containsAll(expectedListTopTwo));
    }

    @Test
    public void second_challenge() {
        String text = "Hello, this is an example for you to practice. You should grab\n";
        text += "this text and make it as your test case.";
        text += "` ` `javascript";
        text += "if (true) {";
        text += "console.log('should should should')";
        text += "}";
        text += "` ` `";

        Processor processor = new ProcessorImpl();
        List<String> expectedListTopOne = Arrays.asList("you", "this");
        List<String> expectedListTopTwo = Arrays.asList("your", "to", "text", "test", "should", "practice", "make", "it");

        processor.analyse(text);

        List<Map.Entry<String, Integer>> list = processor.getResults();
        assertEquals(21, processor.getProcessedWords());
        assertCorrectOutputNotConsideringOrders(list, expectedListTopOne, expectedListTopTwo);
    }
}
