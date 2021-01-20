package ir.bigz.ms.searchfilter.common.utility;

import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.atomic.AtomicInteger;

public class Utils {

    public static boolean isValidNationalCode(String nationalcode) {
        boolean isValid = false;
        if (nationalcode != null && nationalcode.length() == 10) {
            if (StringUtils.isNumeric(nationalcode)) {
                AtomicInteger index = new AtomicInteger();
                int controllerConditionNumber = nationalcode.substring(0, 9).chars().map(i -> Integer.valueOf(Character.toString((char) i)) * (10 - index.getAndIncrement())).sum() % 11;
                int controllerNumber = Integer.valueOf(nationalcode.substring(9));
                isValid = (controllerConditionNumber < 2) ? controllerConditionNumber == controllerNumber : controllerNumber == (11 - controllerConditionNumber);
            }
        }
        return isValid;
    }
}
