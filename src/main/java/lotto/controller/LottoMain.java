package lotto.controller;

import lotto.dto.LottoCount;
import lotto.dto.LottoResult;
import lotto.model.Lotto;
import lotto.model.LottoNumber;
import lotto.model.Lottos;
import lotto.model.Money;
import lotto.model.RandomLottoGenerator;
import lotto.model.WinnerLotto;
import lotto.view.InputView;
import lotto.view.ResultView;

import java.util.List;
import java.util.stream.Collectors;

public class LottoMain {
    public static void main(String[] args) {
        LottoCount lottoCount = payLottoMoney();

        Lottos lottos = buyAndPrintLottos(lottoCount);
        LottoResult lottoResult = lottos.extractLottoResult(makeWinnerLottos());

        ResultView.printResult(lottoCount.multiplyUnit(), lottoResult);
    }

    private static LottoCount payLottoMoney() {
        Money buyingMoney = new Money(InputView.askBuyingMoney());
        int manualLottoCount = InputView.askManualLottoCount();
        return new LottoCount(buyingMoney, manualLottoCount);
    }

    private static Lottos buyAndPrintLottos(LottoCount lottoCount) {
        Lottos manual = makeManualLottos(InputView.askManualLottoNumbers(lottoCount.getManualCount()));
        Lottos random = new Lottos(lottoCount.getRandomCount(), new RandomLottoGenerator());

        ResultView.printBuyingLottos(manual, random);

        return manual.addAll(random);
    }

    private static Lottos makeManualLottos(List<String> manualLottoNumbers) {
        return manualLottoNumbers.stream()
                .map(Lotto::new)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Lottos::new));
    }

    private static WinnerLotto makeWinnerLottos() {
        Lotto winnerLotto = new Lotto(InputView.askWinnerLottoNumbers());
        LottoNumber bonusNumber = LottoNumber.valueOf(InputView.askBonusLottoNumber());

        return new WinnerLotto(winnerLotto, bonusNumber);
    }
}
