package lotto;

import lotto.model.Money;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Money 클래스 테스트")
public class MoneyTest {

    private static final Money THOUSAND = new Money(1000);

    @Test
    @DisplayName("같은 금액을 가진 Money 클래스는 equals 가 true 이다")
    void equalsTest() {
        assertThat(new Money(1000)).isEqualTo(THOUSAND);
    }

    @Test
    @DisplayName("금액이 음수이면 예외가 발생한다.")
    void minusMoneyTest() {
        assertThatThrownBy(() -> new Money(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("음수");
    }

    @Test
    @DisplayName("stream에서 reduce와 add로 값을 더할 수 있다.")
    void addTest() {
        List<Money> monies = Lists.newArrayList(new Money(0), new Money(1), new Money(2), new Money(3));

        Money result = monies.stream()
                .reduce(new Money(0), Money::add);

        assertThat(result).isEqualTo(new Money(6));
    }

    @Test
    @DisplayName("Money가 입력받은 단위의 배수인지 isDivided(money) 로 확인할 수 있다.")
    void notThousandMoneyTest() {
        Money money = new Money(14000);

        assertThat(money.isDivided(THOUSAND)).isTrue();
    }

    @Test
    @DisplayName("Money가 입력받은 단위 기준 몇개 있는지 getUnitCount(money) 로 알 수 있다. ")
    void getUnitCountTest() {
        Money money = new Money(14000);

        assertThat(money.calculateUnitCount(THOUSAND)).isEqualTo(14);
    }

    @Test
    @DisplayName("getYield로 수익률을 구할 수 있다.")
    void getYieldTest() {
        Money money = new Money(14000);

        assertThat(String.format("%.2f", new Money(5000).divideBy(money))).isEqualTo("0.36");
    }
}
