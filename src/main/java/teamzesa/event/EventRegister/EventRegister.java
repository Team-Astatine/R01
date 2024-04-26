package teamzesa.event.EventRegister;

public interface EventRegister {

    /*
    각 이벤트 등록시 Constructor 에서 field variable 을 init 하기 위함
    */
    void init();

    /*
    이벤트 실행
    */
    void execute();

}
