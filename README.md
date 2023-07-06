# Minecraft Astatine Project - R01

> ## combat
* EntityDamageTickingHandler
  * _Default Attack Speed 20.0_
  * 양손무기를 들고 피격 시 공격속도 설정 1.0
  * 한손무기를 들고 피격 시 공격속도 설정 10.0
  
* Explosive
  * _Default Explosion Radius 4_
  * MineCart에 폭발범위를 수정합니다.
  * Radius > 100
  * Fire

* HandSwing
  * 한손으로만 공격하는 모션을 다채롭게 바꿉니다.
  * Main Hand Swing 시 Off Hand Swing

* UserHealthScale
  * 킬에 의한 영구체력을 설정합니다.
  * 약탈자의 체력이 60칸(체력 3줄) 미만일 경우
  * 피해자의 체력이 4칸 초과일 경우

> ## command
* ArmourSet
  * 손에 든 아이템을 특정부위에 착용합니다.
  * /머리
  * _/몸통_
  * _/바지_
  * _/신발_

* HealthSet
  * 임의로 특정유저의 체력을 조작합니다.
  * _/체력초기화 [닉네임] [체력]_

* MotdSet
  * 서버 Motd를 저장합니다.
  * _/motd [설정할 이름]_

* SaveUserData
  * 모든 유저정보를 저장합니다.
  * _/saveuserdata_
  
* TotemStacking
  * 플레이어의 토템을 합칩니다.
    * 최소 합쳐지지 않은 토템 2개 이상을 소유해야 합니다.
    * 1개 이상의 토템을 합칠 수 있습니다.
    * _1 + 1 = 2_ or _20 + 20 = 40_ 이 가능합니다.
    * 단, 합칠토템이 없을 시 작동하지 않습니다.

* Value Object Checker
  * 특정 플레이어의 user value 를 출력합니다.
  * _/vo [Nick Name]_

> ## userValue
* JoinAndQuit
  * 플레이어 접속 시 해당 플레이어 데이터를 적용합니다.
  * 공격속도 , 최대체력 , 한글이름 등을 저장합니다.
  * 해당 데이터는 서버종료전까지 생명주기를 가지며 업데이트 됩니다.

* User
  * 유저의 Value Object 로 유저상세 정보를 저장 및 수정 합니다.

* UserHandler
  * UUID 로 해당 유저의 상세 정보를 로드하여 서버에 적용합니다.
  * 서버종료 시 HashMap 생명주기가 끝나며, 저장됩니다.

* UserIOHandler
  * 유저의 Input Output을 핸들링 합니다.

> ## WorldSet
* Raid Announcer class
  * 레이드시 해당 좌표와 월드를 고지합니다.

> ## Component Exchanger
* 문자열을 컴포넌트로 변환하여 반환합니다.
  * 색을 지정할 수 있습니다.
    * 빨강
    * 노랑
    * 주황
    * 하얀