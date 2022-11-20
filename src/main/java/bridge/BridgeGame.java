package bridge;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {
    private final Bridge bridge;
    private int tryCount = 1;

    public BridgeGame(Bridge bridge) {
        this.bridge = bridge;
    }

    public int getTryCount() {
        return tryCount;
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public boolean move(String moving) {
        bridge.getPlayer().setLastMoving(moving);
        if (bridge.possibleMove(moving)) {
            bridge.getPlayer().locationUpdate();
            bridge.setCorrect(true);
            return true;
        }
        bridge.setCorrect(false);
        return false;
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public boolean retry(String input) {
        if (input.equals("R")) {
            tryCount++;
            bridge.setPlayer(new Player());
            return true;
        }
        return false;
    }
}
