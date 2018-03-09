package com.example.nestorfernandez.flipaswitch;

/**
 * Created by nestor.fernandez on 01/03/2018.
 */

public abstract class constant {
    private static int mobile_height;
    private static int mobile_width;
    private static int ground;
    private static int cieling;
    private static int points;

    public static int getMobile_height() {
        return mobile_height;
    }

    public static void setMobile_height(int height) {
        constant.mobile_height = height;
    }

    public static int getMobile_width() {
        return mobile_width;
    }

    public static void setMobile_width(int width) {
        constant.mobile_width = width;
    }

    public static int getGround() {
        return ground;
    }

    public static void setGround(int ground) {
        constant.ground = ground;
    }

    public static int getCieling() {
        return cieling;
    }

    public static void setCieling(int cieling) {
        constant.cieling = cieling;
    }

    public static int getPoints() {
        return points/100;
    }

    public static void setPoints(int points) {
        constant.points = points;
    }

    public static void incrementPoints(){
        points++;
    }
}
