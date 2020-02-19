package com.jiker.keju;



import java.io.*;
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

    public List getLines() throws IOException {
        InputStream rsInputStream = this.getClass().getClassLoader().getResourceAsStream(filePath);
        try {
//            File file = new File(filePath);
            BufferedReader bf = new BufferedReader(new InputStreamReader(rsInputStream));
            String tempLine;
            while ((tempLine = bf.readLine()) != null) {
                linesInfo.add(tempLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            rsInputStream.close();
        }
        return linesInfo;
    }

    public List<TourInfo> getTourInfo() throws Exception {
        List<TourInfo> tourInfos =new ArrayList<TourInfo>();
        for (String oriInfo : linesInfo) {
            TourInfo tourInfo =parseTxtToObject(oriInfo);
            tourInfos.add(tourInfo);
        }
        return tourInfos;
    }

    private TourInfo parseTxtToObject(String oriInfo) throws Exception {
        TourInfo tourInfo = new TourInfo();
        String RegexExp = "(\\d+)公里,等待(\\d+)分钟";//
        //get Distance
        Pattern pattern = Pattern.compile(RegexExp);
        Matcher matcher = pattern.matcher(oriInfo);
        if (matcher.find()) {
            String distance = matcher.group(1);
            String waitTime = matcher.group(2);
            tourInfo.setDistance(Integer.parseInt(distance));
            tourInfo.setWaitTime(Integer.parseInt(waitTime));
        } else {
            throw new Exception("无法匹配到行程信息");
        }

        return tourInfo;

    }
}
