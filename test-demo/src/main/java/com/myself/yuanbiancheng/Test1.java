package com.myself.yuanbiancheng;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Test1 {


    private List<String> allFileNameList;

    public Test1() {
        allFileNameList = new ArrayList<>();

    }


    /**
     * test.txt
     * <p>
     * test.txt
     * test(1/2).txt
     */


    public String buildFileName(String fileName) {

        List<String> allFileNames = getAllFileName();

        if (allFileNames.contains(fileName)) {
            String newName = getNewName(fileName, allFileNames);
            return newName;
        }

        return fileName;

    }

    public String createFile(String fileName) {
        String newName = buildFileName(fileName);

        this.allFileNameList.add(newName);

        return newName;

    }

    private List<String> getAllFileName() {
        return allFileNameList;
    }

    /**
     * test.txt
     * <p>
     * test34t45.txt
     * test(1).txt
     * test(2).txt
     * <p>
     * out:
     * test (3) . txt
     */
    private static String getNewName(String name, List<String> allFileNames) {

        int split = -1;
        for (int i = name.length() - 1; i >= 0; i--) {
            if (name.charAt(i) == '.') {
                split = i;
                break;
            }
        }

        String subFix = name.substring(split);
        String preFix = name.substring(0, split);

        StringBuilder stringBuilder = new StringBuilder();
        int maxIdx = -1;

        for (String fileName : allFileNames) {

            if (fileName.startsWith(preFix) && fileName.endsWith(subFix)) {

                int firstDigitIdx = 0, lastDigitIdx = fileName.length() - subFix.length();
                for (int i = lastDigitIdx; i >= 0; i--) {
                    if (Character.isDigit(fileName.charAt(i))) {
                        lastDigitIdx = i;
                        break;
                    }
                }

                for (int i = lastDigitIdx; i >= 0; i--) {
                    if (!Character.isDigit(fileName.charAt(i))) {
                        firstDigitIdx = i + 1;
                        break;
                    }
                }

                maxIdx = Integer.valueOf(fileName.substring(firstDigitIdx, lastDigitIdx));
                maxIdx += 1;//?

                stringBuilder.append(fileName.substring(0, firstDigitIdx))
                        .append(maxIdx)
                        .append(fileName.substring(lastDigitIdx));
            }
        }


        return stringBuilder.toString();
    }

}
