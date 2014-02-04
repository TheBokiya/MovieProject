import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CircleBuilder;
import javafx.stage.Stage;

public class Main extends Application {

	private String[] movies;
	private int SCREEN_WIDTH = 1200;
	private int SCREEN_HEIGHT = 700;
	private int SELECTED_YEAR;
	private Group root;
	private ToolBar toolbar;
	
	private Color ACTION_COLOR = Color.web("D94214");
	private Color ANIMATION_COLOR = Color.web("FFF2C1");
	private Color COMEDY_COLOR = Color.web("80A894");
	private Color DRAMA_COLOR = Color.web("093844");
	private Color DOCUMENTARY_COLOR = Color.web("03658C");
	private Color ROMANCE_COLOR = Color.web("D94D3F");
	private Color SHORT_COLOR = Color.web("1E1E1F");
	private Color DEFAULT_COLOR = Color.GREY;

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub

		root = new Group();
		toolbar = new ToolBar();
		toolbar.setMinWidth(SCREEN_WIDTH);
		toolbar.prefHeight(50);

		final Label yearLabel = new Label();
		yearLabel.setText("Year: ");

		Slider yearSlider = new Slider();
		yearSlider.setMin(1970);
		yearSlider.setMax(2005);
		yearSlider.setShowTickLabels(false);
		yearSlider.setBlockIncrement(1);
		yearSlider.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0,
					Number arg1, Number arg2) {
				reset();
				SELECTED_YEAR = (int) Math.floor((Double) arg0.getValue());
				yearLabel.setText("Year: " + SELECTED_YEAR);
				System.out.println(SELECTED_YEAR);
				draw(SELECTED_YEAR);
			}
		});
		
		ToggleButton action = new ToggleButton("Action");
		action.setStyle("-fx-base: #D94214;");
		
		ToggleButton animation = new ToggleButton("Animation");
		animation.setStyle("-fx-base: #FFF2C1;");
		
		ToggleButton comedy = new ToggleButton("Comedy");
		comedy.setStyle("-fx-base: #80A894;");
		
		ToggleButton drama = new ToggleButton("Drama");
		drama.setStyle("-fx-base: #093844;");
		
		ToggleButton documentary = new ToggleButton("Documentary");
		documentary.setStyle("-fx-base: #03658C;");
		
		ToggleButton romance = new ToggleButton("Romance");
		romance.setStyle("-fx-base: #D94D3F;");
		
		ToggleButton shortFilm = new ToggleButton("Short");
		shortFilm.setStyle("-fx-base: #1E1E1F;");

		toolbar.getItems().addAll(yearSlider, yearLabel, action, animation, comedy, drama, documentary, romance, shortFilm);

		root.getChildren().add(toolbar);

		stage.setTitle("Movies Visual Analytics");
		stage.setScene(new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT + 25));
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void draw(int year) {

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
				Circle mov = new Circle();
				if (isInteger(movies[2]) && Integer.parseInt(movies[2]) == year) {
					if (isInteger(movies[5])) {
						if (movies[18].equalsIgnoreCase("1")) {
							mov = createCircle(ACTION_COLOR);
						} else if (movies[19].equalsIgnoreCase("1")) {
							mov = createCircle(ANIMATION_COLOR);
						} else if (movies[20].equalsIgnoreCase("1")) {
							mov = createCircle(COMEDY_COLOR);
						} else if (movies[21].equalsIgnoreCase("1")) {
							mov = createCircle(DRAMA_COLOR);
						} else if (movies[22].equalsIgnoreCase("1")) {
							mov = createCircle(DOCUMENTARY_COLOR);
						} else if (movies[23].equalsIgnoreCase("1")) {
							mov = createCircle(ROMANCE_COLOR);
//						} else if (movies[24] == "1" && movies[24] != null) {
//							mov = createCircle(SHORT_COLOR);
						} else {
							mov = createCircle(DEFAULT_COLOR );
						}
					} 
					
					mov.setOnMouseClicked(new EventHandler<MouseEvent>() {

						@Override
						public void handle(MouseEvent arg0) {
							// TODO Auto-generated method stub
							System.out.println(movies[1]);
						}
					});
					root.getChildren().add(mov);
				}
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
	
	public void reset(){
		root.getChildren().clear();
		root.getChildren().add(toolbar);
	}
	
	public Circle createCircle(Color col){
		Circle mov = CircleBuilder.create()
				.centerX(Math.random() * SCREEN_WIDTH)
				.centerY(Math.random() * SCREEN_HEIGHT + 25)
				.radius(Double.parseDouble(movies[5])/2000).fill(col).opacity(0.8)
				.build();
		return mov;
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
