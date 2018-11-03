//Author Yasin Balcancý
import java.util.Scanner;

public class Country {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String cityName;
        double latitudeInDegs;
        double latitudeInMins;
        double latitudeInSecs;
        double longitudeInDegs;
        double longitudeInMins;
        double longitudeInSecs;
        City[] ca = new City[100];
        int i = 0;
        do {
        	System.out.print("Please enter city name : (Q to exit..): ");
        	cityName = in.next();
        	if (!cityName.equals("Q")){ // if "Q" is typed, other questions will be skipped.
        		System.out.print("Latitude in Degrees: ");
        		latitudeInDegs = in.nextDouble();
        		System.out.print("Latitude in Minutes: ");
        		latitudeInMins = in.nextDouble();
        		System.out.print("Latitude in Seconds: ");
        		latitudeInSecs = in.nextDouble();
        		System.out.print("Longitude in Degrees: ");
        		longitudeInDegs = in.nextDouble();
        		System.out.print("Longitude in Minutes: ");
        		longitudeInMins = in.nextDouble();
        		System.out.print("Longitude in Seconds: ");
        		longitudeInSecs = in.nextDouble();
        		ca[i] = new City(cityName,latitudeInDegs,latitudeInMins,latitudeInSecs,longitudeInDegs,longitudeInMins,longitudeInSecs);
        		i++; // i is the number of cities created
        		System.out.println("City created...");
        		System.out.println("************************");
        	} // if
        } // do
        while (!cityName.equals("Q"));
        System.out.println("End of City Data Input Part...");
        System.out.println("*********************************");
        
        for (int m = 0; m<i; m++){
        	System.out.println(m+". City Name: "+ ca[m].getCityName() + ", Latitude: " + ca[m].getLatitude() + ", Longitude: " + ca[m].getLongitude());
        } // for
        int f = 0;
        int s = 0;
        do{
        	System.out.print("Please enter index of first city (-1 to exit...) : ");
        	f = in.nextInt(); // index of the first city
        	if (f != -1) {
        		System.out.print("Please enter index of second city: ");
        		s = in.nextInt(); // index of the second city
        		if(f < i && s < i){
        			double distance = 111*Math.sqrt(Math.pow(ca[f].getLatitude() - ca[s].getLatitude(),2) + Math.pow(ca[f].getLongitude() - ca[s].getLongitude(),2));
        			System.out.println("Distance of two cities: " + distance);
        			System.out.println();
        		} //if
        	}
        	else {System.out.println("Invalid index. Program will exit...");};
        } // do
        while (f != -1 && f < i && s < i);
	}
}
