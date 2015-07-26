package rogersilva.bora.utils;

/**
 * Created by RogerSilva on 7/26/2015.
 */
public class StringUtils {

    /**
     * Check whether string is null or empty
     * @param string
     * @return true whether string is null or empty. Otherwise false
     */
    public static boolean isNullOrEmpty(String string) {

        return (string == null || string.isEmpty());
    }
}
