package calculator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.Arrays;

@SuppressWarnings("NonAsciiCharacters")
public class StringCalculatorTest {

    @Test
    void 콤마_구분자() {
        String[] actual = StringCalculator.splitString("1,2");
        String[] expected = {"1", "2"};

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 콜론_구분자() {
        String[] actual = StringCalculator.splitString("1:2");
        String[] expected = {"1", "2"};

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 커스텀_구분자() {
        String[] actual = StringCalculator.splitString("//;\n1;2;3");
        String[] expected = {"1", "2", "3"};

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 문자열_배열을_숫자_배열로_변환() {
        List<Integer> actual = StringCalculator.convertToIntegerArray(new String[]{"1", "2", "3"});
        List<Integer> expected = Arrays.asList(1, 2, 3);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 숫자_배열_더하기() {
        int actual = StringCalculator.splitAndSum("1,2,3,4");
        int expected = 10;

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 숫자_하나만_입력한_경우_해당_숫자_반환() {
        int actual = StringCalculator.splitAndSum("15");
        int expected = 15;

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 커스텀_구분자에_여러_문자가_포함된_경우() {
        int actual = StringCalculator.splitAndSum("//@@..\n5@@..6@@..4");
        int expected = 15;

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 구분자만_입력한_경우_예외처리() {
        int actual = StringCalculator.splitAndSum(",");
        int expected = 0;

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 커스텀_구분자만_입력한_경우_예외처리() {
        int actual = StringCalculator.splitAndSum("//\n");
        int expected = 0;

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 빈_문자열_입력한_경우_예외처리() {
        int actual = StringCalculator.splitAndSum("");
        int expected = 0;

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void null_입력한_경우_예외처리() {
        int actual = StringCalculator.splitAndSum(null);
        int expected = 0;

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 커스텀_구분자에_숫자가_포함된_경우_예외처리() {
        assertThatThrownBy(() -> {
            StringCalculator.splitAndSum("//..2.\n1..2.4");
        }).isInstanceOf(RuntimeException.class);
    }

    @Test
    void 숫자_이외의_값을_입력한_경우_예외처리() {
        assertThatThrownBy(() -> StringCalculator.convertToIntegerArray(new String[]{"a", "b"}))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void 음수를을_입력한_경우_예외처리() {
        assertThatThrownBy(() -> StringCalculator.convertToIntegerArray(new String[]{"-1", "-2"}))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void 소수값을_입력한_경우_예외처리() {
        assertThatThrownBy(() -> StringCalculator.convertToIntegerArray(new String[]{"1.1", "2.3"}))
                .isInstanceOf(RuntimeException.class);
    }
}
