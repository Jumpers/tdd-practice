package com.jiker.keju;

import java.io.IOException;
import java.util.List;

public class AppRunner {

    /*TODO
          1. args[0]为resources下的测试数据文件名，例如传入的args[0]值为"testData.txt"。
          2. 你写的程序将把testDataFile作为参数加载此文件并读取文件内的测试数据，并对每条测试数据计算结果。
          3. 将所有计费结果拼接并使用\n分割，然后保存到receipt变量中。
         */
    public static void main(String[] args) {
        String receipt = null;
        try {
            String testDataFile = args[0];
            receipt = getContent(testDataFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(receipt);
    }

    private static String getContent(String testDataFile) throws Exception {
        StringBuilder feeContent = new StringBuilder();
        for (TourInfo tourInfo : new ParseFile(testDataFile).getTourInfo()) {
            feeContent.append("收费").append(tourInfo.calTourFee()).append("元\n");
        }
        return feeContent.toString();
    }
}
