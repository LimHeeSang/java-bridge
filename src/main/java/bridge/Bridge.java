package bridge;

import bridge.enums.BridgeMark;
import bridge.enums.GameStatus;
import bridge.valid.BridgeValidUtil;

import java.util.List;

public class Bridge {

    private static final int CRITERION_ROUND_START = 1;

    private final List<BridgeMark> bridge;

    public Bridge(List<String> bridge) {
        BridgeValidUtil.validateSize(bridge.size());
        this.bridge = BridgeMark.of(bridge);
    }

    public GameStatus cross(int round, BridgeMark playerMark) {
        if (isSuccess(playerMark, round)) {
            return GameStatus.SUCCESS;
        }
        if (isContinue(playerMark, round)) {
            return GameStatus.CONTINUE;
        }
        return GameStatus.FAIL;
    }

    private boolean isSuccess(BridgeMark playerMark, int round) {
        return isContinue(playerMark, round) && isLastRound(round);
    }

    private boolean isContinue(BridgeMark playerMark, int round) {
        BridgeMark answerMark = get(round);
        return answerMark.equals(playerMark);
    }

    private boolean isLastRound(int round) {
        return size() == round;
    }

    private BridgeMark get(int round) {
        return bridge.get(round - CRITERION_ROUND_START);
    }

    private int size() {
        return bridge.size();
    }
}
