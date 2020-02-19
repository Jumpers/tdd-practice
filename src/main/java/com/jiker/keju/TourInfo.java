package com.jiker.keju;

public class  TourInfo {

    private int distance;

    private int waitTime;

    public int getDistance() {
        return distance;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    /**
     * 进行计费操作
     * 不大于2公里时只收起步价6元。
     * 超过2公里的部分每公里收取0.8元。
     * 超过8公里的部分，每公里加收50%长途费。¡
     * 停车等待时加收每分钟0.25元。
     * 最后计价的时候司机会四舍五入只收到元。
     * @return 计程费用
     */
    public long calTourFee() {
        FeeCalculator feeCalculator = new FeeCalculator();
        double calFee =0;
        if (distance>8) {
            calFee = feeCalculator.normalCal() + feeCalculator.midDistanceCal() + feeCalculator.longDistanceCal() + feeCalculator.waitFee();
        }
        if (distance <= 8 && distance >2) {
            calFee = feeCalculator.normalCal() + feeCalculator.midDistanceCal() + feeCalculator.waitFee();
        }
        if (distance<=2) {
            calFee= feeCalculator.normalCal()+feeCalculator.waitFee();
        }
        return Math.round(calFee);
}



    class FeeCalculator{

        private int normalCal() {
            return 6;
        }

        private double midDistanceCal() {
            return (distance-2)*0.8;
        }

        private double longDistanceCal() {
            return (distance - 8) * (0.8 * 0.5);
        }

        private double waitFee() {
            return waitTime*0.25;
        }
    }
}
