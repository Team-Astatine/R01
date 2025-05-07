package teamzesa.Data.User.UserData;

import teamzesa.Data.User.Interface.RObject;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

/**
 * 모든 User 객체는 {@link RObject} 객체를 상속 받습니다.
 * User의 Default Status를 Setup 하기 위한 객체입니다.
 *
 * @Param uuid               유저의 UUID를 기록합니다.
 * @Param nameList           유저가 이름을 변경했을시, 해당 내역을 기록합니다.
 * @Param connectionIPList   유저가 여러 IP로 접속 시 IP 들을 기록합니다.
 * @Param joinCount          유저가 서버에 join한 횟수를 Count합니다.
 * @Param playTime           유저의 플레이 시간을 기록합니다.
 * @Param level              유저의 level을 기록합니다.
 * @Param godMode            유저가 무적인지 Status를 확인합니다.
 * @Param announcingSkip     서버 공지사항을 on/off 합니다.
 */
public record User(
        UUID uuid,
        List<String> nameList,
        HashSet<String> connectionIPList,
        int joinCount,
        int playTime,
        int level,
        boolean godMode,
        boolean announcingSkip
) implements RObject {
}
