package com.zhd.study.excel;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-04-21 19:33
 */
public class VersionSort {
    public static void main(String[] args) {

        String[] arrVer = {"1.45", "1.5", "3.3.3.3.3", "6"};
        Arrays.sort(arrVer, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (StringUtils.isBlank(o1) && StringUtils.isNotBlank(o2)) {
                    return -1;
                }
                if (StringUtils.isNotBlank(o1) && StringUtils.isBlank(o2)) {
                    return 1;
                }
                String[] split1 = o1.split("\\.");
                String[] split2 = o2.split("\\.");

                int max = split1.length > split2.length ? split1.length : split2.length;

                for (int i = 0; i < max; i++) {
                    if (i >= split1.length) {
                        return -1;
                    } else if (i >= split2.length) {
                        return 1;
                    } else {
                        return Integer.valueOf(split1[i]) - Integer.valueOf(split2[i]);
                    }
                }
                return 0;
            }
        });

        System.out.println(Arrays.toString(arrVer));

    }
}
