import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

class InputTextParser {
    Map<Character, TreeSet<String>> mapWordsToLetters(String textToBeParsed) {
        if (textToBeParsed == null || textToBeParsed.isEmpty()) {
            throw new IllegalArgumentException("Input text cannot be null or empty value");
        }

        Set<String> uniqueWords = splitTextIntoUniqueWords(textToBeParsed);
        Map<Character, TreeSet<String>> wordsAssignedToLetter = assignWordToLetter(uniqueWords);

        return wordsAssignedToLetter;
    }

    private Set<String> splitTextIntoUniqueWords(String textToBeParsed) {
        String[] allWordsFromText = textToBeParsed.split(" ");
        Set<String> uniqueWords = new HashSet<>();

        for (String word : allWordsFromText) {
            int startIndex = 0;
            for (int i = 0; i < word.length(); i++) {
                if (!Character.isLetterOrDigit(word.charAt(i))) {
                    uniqueWords.add(word.substring(startIndex, i).toLowerCase());
                    startIndex = i + 1;
                } else if (i == word.length() - 1) {
                    uniqueWords.add(word.substring(startIndex, i + 1).toLowerCase());
                }
            }
        }

        return uniqueWords;
    }

    private Map<Character, TreeSet<String>> assignWordToLetter(Set<String> uniqueWords) {
        Map<Character, TreeSet<String>> wordsAssignedToLetter = new HashMap<>();

        for (String word : uniqueWords) {
            for (int i = 0; i < word.length(); i++) {
                char sign = word.charAt(i);
                if (Character.isLetter(sign)) {
                    if (!wordsAssignedToLetter.containsKey(sign)) {
                        wordsAssignedToLetter.put(sign, new TreeSet<>());
                    }

                    wordsAssignedToLetter.get(sign).add(word);
                }
            }
        }

        return wordsAssignedToLetter;
    }
}