import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import static org.assertj.core.api.BDDAssertions.then;


@RunWith(JUnit4.class)
public class InputTextParserTest {
    private InputTextParser inputTextParser = new InputTextParser();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void whenMapWordsToLetters_givenNullableInput_thenThrowException() {
        //given
        String inputText = null;

        //then
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Input text cannot be null or empty value");

        //when
        inputTextParser.mapWordsToLetters(inputText);
    }

    @Test
    public void whenMapWordsToLetters_givenEmptyInput_thenThrowException() {
        //given
        String inputText = "";

        //then
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Input text cannot be null or empty value");

        //when
        inputTextParser.mapWordsToLetters(inputText);
    }

    @Test
    public void whenMapWordsToLetters_givenOnlySpecialSings_thenReturnEmptyStructure() {
        //given
        String text = " ,,,,.$%^&* ^&#&%^><<? *.*.*.*";

        //when
        Map<Character, TreeSet<String>> resultStructure = inputTextParser.mapWordsToLetters(text);

        //then
        then(resultStructure).hasSize(0);
    }

    @Test
    public void whenMapWordsToLetters_givenDifferentCaseOfLettersInWords_thenReturnCorrectStructure() {
        //given
        String inputText = "ala, ala, Ala, ALa, ALA, aLa, aLA, alA";

        //when
        Map<Character, TreeSet<String>> resultStructure = inputTextParser.mapWordsToLetters(inputText);

        //then
        then(resultStructure).hasSize(2);
        List<String> aWords = new ArrayList<>(resultStructure.get('a'));
        List<String> lWords = new ArrayList<>(resultStructure.get('l'));
        then(resultStructure.keySet()).containsSequence('a', 'l');
        then(aWords).isEqualTo(Collections.singletonList("ala"));
        then(lWords).isEqualTo(Collections.singletonList("ala"));
    }

    @Test
    public void whenMapWordsToLetters_givenInputWithSpecialSigns_thenReturnCorrectStructure() {
        //given
        String text = "john.doe123@gmail.com";

        //when
        Map<Character, TreeSet<String>> resultStructure = inputTextParser.mapWordsToLetters(text);

        //then
        then(resultStructure).hasSize(12);
        List<String> jWords = new ArrayList<>(resultStructure.get('j'));
        List<String> dWords = new ArrayList<>(resultStructure.get('d'));
        List<String> gWords = new ArrayList<>(resultStructure.get('g'));
        List<String> cWords = new ArrayList<>(resultStructure.get('c'));

        then(resultStructure.keySet()).containsSequence('a', 'c', 'd', 'e', 'g', 'h', 'i', 'j', 'l', 'm', 'n', 'o');
        then(jWords).isEqualTo(Collections.singletonList("john"));
        then(dWords).isEqualTo(Collections.singletonList("doe123"));
        then(gWords).isEqualTo(Collections.singletonList("gmail"));
        then(cWords).isEqualTo(Collections.singletonList("com"));
    }

    @Test
    public void whenMapWordsToLetters_givenInputStartedBySpecialSings_thenReturnEmptyStructure() {
        //given
        String text = ",,,,.john 123john";

        //when
        Map<Character, TreeSet<String>> resultStructure = inputTextParser.mapWordsToLetters(text);

        //then
        then(resultStructure).hasSize(4);
        List<String> hKeyWords = new ArrayList<>(resultStructure.get('h'));
        List<String> cKeyWords = new ArrayList<>(resultStructure.get('j'));
        List<String> dKeyWords = new ArrayList<>(resultStructure.get('n'));
        List<String> oKeyWords = new ArrayList<>(resultStructure.get('o'));

        then(resultStructure.keySet()).containsSequence('h', 'j', 'n', 'o');
        then(hKeyWords).isEqualTo(Arrays.asList("123john", "john"));
        then(cKeyWords).isEqualTo(Arrays.asList("123john", "john"));
        then(dKeyWords).isEqualTo(Arrays.asList("123john", "john"));
        then(oKeyWords).isEqualTo(Arrays.asList("123john", "john"));
    }

    @Test
    public void whenMapWordsToLetters_givenInputWithMultipleSpacesAndSpecialSigns_thenReturnCorrectStructure() {
        //given
        String text = "john      /john doe@ j.o.h.n";

        //when
        Map<Character, TreeSet<String>> resultStructure = inputTextParser.mapWordsToLetters(text);

        //then
        List<String> jWords = new ArrayList<>(resultStructure.get('j'));

        then(resultStructure).hasSize(6);
        then(resultStructure.keySet()).containsSequence('d', 'e', 'h', 'j', 'n', 'o');
        then(jWords).isEqualTo(Arrays.asList("j", "john"));
    }

    @Test
    public void whenMapWordsToLetters_givenInputWithSpecialCharactersAndDoubledWords_thenReturnCorrectStructure() {
        //given
        String text = "ala ma kota, kot koduje w Javie Kota";

        //when
        Map<Character, TreeSet<String>> resultStructure = inputTextParser.mapWordsToLetters(text);

        //then
        then(resultStructure).hasSize(13);
        List<String> aWords = new ArrayList<>(resultStructure.get('a'));
        List<String> dWords = new ArrayList<>(resultStructure.get('d'));
        List<String> eWords = new ArrayList<>(resultStructure.get('e'));
        List<String> iWords = new ArrayList<>(resultStructure.get('i'));
        List<String> jWords = new ArrayList<>(resultStructure.get('j'));
        List<String> kWords = new ArrayList<>(resultStructure.get('k'));
        List<String> lWords = new ArrayList<>(resultStructure.get('l'));
        List<String> mWords = new ArrayList<>(resultStructure.get('m'));
        List<String> oWords = new ArrayList<>(resultStructure.get('o'));
        List<String> tWords = new ArrayList<>(resultStructure.get('t'));
        List<String> uWords = new ArrayList<>(resultStructure.get('u'));
        List<String> vWords = new ArrayList<>(resultStructure.get('v'));
        List<String> wWords = new ArrayList<>(resultStructure.get('w'));

        then(resultStructure.keySet()).containsSequence('a', 'd', 'e', 'i', 'j', 'k', 'l', 'm', 'o', 't', 'u', 'v', 'w');
        then(aWords).isEqualTo(Arrays.asList("ala", "javie", "kota", "ma"));
        then(dWords).isEqualTo(Arrays.asList("koduje"));
        then(eWords).isEqualTo(Arrays.asList("javie", "koduje"));
        then(iWords).isEqualTo(Arrays.asList("javie"));
        then(jWords).isEqualTo(Arrays.asList("javie", "koduje"));
        then(kWords).isEqualTo(Arrays.asList("koduje", "kot", "kota"));
        then(lWords).isEqualTo(Arrays.asList("ala"));
        then(mWords).isEqualTo(Arrays.asList("ma"));
        then(oWords).isEqualTo(Arrays.asList("koduje", "kot", "kota"));
        then(tWords).isEqualTo(Arrays.asList("kot", "kota"));
        then(uWords).isEqualTo(Arrays.asList("koduje"));
        then(vWords).isEqualTo(Arrays.asList("javie"));
        then(wWords).isEqualTo(Arrays.asList("w"));
    }
}
