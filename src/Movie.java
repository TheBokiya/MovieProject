import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CircleBuilder;

public class Movie {
	
	// Variables for the movie object
	private int SCREEN_WIDTH = 1200;
	private int SCREEN_HEIGHT = 700;

	private String title, genre;
	private int year, length;
	private double rating;
	private Color color;

	private double multiplier = 2.0;

	private Color ACTION_COLOR = Color.web("D94214");
	private Color ANIMATION_COLOR = Color.web("FFF2C1");
	private Color COMEDY_COLOR = Color.web("80A894");
	private Color DRAMA_COLOR = Color.web("36175E");
	private Color DOCUMENTARY_COLOR = Color.web("03658C");
	private Color ROMANCE_COLOR = Color.web("D982AB");
	private Color SHORT_COLOR = Color.web("52616D");

	// Constructor
	public Movie(String title, String year, String length, String rating,
			String genre) {
		this.title = title;
		if (isInteger(year) && isInteger(length)) {
			this.year = Integer.parseInt(year);
			this.length = Integer.parseInt(length);
		}
		if (isDouble(rating)) {
			if (Double.parseDouble(rating) <= 10) {
				this.rating = Double.parseDouble(rating);
			} else {
				this.rating = 1.0;
			}
		}
		this.genre = genre;

		switch (genre) {
		case "Action":
			this.color = ACTION_COLOR;
			break;
		case "Animation":
			this.color = ANIMATION_COLOR;
			break;
		case "Comedy":
			this.color = COMEDY_COLOR;
			break;
		case "Drama":
			this.color = DRAMA_COLOR;
			break;
		case "Documentary":
			this.color = DOCUMENTARY_COLOR;
			break;
		case "Romance":
			this.color = ROMANCE_COLOR;
			break;
		case "Short":
			this.color = SHORT_COLOR;
			break;
		}
	}

	// Draw the circle without year or genre
	public Circle createCircle() {
		Circle mov = CircleBuilder
				.create()
				.centerX(Math.random() * SCREEN_WIDTH)
				.centerY(
						50 + (int) (Math.random() * ((SCREEN_HEIGHT - 50) + 1)))
				.radius(rating * multiplier).fill(color).opacity(0.8).build();
		return mov;
	}

	// Draw the circle with year and genre
	public Circle createCircle(int year, String genre) {
		Circle mov = new Circle();
		if (this.year == year) {
			if (this.genre.equalsIgnoreCase(genre)) {
				mov = CircleBuilder
						.create()
						.centerX(Math.random() * SCREEN_WIDTH)
						.centerY(
								50 + (int) (Math.random() * ((SCREEN_HEIGHT - 50) + 1)))
						.radius(rating * multiplier).fill(color).opacity(0.8)
						.build();
			}
		}
		return mov;
	}

	// Create  the circle with year only
	public Circle createCircle(int year) {
		Circle mov = new Circle();
		if (this.year == year) {
			mov = CircleBuilder
					.create()
					.centerX(Math.random() * SCREEN_WIDTH)
					.centerY(
							50 + (int) (Math.random() * ((SCREEN_HEIGHT - 50) + 1)))
					.radius(rating * multiplier).fill(color).opacity(0.8)
					.build();

		}
		return mov;
	}

	// Check if it's an integer
	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		}
		// only got here if we didn't return false
		return true;
	}

	// Check if it's a double
	public static boolean isDouble(String s) {
		try {
			Double.parseDouble(s);
		} catch (NumberFormatException e) {
			return false;
		}
		// only got here if we didn't return false
		return true;
	}

	// Getters
	public String getTitle() {
		return title;
	}

	public String getGenre() {
		return genre;
	}

	public int getYear() {
		return year;
	}

	public int getLength() {
		return length;
	}

	public double getRating() {
		return rating;
	}

	public Color getColor() {
		return color;
	}

}
