package com.jiker.keju;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;




@RunWith(Parameterized.class)
public class TourInfoTest {
    private final int waitTime;
    private final int distance;
    private final long calFee;

    public TourInfoTest(int distance, int waittime, long calFee) {
        this.distance =distance;
        this.waitTime = waittime;
        this.calFee = calFee;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {1,0,6L},{3,0,7L},{10,0,13L},{2,3,7L}
        });
    }


    /**
     * 1公里,等待0分钟
     * 3公里,等待0分钟
     * 10公里,等待0分钟
     * 2公里,等待3分钟
     * 收费6元\n
     * 收费7元\n
     * 收费13元\n
     * 收费7元\n
     */
    @Test
    public  void should_return_distance_fee() {
        TourInfo tourInfo = new TourInfo();
        tourInfo.setDistance(distance);
        tourInfo.setWaitTime(waitTime);
        long fee = tourInfo.calTourFee();
        assertThat(fee,is(calFee));
    }
}
