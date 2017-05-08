package com.stem.sunny.physicforstem;

/**
 * @author Sunnara
 * @version 1.0
 * Description -
 */

public class ConvertWeight {

    private double grams, pounds, kilograms, ounces;

    public double getOunces() {
        return ounces;
    }

    public void setOunces(double ounces) {
        this.ounces = ounces;
    }

    public double getGrams() {
        return grams;
    }

    public void setGrams(double grams) {
        this.grams = grams;
    }

    public double getPounds() {
        return pounds;
    }

    public void setPounds(double pounds) {
        this.pounds = pounds;
    }

    public double getKilograms() {
        return kilograms;
    }

    public void setKilograms(double kilograms) {
        this.kilograms = kilograms;
    }



    public double gramsTopounds(double grams) {
        return grams / 453.59237;
    }

    public double gramsTokilograms(double grams) {
        return grams / 1000;
    }

    public double gramsToounces(double grams) {
        return grams / 28.34952;
    }

    public double poundsTograms(double pounds) {
        return pounds * 453.59237;
    }

    public double poundsTokilograms(double pounds) {
        return pounds / 2.20462;
    }

    public double poundsToounces(double pounds) {
        return pounds * 16;
    }

    public double kilogramsTograms(double kilograms) {
        return kilograms * 1000;
    }

    public double kilogramsTopounds(double kilograms) {
        return kilograms * 2.20462;
    }

    public double kilogramsToounces(double kilograms) {
        return kilograms * 35.27396;
    }

    public double ouncesTograms(double ounces) {
        return ounces * 28.34952;
    }

    public double ouncesTopounds(double ounces) {
        return ounces / 16;
    }

    public double ouncesTokilograms(double ounces) {
        return ounces / 35.27396;
    }
}
