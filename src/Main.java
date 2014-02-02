import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CircleBuilder;
import javafx.stage.Stage;

public class Main extends Application {

	private String[] movies;
	private int SCREEN_WIDTH = 1200;
	private int SCREEN_HEIGHT = 700;

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub

		Group root = new Group();

		String csvFile = "movies.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				movies = line.split(cvsSplitBy);

				// System.out.println("Title: " + movies[1]);
				// System.out.println("Year: " + movies[2]);
				// System.out.println("Length: " + movies[3] + "mn");
				// System.out.println("IMDB Rating: " + movies[5]);
				// System.out.println("+++++++++++++++++++++");

				Circle mov = CircleBuilder.create()
						.centerX(Math.random() * SCREEN_WIDTH)
						.centerY(Math.random() * SCREEN_HEIGHT).radius(2)
						.fill(Color.web("D94D3F")).opacity(0.5).build();

				root.getChildren().add(mov);

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("Done");

		stage.setTitle("Movies Visual Analytics");
		stage.setScene(new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT));
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		String csvFile = "movies.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				movies = line.split(cvsSplitBy);

				// System.out.println("Title: " + movies[1]);
				// System.out.println("Year: " + movies[2]);
				// System.out.println("Length: " + movies[3] + "mn");
				// System.out.println("IMDB Rating: " + movies[5]);
				// System.out.println("+++++++++++++++++++++");

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("Done");
	}

	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		}
		// only got here if we didn't return false
		return true;
	}
}
