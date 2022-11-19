package bridge;

public class BridgeGamePlay {
    private static final String GAME_START_MESSAGE = "다리 건너기 게임을 시작합니다.\n";

    private final InputView inputView = new InputView();

    public void startGame() {
        System.out.println(GAME_START_MESSAGE);
        playGame(inputView.readBridgeSize());
    }
    private void playGame(int size) {
        BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
        Bridge bridge = new Bridge(bridgeMaker.makeBridge(size), size);
        BridgeGame bridgeGame = new BridgeGame(bridge);
        OutputView outputView = new OutputView(bridge, bridgeGame);
        playLoop(bridge, bridgeGame, outputView);
        outputView.printResult();
    }

    private void playLoop(Bridge bridge, BridgeGame bridgeGame, OutputView outputView) {
        while (!bridge.getGameResult()) {
            if (!moveAndPrint(bridgeGame, outputView)) {
                break ;
            }
        }
        if (!bridge.getGameResult() && bridgeGame.retry(inputView.readGameCommand())) {
            playLoop(bridge, bridgeGame, outputView);
        }
    }

    private boolean moveAndPrint(BridgeGame bridgeGame, OutputView outputView) {
        boolean ret = bridgeGame.move(inputView.readMoving());
        outputView.printMap();
        return ret;
    }
}