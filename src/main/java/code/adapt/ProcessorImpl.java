package code.adapt;

import java.util.*;

public class ProcessorImpl implements Processor {
    private static final String SPACE = " ";
    private static final String COMMA = ",";
    private static final String NEWLINE = "\n";
    private static final String PERIOD = ".";
    private static final String DOUBLE_SPACE = "  ";
    public static final String JAVASCRIPT_CODE = "(` ` `)javascript(.|\\n)*(` ` `)";
    private Map<String, Integer> map;
    private int processedWords = 0;

    @Override
    public void analyse(String text) {
        this.map = new HashMap<String, Integer>();
        String input = getProcessedInput(text);
        processWords(input);
    }

    private void processWords(String input) {
        String[] words = input.split(SPACE);
        for (String word : words) {
            if (this.map.containsKey(word)) {
                int n = this.map.get(word);
                this.map.put(word, ++n);
            } else {
                this.map.put(word, 1);
            }
        }
        processedWords = words.length;
    }

    private String getProcessedInput(String text) {
        String input = text.toLowerCase();
        input = input.replaceAll(JAVASCRIPT_CODE,"");
        input = input.replaceAll("[" + COMMA+PERIOD+NEWLINE+ "]", SPACE);
        input = input.replace(DOUBLE_SPACE, SPACE);
        return input;
    }

    @Override
    public List<Map.Entry<String, Integer>> getResults() {
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(this.map.entrySet());
        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return Integer.compare(o2.getValue(), o1.getValue());
            }
        });

        return list;
    }

    @Override
    public int getProcessedWords() {
        return processedWords;
    }
}
