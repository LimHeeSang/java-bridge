package bridge.service;

import bridge.Bridge;
import bridge.BridgeMaker;
import bridge.BridgeMark;
import bridge.BridgeNumberGenerator;
import bridge.GameStatus;
import bridge.Player;
import bridge.dto.GameResultDto;

/**
 * 다리 건너기 게임을 관리하는 클래스
 *
 * 제공된 BridgeGame 클래스를 활용해 구현해야 한다.
 * BridgeGame에 필드(인스턴스 변수)를 추가할 수 있다.
 * BridgeGame의 패키지는 변경할 수 있다.
 * BridgeGame의 메서드의 이름은 변경할 수 없고, 인자와 반환 타입은 필요에 따라 추가하거나 변경할 수 있다.
 * 게임 진행을 위해 필요한 메서드를 추가 하거나 변경할 수 있다.
 */
public class BridgeGame {

    private Bridge bridge;
    private Player player;

    public BridgeGame() {
        this.player = new Player();
    }

    public void createBridge(int size, BridgeNumberGenerator bridgeNumberGenerator) {
        BridgeMaker bridgeMaker = new BridgeMaker(bridgeNumberGenerator);
        bridge = new Bridge(bridgeMaker.makeBridge(size));
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public GameStatus move(int round, String moveMark) {
        BridgeMark mark = BridgeMark.of(moveMark);
        player.record(mark);
        return bridge.cross(round, mark);
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry() {
        player.increaseAttempt();
    }

    public GameResultDto getGameReport() {
        return player.toResponseDto();
    }
}
