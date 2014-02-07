import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.animation.FillTransition;
import javafx.animation.StrokeTransition;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

	private String[] movies;
	private ArrayList<Movie> myMovies = new ArrayList<>();
	private int SCREEN_WIDTH = 1200;
	private int SCREEN_HEIGHT = 700;

	private int SELECTED_YEAR;
	private String SELECTED_GENRE;

	private Group root;
	private ToolBar toolbar;

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub

		read();

		root = new Group();
		toolbar = new ToolBar();
		toolbar.setMinWidth(SCREEN_WIDTH);
		toolbar.prefHeight(50);

		SELECTED_YEAR = 1995;
		SELECTED_GENRE = "NA";

		final Label yearLabel = new Label();
		yearLabel.setText("Year: " + SELECTED_YEAR);

		draw();

		final Slider yearSlider = new Slider();
		yearSlider.setMin(1970);
		yearSlider.setMax(2005);
		yearSlider.setValue(1995);
		yearSlider.setShowTickMarks(true);
		yearSlider.setShowTickLabels(false);
		yearSlider.setBlockIncrement(1);
		yearSlider.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0,
					Number arg1, Number arg2) {
				reset();
				SELECTED_YEAR = (int) Math.floor((Double) arg0.getValue());
				yearLabel.setText("Year: " + SELECTED_YEAR);
				draw();
			}
		});

		Separator separator1 = new Separator(Orientation.VERTICAL);

		CheckBox showAll = new CheckBox("Show all");
		showAll.selectedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean arg1, Boolean arg2) {
				// TODO Auto-generated method stub
				if (arg0.getValue()) {
					yearSlider.setDisable(true);
					reset();
					drawAll();
				} else {
					yearSlider.setDisable(false);
					reset();
					draw();
				}
			}
		});

		Separator separator2 = new Separator(Orientation.VERTICAL);

		final ToggleGroup toggleGroup = new ToggleGroup();

		final ToggleButton action = new ToggleButton("Action");
		action.setId("001");
		action.setToggleGroup(toggleGroup);
		action.setStyle("-fx-base: #D94214;");

		final ToggleButton animation = new ToggleButton("Animation");
		animation.setToggleGroup(toggleGroup);
		animation.setStyle("-fx-base: #FFF2C1;");

		final ToggleButton comedy = new ToggleButton("Comedy");
		comedy.setToggleGroup(toggleGroup);
		comedy.setStyle("-fx-base: #80A894;");

		final ToggleButton drama = new ToggleButton("Drama");
		drama.setToggleGroup(toggleGroup);
		drama.setStyle("-fx-base: #36175E;");

		final ToggleButton documentary = new ToggleButton("Documentary");
		documentary.setToggleGroup(toggleGroup);
		documentary.setStyle("-fx-base: #03658C;");

		final ToggleButton romance = new ToggleButton("Romance");
		romance.setToggleGroup(toggleGroup);
		romance.setStyle("-fx-base: #D982AB;");

		final ToggleButton shortFilm = new ToggleButton("Short");
		shortFilm.setToggleGroup(toggleGroup);
		shortFilm.setStyle("-fx-base: #52616D;");

		toggleGroup.selectedToggleProperty().addListener(
				new ChangeListener<Toggle>() {

					@Override
					public void changed(ObservableValue<? extends Toggle> arg0,
							Toggle arg1, Toggle arg2) {
						// TODO Auto-generated method stub
						reset();
						if (!action.isSelected() && !animation.isSelected()
								&& !comedy.isSelected() && !drama.isSelected()
								&& !documentary.isSelected()
								&& !romance.isSelected()
								&& !shortFilm.isSelected()) {
							SELECTED_GENRE = "NA";
							reset();
							draw();
						}
						if (action.isSelected())
							SELECTED_GENRE = "Action";
						else if (animation.isSelected())
							SELECTED_GENRE = "Animation";
						else if (comedy.isSelected())
							SELECTED_GENRE = "Comedy";
						else if (drama.isSelected())
							SELECTED_GENRE = "Drama";
						else if (documentary.isSelected())
							SELECTED_GENRE = "Documentary";
						else if (romance.isSelected())
							SELECTED_GENRE = "Romance";
						else if (shortFilm.isSelected())
							SELECTED_GENRE = "Short";
						drawWithGenre();
					}
				});

		Separator separator3 = new Separator(Orientation.VERTICAL);

		Label searchLabel = new Label("Search:");

		TextField searchField = new TextField();

		toolbar.getItems().addAll(yearLabel, yearSlider, separator1, showAll,
				separator2, action, animation, comedy, drama, documentary,
				romance, shortFilm, separator3, searchLabel, searchField);

		root.getChildren().add(toolbar);

		Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT + 25);
		scene.getStylesheets().add("stylesheet.css");

		stage.setTitle("Project Sloth");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void read() {
		String csvFile = "movies.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				movies = line.split(cvsSplitBy);

				// create a movie object for every movie read
				if (movies[18].equalsIgnoreCase("1")) {
					myMovies.add(new Movie(movies[1], movies[2], movies[3],
							movies[4], "Action"));
				} else if (movies[19].equalsIgnoreCase("1")) {
					myMovies.add(new Movie(movies[1], movies[2], movies[3],
							movies[4], "Animation"));
				} else if (movies[20].equalsIgnoreCase("1")) {
					myMovies.add(new Movie(movies[1], movies[2], movies[3],
							movies[4], "Comedy"));
				} else if (movies[21].equalsIgnoreCase("1")) {
					myMovies.add(new Movie(movies[1], movies[2], movies[3],
							movies[4], "Drama"));
				} else if (movies[22].equalsIgnoreCase("1")) {
					myMovies.add(new Movie(movies[1], movies[2], movies[3],
							movies[4], "Documentary"));
				} else if (movies[23].equalsIgnoreCase("1")) {
					myMovies.add(new Movie(movies[1], movies[2], movies[3],
							movies[4], "Romance"));
				} else {
					myMovies.add(new Movie(movies[1], movies[2], movies[3],
							movies[5], "Short"));
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

	public void reset() {
		root.getChildren().clear();
		root.getChildren().add(toolbar);
	}

	public void transition(Circle c) {
		FillTransition ft = new FillTransition(Duration.millis(500), c,
				(Color) c.getFill(), Color.WHITE);
		ft.setCycleCount(1);
		ft.setAutoReverse(false);

		ft.play();

		StrokeTransition st = new StrokeTransition(Duration.millis(500), c,
				Color.WHITE, (Color) c.getFill());
		st.setCycleCount(1);
		st.setAutoReverse(false);

		st.play();

	}

	public void reverseTransition(Circle c) {
		FillTransition ft = new FillTransition(Duration.millis(500), c,
				(Color) c.getFill(), (Color) c.getStroke());
		ft.setCycleCount(1);
		ft.setAutoReverse(false);

		ft.play();

		StrokeTransition st = new StrokeTransition(Duration.millis(500), c,
				(Color) c.getStroke(), null);
		st.setCycleCount(1);
		st.setAutoReverse(false);

		st.play();

	}

	public void drawAll() {
		for (final Movie m : myMovies) {
			final Circle circle = m.createCircle();
			circle.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					// TODO Auto-generated method stub
					System.out.println("Title: " + m.getTitle());
				}
			});

			circle.setOnMouseDragged(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					// TODO Auto-generated method stub
					circle.setCenterX(arg0.getX());
					circle.setCenterY(arg0.getY());
				}
			});

			circle.setOnMouseEntered(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					// TODO Auto-generated method stub
					transition(circle);
				}
			});

			circle.setOnMouseExited(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					// TODO Auto-generated method stub
					reverseTransition(circle);
				}
			});
			root.getChildren().add(circle);
		}
	}

	public void drawWithGenre() {
		for (final Movie m : myMovies) {
			final Circle circle = m.createCircle(SELECTED_YEAR, SELECTED_GENRE);
			circle.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					// TODO Auto-generated method stub
					System.out.println("Title: " + m.getTitle());
					// Rectangle infoBox = RectangleBuilder.create()
					// .x(circle.getCenterX()+circle.getRadius()/2+10)
					// .y(circle.getCenterY()-circle.getRadius()/2)
					// .width(100)
					// .height(100)
					// .fill(Color.BLACK)
					// .opacity(0.3)
					// .build();
					// root.getChildren().add(infoBox);
				}
			});

			circle.setOnMouseDragged(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					// TODO Auto-generated method stub
					circle.setCenterX(arg0.getX());
					circle.setCenterY(arg0.getY());
				}
			});

			circle.setOnMouseEntered(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					// TODO Auto-generated method stub
					transition(circle);
				}
			});

			circle.setOnMouseExited(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					// TODO Auto-generated method stub
					reverseTransition(circle);
				}
			});
			root.getChildren().add(circle);
		}
	}

	public void draw() {
		for (final Movie m : myMovies) {
			final Circle circle = m.createCircle(SELECTED_YEAR);
			circle.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					// TODO Auto-generated method stub
					System.out.println("Title: " + m.getTitle());
					// Rectangle infoBox = RectangleBuilder.create()
					// .x(circle.getCenterX()+circle.getRadius()/2+10)
					// .y(circle.getCenterY()-circle.getRadius()/2)
					// .width(100)
					// .height(100)
					// .fill(Color.BLACK)
					// .opacity(0.3)
					// .build();
					// root.getChildren().add(infoBox);
				}
			});

			circle.setOnMouseDragged(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					// TODO Auto-generated method stub
					circle.setCenterX(arg0.getX());
					circle.setCenterY(arg0.getY());
				}
			});

			circle.setOnMouseEntered(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					// TODO Auto-generated method stub
					transition(circle);
				}
			});

			circle.setOnMouseExited(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					// TODO Auto-generated method stub
					reverseTransition(circle);
				}
			});
			root.getChildren().add(circle);
		}
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
