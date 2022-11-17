package bridge;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BridgeTest {

    @DisplayName("U, D가 리스트로 주어질 때 enum 리스트로 반환하는 기능")
    @MethodSource("createTestBridgeStatus")
    @ParameterizedTest
    void mapToBridgeMarks(int index, BridgeMark answer) {
        List<BridgeMark> bridgeMarks = BridgeMark.of(List.of("U", "D", "U", "D"));
        assertThat(bridgeMarks.get(index)).isEqualTo(answer);
    }

    private static List<Arguments> createTestBridgeStatus() {
        return List.of(
                Arguments.of(0, BridgeMark.UP),
                Arguments.of(1, BridgeMark.DOWN),
                Arguments.of(2, BridgeMark.UP),
                Arguments.of(3, BridgeMark.DOWN)
        );
    }

    @DisplayName("다리를 한 라운드 건널 때 게임 계속 가능 결과 반환")
    @Test
    void crossContinueResult() {
        Bridge bridge = new Bridge(List.of("U", "D", "U", "D"));
        GameStatus gameStatus = bridge.cross(1, BridgeMark.UP);
        assertThat(gameStatus.isContinue()).isTrue();
    }

    @DisplayName("다리를 한 라운드 건널 때 게임 실패 결과 반환")
    @Test
    void crossFailResult() {
        Bridge bridge = new Bridge(List.of("U", "D", "U", "D"));
        GameStatus gameStatus = bridge.cross(3, BridgeMark.DOWN);
        assertThat(gameStatus.isFail()).isTrue();
    }

    @DisplayName("다리를 한 라운드이자 마지막 라운드를 건널 때 게임 성공 결과 반환")
    @Test
    void crossSuccessResultInLast() {
        Bridge bridge = new Bridge(List.of("U", "D", "U", "D"));
        GameStatus gameStatus = bridge.cross(4, BridgeMark.DOWN);
        assertThat(gameStatus.isSUCCESS()).isTrue();
    }
}
