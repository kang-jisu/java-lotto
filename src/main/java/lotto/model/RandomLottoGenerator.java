package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class RandomLottoGenerator implements LottoGenerator {

    private static final int LOTTO_SIZE = 6;
    private static final int LOTTO_NUM_LOWER_BOUND = 1;
    private static final int LOTTO_NUM_UPPER_BOUND = 45;

    public RandomLottoGenerator() {
    }

    @Override
    public Lotto get() {
        return new Lotto(getLottoNumbers());
    }

    private List<LottoNumber> getLottoNumbers() {
        List<LottoNumber> numbers = initNumbers();

        List<LottoNumber> lotto = new ArrayList<>(LOTTO_SIZE);
        for (int i = 0; i < LOTTO_SIZE; i++) {
            Collections.shuffle(numbers);
            lotto.add(numbers.remove(i));
        }
        return lotto;
    }

    private List<LottoNumber> initNumbers() {
        List<LottoNumber> numbers = new LinkedList<>();
        for (int i = LOTTO_NUM_LOWER_BOUND; i <= LOTTO_NUM_UPPER_BOUND; i++) {
            numbers.add(new LottoNumber(i));
        }
        return numbers;
    }
}
