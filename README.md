# Minecraft Astatine Project - R01

> ## Announcer
* Raid Announcer class
  * 레이드시 해당 좌표와 월드를 고지합니다.

> ## combat
* EntityDamageTickingHandler
  * 엔티티의 무적설정을 임의로 수정합니다.
  * 기존 20 > 1 로 수정하여 연속 타격을 가능하게 설정합니다.
  
* ExplosiveHandler
  * 기존 엔티티 폭팔범위를 수정합니다.
  * TnT Cart , TnT , Creeper 등
  
* PlayerHealthScaleHandler
  * 킬에 의한 영구체력을 설정합니다.

> ## command
* ArmourSet
  * 손에 든 아이템을 특정부위에 착용합니다.
  * /머리
  * /몸통
  * /바지
  * /신발

* HealthSet
  * 임의로 특정유저의 체력을 조작합니다.
  * /체력초기화 [닉네임] [체력]

* MotdSet
  * 서버 Motd를 저장합니다.
  * /motd [설정할 이름]

* SaveUserData
  * 모든 유저정보를 저장합니다.
  * /saveuserdata

* NameChanger
  * 플레이어 이름을 변환하며 콘솔에서만 작성 가능합니다.
  * 관리자는 빨강으로 표기 됩니다.
  * 탭 리스트 , 채팅의 플레이어 이름을 변화합니다. ❌
  * /namechange [닉네임] [바꿀이름] user or moder_
  
* TotemStacking
  * 플레이어의 토템을 합칩니다.
    * 최소 합쳐지지 않은 토템 2개 이상을 소유해야 합니다.
    * 1개 이상의 토템을 합칠 수 있습니다.
    * _1 + 1 = 2_ or _20 + 20 = 40_ 이 가능합니다.
    * 단, 합칠토템이 없을 시 작동하지 않습니다.

> ## user
* IOHandler
  * 유저의 Input Output을 핸들링 합니다. 

* JoinAndQuit
  * 플레이어 접속 시 해당 플레이어 데이터를 적용합니다.
  * 공격속도 , 최대체력 , 한글이름 등을 저장합니다.
  * 해당 데이터는 서버종료전까지 생명주기를 가지며 업데이트 됩니다.

* User
  * 유저의 Value Object 로 유저상세 정보를 저장 및 수정 합니다.

* UserHandler
  * UUID 로 해당 유저의 상세 정보를 로드하여 서버에 적용합니다.
  * 서버종료 시 HashMap 생명주기가 끝나며, 저장됩니다.



