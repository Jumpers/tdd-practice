package com.jiker.keju;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseFile {
    private final String filePath;
    private ArrayList<String> linesInfo = new ArrayList<String>();

    public ParseFile(String filePath) throws IOException {
        this.filePath = filePath;
        getLines();
    }

    public void getLines() throws IOException {
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    this.getClass().getClassLoader().getResourceAsStream(filePath)));
            String tempLine;
            while ((tempLine = bf.readLine()) != null) {
                linesInfo.add(tempLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.getClass().getClassLoader().getResourceAsStream(filePath).close();
        }
    }

    public List<TourInfo> getTourInfo() throws Exception {
        List<TourInfo> tourInfos = new ArrayList<>();
        for (String oriInfo : linesInfo) {
            TourInfo tourInfo = parseTxtToObject(oriInfo);
            tourInfos.add(tourInfo);
        }
        return tourInfos;
    }

    private TourInfo parseTxtToObject(String oriInfo) throws Exception {
        TourInfo tourInfo = new TourInfo();
        String RegexExp = "([0-9]+).+([0-9]+)";//
        Pattern pattern = Pattern.compile(RegexExp);
        Matcher matcher = pattern.matcher(oriInfo);
        if (matcher.find()) {
            tourInfo.setDistance(Integer.parseInt(matcher.group(1)));
            tourInfo.setWaitTime(Integer.parseInt(matcher.group(2)));
        } else {
            throw new Exception("无法匹配到行程信息");
        }
        return tourInfo;
    }
}
