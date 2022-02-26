
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class WordleTest {

    @Test
    void wordle() {

        Assertions.assertThat(new Wordle("aaaaa")
                .guess("bbbbb")).isEqualTo(".....");
        Assertions.assertThat(new Wordle("aaaaa")
                .guess("abbbb")).isEqualTo("A....");
        Assertions.assertThat(new Wordle("aaaaa")
                .guess("babbb")).isEqualTo(".A...");
        Assertions.assertThat(new Wordle("aaaaa")
                .guess("bbabb")).isEqualTo("..A..");
        Assertions.assertThat(new Wordle("aaaaa")
                .guess("bbbab")).isEqualTo("...A.");
        Assertions.assertThat(new Wordle("aaaaa")
                .guess("bbbba")).isEqualTo("....A");



    }
}