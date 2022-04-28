package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class RandomLottoGenerator implements LottoGenerator {

    public static final int LOTTO_SIZE = 6;

    private final List<Integer> numbers;

    RandomLottoGenerator() {
        numbers = new LinkedList<>();
        for (int i = 1; i <= 45; i++) {
            numbers.add(i);
        }
    }

    @Override
    public Lottos get() {
        return new Lottos(getLottoNumbers());
    }

    private List<Integer> getLottoNumbers() {
        List<Integer> lottos = new ArrayList<>(LOTTO_SIZE);
        for (int i = 0; i < LOTTO_SIZE; i++) {
            Collections.shuffle(numbers);
            lottos.add(numbers.remove(i));
        }
        return lottos;
    }
}
