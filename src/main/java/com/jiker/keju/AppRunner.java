package com.jiker.keju;

import java.io.IOException;
import java.util.List;

public class AppRunner {

    public static void main(String[] args) {
        /*TODO
          1. args[0]为resources下的测试数据文件名，例如传入的args[0]值为"testData.txt"。
          2. 你写的程序将把testDataFile作为参数加载此文件并读取文件内的测试数据，并对每条测试数据计算结果。
          3. 将所有计费结果拼接并使用\n分割，然后保存到receipt变量中。
         */
        String testDataFile = args[0];
        ParseFile parseFile = null;
        List<TourInfo> tourInfos = null;
        try {
            parseFile = new ParseFile(testDataFile);
            tourInfos = parseFile.getTourInfo();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String receipt = "";
        StringBuffer feeContent = new StringBuffer();
        for (TourInfo tourInfo : tourInfos) {
            feeContent.append("收费").append(tourInfo.calTourFee()).append("元\n");
        }
        receipt = feeContent.toString();
        System.out.println(receipt);
    }
}
