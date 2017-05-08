package com.stem.sunny.physicforstem;

/**
 * @author Sunnara
 * @version 1.0
 * Description - This class contains each conversion possible using the dropdown bar of the
 *               converting distance
 */
public class ConvertLength {

    private double meters, inches, feet, miles, centimeters, kilometers;

    public double getMeters() {
        return meters;
    }

    public void setMeters(double meters) {
        this.meters = meters;
    }

    public double getInches() {
        return inches;
    }

    public void setInches(double inches) {
        this.inches = inches;
    }

    public double getFeet() {
        return feet;
    }

    public void setFeet(double feet) {
        this.feet = feet;
    }

    public double getMiles() {
        return miles;
    }

    public void setMiles(double miles) {
        this.miles = miles;
    }

    public double getCentimeters() {
        return centimeters;
    }

    public void setCentimeters(double centimeters) {
        this.centimeters = centimeters;
    }

    public double getKilometers() {
        return kilometers;
    }

    public void setKilometers(double kilometers) {
        this.kilometers = kilometers;
    }

    public double metersToinches(double meters) {
        return meters * 39.3701;
    }

    public double metersTofeet(double meters) {
        return meters * 3.2808;
    }

    public double metersTomiles(double meters) {
        return meters / 1609.344;
    }

    public double metersTokilometers(double meters) {
        return meters / 1000;
    }

    public double metersTocentimeters(double meters) {
        return meters * 100;
    }

    public double inchesTometers(double inches) {
        return inches / 39.3701;
    }

    public double inchesTofeet(double inches) {
        return inches / 12;
    }

    public double inchesTomiles(double inches) {
        return inches / 63360;
    }

    public double inchesTokilometers(double inches)  {
        return inches / 39370.1;
    }

    public double inchesTocentimeters(double inches) {
        return inches * 2.54;
    }

    public double feetToinches(double feet) {
        return feet * 12;
    }

    public double feetTomiles(double feet) {
        return feet / 5280;
    }

    public double feetTometers(double feet) {
        return feet / 3.28084;
    }

    public double feetTokilometers(double feet) {
        return feet / 3280.84;
    }

    public double feetTocentimeters(double feet) {
        return feet * 30.48;
    }

    public double milesToinches(double miles) {
        return miles *  63360;
    }

    public double milesTofeet(double miles) {
        return miles *  5280;
    }

    public double milesTometers(double miles) {
        return miles * 1609.344;
    }

    public double milesTokilometers(double miles) {
        return miles * 1.60934;
    }

    public double milesTocentimeters(double miles) {
        return miles * 160934.4;
    }

    public double kilometersToinches(double kilometers) {
        return kilometers * 39370.1;
    }

    public double kilometersTofeet(double kilometers) {
        return kilometers * 3280.84;
    }

    public double kilometersTomiles(double kilometers) {
        return kilometers / 1.60934;
    }

    public double kilometersTometers(double kilometers) {
        return kilometers * 1000;
    }

    public double kilometersTocentimeters(double kilometers) {
        return  kilometers * 100000;
    }

    public double centimetersToinches(double centimeters) {
        return centimeters / 2.54;
    }

    public double centimetersTofeet(double centimeters) {
        return centimeters / 30.48;
    }

    public double centimetersTomiles(double centimeters) {
        return centimeters / 160934.4;
    }

    public double centimetersTometers(double centimeters) {
        return centimeters / 100;
    }

    public double centimetersTokilometers(double centimeters) {
        return centimeters / 100000;
    }
}
