package com.mm.movie.util;

import java.util.List;

/**
 * Utility class for string related operations.
 */
public class StringUtils {
    /**
     * Method to created comma separated string of given string list.<br/>
     * This class also has sample unit test case.
     *
     * @param list the list of strings
     * @return comma separated string
     */
    public static String join(List<String> list) {
        StringBuilder sb = new StringBuilder();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                sb.append(list.get(i));
                if (i < list.size() - 1) {
                    sb.append(", ");
                }
            }
        }
        return sb.toString();
    }
}
