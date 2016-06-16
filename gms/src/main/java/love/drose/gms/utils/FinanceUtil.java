package love.drose.gms.utils;

/**
 * Created by lovedrose on 6/15/16.
 */
public class FinanceUtil {

    private FinanceUtil() {}

    private static double curDayTurnover;

    private static void setZore() {
        curDayTurnover = 0;
    }

    public static double getCurDayTurnover() {
        return curDayTurnover;
    }

    public static void setCurDayTurnover(double curDayTurnover) {
        FinanceUtil.curDayTurnover = curDayTurnover;
    }
}
