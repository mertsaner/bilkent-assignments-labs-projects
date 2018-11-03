//Author Yasin Balcancý
public class City {
	private double latitudeInDegs;
	private double latitudeInMins;
	private double latitudeInSecs;
	private double longitudeInDegs;
	private double longitudeInMins;
	private double longitudeInSecs;
	private String cityName;
    
    public City(String cityName, double latitudeInDegs, double latitudeInMins, double latitudeInSecs, double longitudeInDegs, double longitudeInMins, double longitudeInSecs) {
    	this.latitudeInDegs = latitudeInDegs;
    	this.latitudeInMins = latitudeInMins;
    	this.latitudeInSecs = latitudeInSecs;
    	this.longitudeInDegs = longitudeInDegs;
    	this.longitudeInMins = longitudeInMins;
    	this.longitudeInSecs = longitudeInSecs;
    	this.cityName = cityName;
    }
    public double convertToDegrees(double deg, double min, double sec) {
    	return deg + min/60 + sec/3600;
    } // converts given types to degrees
    public double getLatitude() {
    	return convertToDegrees(latitudeInDegs, latitudeInMins, latitudeInSecs);
    } //latitude
    public double getLongitude() {
    	return convertToDegrees (longitudeInDegs, longitudeInMins, longitudeInSecs);
    } //longitute
    public String getCityName() {
    	return cityName;
    } //city name
}