package Utils;

import java.sql.Timestamp;


public class Film {
	
	private	int film_id;
	private	String title;
	private	String description ;
	private	int release_year;
	private	int language_id;
	private	int original_language_id;
	private	int rental_duration;
	private	double rental_rate;
	private	int length;
	private	double replacement_cost;
	private	String rating;
	private	String special_features;
	private	Timestamp last_update;

	
	public Film (int film_id,String title, String description, int release_year, int language_id, int original_language_id, int rental_duration, 
			double rental_rate, int length, double replacement_cost, String rating,String  special_features, Timestamp last_update) {
		
	}
	
}
