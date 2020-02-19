package com.jiker.keju;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ParseFileTest {


    private ParseFile parseFile;
    List<TourInfo>  tourInfos;

    @Before
    public void preparedFileStrem() throws Exception {
        parseFile = new ParseFile("testData.txt");
        tourInfos = parseFile.getTourInfo();
    }


    @Test
    public void should_return_count_of_tour() {

        int size = tourInfos.size() ;
        assertThat(size, is(4));
    }

    @Test
    public void should_return_tourInfo() {
        TourInfo tourInfo = tourInfos.get(0);
        int distance = tourInfo.getDistance();
        assertThat(distance, is(1));
    }

    @Test
    public void stringRegexTest() {
        TourInfo tourInfo = new TourInfo();
        String oriInfo = "1公里,等待0分钟";
        String distanceRegex = "\\d+";
        String waitTimeRegex = "分钟";
        //get Distance
        Pattern pattern = Pattern.compile(distanceRegex);
        Matcher matcher = pattern.matcher(oriInfo);
        if (matcher.matches()) {
            String distance = matcher.group();
            tourInfo.setDistance(Integer.parseInt(distance));
        }

        pattern = Pattern.compile(waitTimeRegex);
        matcher = pattern.matcher(oriInfo);
        if (matcher.matches()) {
            String waitTime = matcher.group();
            tourInfo.setWaitTime(Integer.parseInt(waitTime));
        }
    }

    @Test
    public void regexTest() {
        String strE = "1公里,等待0分钟";
//        String pattern = "(\\d+)[\\u4E00-\\u9FA5|,]+(\\d+)[\\u4E00-\\u9FA5]+";
        String pattern2 = "(\\d+)[\\u4E00-\\u9FA5|,]+(\\d+)";
        Pattern compile = Pattern.compile(pattern2);
        Matcher matcher = compile.matcher(strE);
        matcher.find();
        assertThat(matcher.group(1), is("1"));
        assertThat(matcher.group(2), is("0"));
    }

}
