package teamzesa.event;

import org.apache.commons.lang3.BooleanUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class UtilCheck {
    boolean isGodMode;

    @Before
    public void set() {
        isGodMode = false;
    }

    @Test
    @DisplayName("Entry")
    public void print(){
        System.out.println(BooleanUtils.isFalse(isGodMode));
    }
}
