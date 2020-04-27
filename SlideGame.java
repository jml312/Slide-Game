import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.lang.Math;
import java.util.List;
import java.util.Random;

/**
 @author Josh Levy
  * A class to play the Slide Game GUI game
  * */
public class SlideGame extends Application {

    /** A Grid Pane instance */
    private final GridPane gridPane = new GridPane();

    /** Initializes an int array for board values */
    private int[][] values;

    /** Initializes a button array for board buttons */
    private Button[][] buttons;

    /** The number of rows on the board */
    private int rows = 4;

    /** The number of columns on the board */
    private int columns = 4;

    /** A boolean for if a tilde slide occurred */
    private boolean tileSlide = false;

    /** A 2D boolean array for if a merge occurred */
    private boolean[][] merges;

    /** A boolean for if the game is over */
    private boolean gameOver = false;

    /** A random instance for random text to be generated */
    private Random random = new Random();

    /** A jfx panel used to grant access to the testing methods */
    private JFXPanel jfxPanel = new JFXPanel();

    /** Creates an Image instance */
    private  Image spaceImage = new Image("Space.jpg", 500, 500, true, true);

    /** Creates a background image of a space image */
    private BackgroundImage backgroundImage = new BackgroundImage(spaceImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

    /** Creates a background and adds the background image to the background */
    private Background background = new Background(backgroundImage);

    /** a red background fill instance for the background color of the random tile */
    private final BackgroundFill redBackgroundFill = new BackgroundFill(Color.TOMATO.saturate().darker(), CornerRadii.EMPTY, Insets.EMPTY);

    /** a background instance with the red background fill instance */
    private final Background redBackground = new Background(redBackgroundFill);

    /** a green background fill instance for the background color of the merged tile */
    private final BackgroundFill greenBackgroundFill = new BackgroundFill(Color.GREEN.brighter().saturate(), CornerRadii.EMPTY, Insets.EMPTY);

    /** a background instance with the green background fill instance */
    private final Background greenBackground = new Background(greenBackgroundFill);

    /** Stores the number of slides */
    private int numberOfSlides;

    /** Stores the number of merges */
    private int numberOfMerges;

    /**
     * Creates the GUI display
     * @param primaryStage The main window of the program
     * @throws IllegalArgumentException The number or type of arguments are incorrect
     * @throws NumberFormatException The input was not entered in integer format
     */
    @Override
    public void start(Stage primaryStage)  {
        // attempts to get the command line arguments from the user and responds appropriately to the input
        try {
            // stores the command line arguments in a list
            List<String> args = getParameters().getRaw();
            // checks if the size of the arguments are 2 and are between 4 and 16 inclusively
            if (args.size() == 2 && (Integer.parseInt(args.get(0)) >= 4 && Integer.parseInt(args.get(0)) <= 16 && Integer.parseInt(args.get(1)) >= 4 && Integer.parseInt(args.get(1)) <= 16)) {
                // checks if the number of command line arguments are 2 and the first number is  16 and the second number is smaller than 8
                if (Integer.parseInt(args.get(0)) == 16 && Integer.parseInt(args.get(1)) < 8) {
                    throw new IllegalArgumentException("If 16 rows are entered, the minimum amount of columns must be 8");
                }
                // if the first condition is false
                else {
                    // sets the row value to be the first command line argument
                    int rowValue = Integer.parseInt(args.get(0));
                    // sets the row value to be the first command line argument
                    int columnValue = Integer.parseInt(args.get(1));
                    // sets the rows
                    rows = rowValue;
                    // sets the columns
                    columns = columnValue;
                }
            }
            // checks if there are no command line arguments and sets the rows and columns to the default
            else if (args.size() == 0) {
                rows = 4;
                columns = 4;
            }
            // checks if the number of command line arguments are 1 and throws an exception
            else if (args.size() == 1) {
                throw new IllegalArgumentException("Invalid number of arguments or invalid format. Enter either zero integers for 4 by 4 or two integers greater than 4 and less than 16");
            }
            // checks if the number of command line arguments are greater than 2 and throws an exception
            else if (args.size() > 2) {
                throw new IllegalArgumentException("Invalid number of arguments or invalid format. Enter either zero integers for 4 by 4 or two integers greater than 4 and less than 16");
            }
            // checks if the number of command line argument are 2 and either one or two of the arguments are smaller than 4
            else if (args.size() == 2 && Integer.parseInt(args.get(0)) < 4 || Integer.parseInt(args.get(1)) < 4 || (Integer.parseInt(args.get(0)) < 4 && Integer.parseInt(args.get(1)) < 4)) {
                throw new IllegalArgumentException("One or both of the input values are too small. Enter either zero integers for 4 by 4 or two integers greater than 4 and less than 16");
            }
            // checks if the number of command line argument are 2 and either one or two of the arguments are bigger than 16
            else if (args.size() == 2 && Integer.parseInt(args.get(0)) > 16 || Integer.parseInt(args.get(1)) > 16 || (Integer.parseInt(args.get(0)) > 16 && Integer.parseInt(args.get(1)) > 16)) {
                throw new IllegalArgumentException("One or both of the input values are too big. Enter either zero integers for 4 by 4 or two integers greater than 4 and less than 16");
            }
            // throws an exception if anything else is entered
            else {
                throw new IllegalArgumentException("Invalid number of arguments. Enter either zero integers for 4 by 4 or two integers greater than 4 and less than 16");
            }
        }
        // catches an incorrectly formatted command line argument and throws an error
        catch(NumberFormatException e) {
            throw new NumberFormatException("Invalid format, the input is not an integer. Enter either zero integers for 4 by 4 or two integers greater than 4 and less than 16");
        }

        // sets the size of the values array based on the board size
        values = new int[rows][columns];

        // sets the size of the buttons array based on the board size
        buttons = new Button[rows][columns];

        // sets the size of the boolean array based on the board size
        merges = new boolean[rows][columns];

        // initializes the values array
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                values[i][j] = 0;
            }
        }

        // initializes the button array
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                buttons[i][j] = new Button();
            }
        }

        // initializes the merges array
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                merges[i][j] = false;
            }
        }

        // makes a border for the grid pane
        gridPane.setBorder(new Border(new BorderStroke(Color.SNOW, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(5))));

        // creating a font and font size that text will display with
        Font font = new Font("serif", (Math.min(rows, columns)*(-1.61))+40);

        // sets the title of the GUI
        primaryStage.setTitle("Slide Game");

        // creating column constraint instance variable
        ColumnConstraints columnConstraints = new ColumnConstraints();

        // sets the width of each column to be the same
        columnConstraints.setPercentWidth(100.0 / columns);

        // creating row constraint instance variable
        RowConstraints rowConstraints = new RowConstraints();

        // sets the height of each column to be the same
        rowConstraints.setPercentHeight(100.0 / rows);

        // creates an array of column constraints
        ColumnConstraints[] columnArray = new ColumnConstraints[columns];

        // This loop adds each column constraint to the column constraint array and adds it to the grid pane
        for (int i = 0; i < columns; i++) {
            columnArray[i] = new ColumnConstraints();
            gridPane.getColumnConstraints().add(columnArray[i]);
        }

        // creates an array of row constraints
        RowConstraints[] rowArray = new RowConstraints[rows];

        // This loop adds each row constraint to the row constraint array and adds it to the grid pane
        for (int i = 0; i < rows; i++) {
            rowArray[i] = new RowConstraints();
            gridPane.getRowConstraints().add(rowArray[i]);
        }

        // sets the vertical gap on the grid pane to 2
        gridPane.setVgap(2);

        // sets the horizontal gap on the grid pane to 2
        gridPane.setHgap(2);

        // sets the preferred size
        gridPane.setPrefSize(1150,600);

        // This loop adds the buttons to the grid pane and sets the properties of each button on the grid including: color, background, size, font, text color, and mouseover effect
        for (int j = 0; j < rows; j++) {
            for (int i = 0; i < columns; i++) {
                gridPane.add(buttons[j][i], i, j, 1, 1);
                // Sets the button border color to white, border width to 0.9 pixels, background color to the background image, and text fill to white
                buttons[j][i].setStyle(String.format("-fx-border-color: #F5F5F5; -fx-border-width: 0.9px, 0.9px, 0.9px, 0.9px; -fx-background-color: %s; -fx-text-fill: white", background));
                // creates a glow effect on each button
                buttons[j][i].setEffect(new Glow(100));
                int finalI1 = i;
                int finalJ1 = j;
                // Sets the background color to white and the text to black on a hover
                buttons[j][i].setOnMouseEntered(e -> buttons[finalJ1][finalI1].setStyle("-fx-border-color: #000000; -fx-border-width: 0.9px, 0.9px, 0.9px, 0.9px; -fx-background-color: #FFFFFF; -fx-text-fill: black"));
                int finalI = i;
                int finalJ = j;
                // restores the button specification on the exit of a mouse
                buttons[j][i].setOnMouseExited(e -> buttons[finalJ][finalI].setStyle(String.format("-fx-border-color: #F5F5F5; -fx-border-width: 0.9px, 0.9px, 0.9px, 0.9px; -fx-background-color: %s; -fx-text-fill: white", background)));
                // sets the font of the button
                buttons[j][i].setFont(font);
                // binds the button width to the grid pane width
                buttons[j][i].prefWidthProperty().bind(gridPane.widthProperty());
                // binds the button height to the grid pane height
                buttons[j][i].prefHeightProperty().bind(gridPane.heightProperty());
            }
        }
        // generates random text on the board
        generateRandomText();

        // this loop sets the left button's action according to the grid size
        for (int i = 1; i < rows-1; i++) {
            buttons[i][0].setOnAction(event -> {
                slideLeft(values, merges);
                adjustBoard();
            });
        }

        // this loop sets the right button's action according to the grid size
        for (int i = 1; i < rows-1; i++) {
            buttons[i][columns-1].setOnAction(event -> {
                slideRight(values, merges);
                adjustBoard();
            });
        }

        // this loop sets the upper button's action according to the grid size
        for (int i = 1; i < columns-1; i++) {
            buttons[0][i].setOnAction(event -> {
                slideUp(values, merges);
                adjustBoard();
            });
        }

        // this loop sets the bottom button's action according to the grid size
        for (int i = 1; i < columns-1; i++) {
            buttons[rows-1][i].setOnAction(event -> {
                slideDown(values, merges);
                adjustBoard();
            });
        }

        // set diagonal up right button
        buttons[0][columns-1].setOnAction(event -> {
            diagonalSlideUpRight(values, merges);
            adjustBoard();
        });

        // set diagonal up left button
        buttons[0][0].setOnAction(event -> {
            diagonalSlideUpLeft(values, merges);
            adjustBoard();
        });

        // set diagonal down right button
        buttons[rows-1][columns-1].setOnAction(event -> {
            diagonalSlideDownRight(values, merges);
            adjustBoard();
        });

        // set diagonal down left button
        buttons[rows-1][0].setOnAction(event -> {
            diagonalSlideDownLeft(values, merges);
            adjustBoard();
        });

        // closes the program if the window is exited
        primaryStage.setOnCloseRequest(e -> System.exit(0));
        primaryStage.setResizable(false);  // doesn't allow the stage to be resizable
        primaryStage.setFullScreen(true);  // sets the stage to full screen
        primaryStage.sizeToScene();        // packs the stage
        Scene scene = new Scene(gridPane); // adds the grid pane to the scene
        primaryStage.setScene(scene);      // sets the scene of the stage
        primaryStage.show();               // displays the stage
    }

    /**
     * Slides all values in a 2d array accordingly to the left
     * @param values The 2d array on which the values will be slid to the left
     * @param merges The 2d array on which merges are kept track of
     * */
    public void slideLeft(int[][] values, boolean[][] merges) {
        // This loop goes through each tile on the board, starting at one tile to the right of the top left value
        // It slides the value accordingly to the left
        for (int a = 0; a < columns-1; a++) {
            for (int i = 1; i < columns; i++) {
                for (int j = 0; j < rows; j++) {
                    // checks if the current value has text
                    if (values[j][i] != 0) {
                        // checks if the value to the left has no text
                        if (values[j][i-1] == 0) {
                            values[j][i-1] = values[j][i];
                            values[j][i] = 0;
                            tileSlide = true;
                            merges[j][i-1] = false;
                        }
                        // checks if the value to the left has text
                        else {
                            // checks if the value to the left is equal to the current value
                            if (values[j][i-1] == values[j][i]) {
                                values[j][i-1] = values[j][i] + values[j][i-1];
                                values[j][i] = 0;
                                tileSlide = true;
                                merges[j][i-1] = true;
                                numberOfMerges++;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Slides all values in a 2d array accordingly to the right
     * @param values The 2d array on which the values will be slid to the right
     * @param merges The 2d array on which merges are kept track of
     * */
    public void slideRight(int[][] values, boolean[][] merges) {
        // This loop goes through each tile on the board, starting at one tile to the left of the top right value
        // It slides the value accordingly to the right
        for (int a = 0; a < columns-1; a++) {
            for (int i = columns-2; i >= 0; i--) {
                for (int j = 0; j < rows; j++) {
                    // checks if the current value has text
                    if (values[j][i] != 0) {
                        // checks if the value to the left has no text
                        if (values[j][i+1] == 0) {
                            values[j][i+1] = values[j][i];
                            values[j][i] = 0;
                            tileSlide = true;
                            merges[j][i+1] = false;
                        }
                        // checks if the value to the left has text
                        else {
                            // checks if the value to the left is equal to the current value
                            if (values[j][i+1] == values[j][i]) {
                                values[j][i+1] = values[j][i] + values[j][i+1];
                                values[j][i] = 0;
                                tileSlide = true;
                                merges[j][i+1] = true;
                                numberOfMerges++;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Slides all values in a 2d array upwards accordingly
     * @param values The 2d array on which the values will be slid upwards
     * @param merges The 2d array on which merges are kept track of
     * */
    public void slideUp(int[][] values, boolean[][] merges) {
        // This loop goes through each tile on the board, starting at one tile to the right of the top left value
        // It slides the value accordingly upwards
        for (int a = 0; a < rows-1; a++) {
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    // checks if the current value has text
                    if (values[i][j] != 0) {
                        // checks if the value to the left has no text
                        if (values[i-1][j] == 0) {
                            values[i-1][j] = values[i][j];
                            values[i][j] = 0;
                            tileSlide = true;
                            merges[i-1][j] = false;
                        }
                        // checks if the value to the left has text
                        else {
                            // checks if the value to the left is equal to the current value
                            if (values[i-1][j] == values[i][j]) {
                                values[i-1][j] = values[i][j] + values[i-1][j];
                                values[i][j] = 0;
                                tileSlide = true;
                                merges[i-1][j] = true;
                                numberOfMerges++;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Slides all values in a 2d array accordingly downwards
     * @param values The 2d array on which the values will be slid downwards
     * @param merges The 2d array on which merges are kept track of
     * */
    public void slideDown(int[][] values, boolean[][] merges) {
        // This loop goes through each tile on the board, starting at one tile to the right of the bottom left value
        // It slides the value accordingly downwards
        for (int a = 0; a < rows-1; a++) {
            for (int i = rows-2; i >= 0; i--) {
                for (int j = 0; j < columns; j++) {
                    // checks if the current value has text
                    if (values[i][j] != 0) {
                        // checks if the value to the left has no text
                        if (values[i+1][j] == 0) {
                            values[i+1][j] = values[i][j];
                            values[i][j] = 0;
                            tileSlide = true;
                            merges[i+1][j] = false;
                        }
                        // checks if the value to the left has text
                        else {
                            // checks if the value to the left is equal to the current value
                            if (values[i+1][j] == values[i][j]) {
                                values[i+1][j] = values[i][j] + values[i+1][j];
                                values[i][j] = 0;
                                tileSlide = true;
                                merges[i+1][j] = true;
                                numberOfMerges++;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Slides all values in a 2d array up and to the left
     * @param values The 2d array on which the values will be slid up and to the left
     * @param merges The 2d array on which merges are kept track of
     * */
    public void diagonalSlideUpLeft(int[][] values, boolean[][] merges) {
        // This loop starts at the location of the button and looks at the tile diagonally below it
        // It executes a slide or no slide according to that tile's value and then continues to the next tile
        for (int a = 0; a < Math.min(rows, columns) - 1; a++) {
            for (int i = 0; i < rows - 1; i++) {
                for (int j = 0; j < columns - 1; j++) {
                    // checks if the current tile has no text
                    if (values[i][j] == 0) {
                        // checks if the value diagonally below it has text and moves that tile
                        if (values[i + 1][j + 1] != 0) {
                            values[i][j] = values[i + 1][j + 1];
                            values[i + 1][j + 1] = 0;
                            tileSlide = true;
                            merges[i][j] = false;
                            merges[i + 1][j + 1] = false;
                        }
                    }
                    // checks if the current value has text
                    else {
                        // if the value diagonally below it has the same value it merges them
                        if (values[i + 1][j + 1] == values[i][j]) {
                            values[i][j] = values[i][j] + values[i + 1][j + 1];
                            values[i + 1][j + 1] = 0;
                            tileSlide = true;
                            merges[i + 1][j + 1] = false;
                            merges[i][j] = true;
                            numberOfMerges++;
                        }
                    }
                }
            }
        }
    }

    /**
     * Slides all values in a 2d array down and to the left
     * @param values The 2d array on which the values will be slid down and to the left
     * @param merges The 2d array on which merges are kept track of
     * */
    public void diagonalSlideDownLeft(int[][] values, boolean[][] merges) {
        // This loop starts at the location of the button and looks at the tile diagonally above it
        // It executes a slide or no slide according to that tile's value and then continues to the next tile
        for (int a = 0; a < Math.min(rows, columns) - 1; a++) {
            for (int i = rows-1; i >= 1; i--) {
                for (int j = 0; j < columns - 1; j++) {
                    // checks if the current tile has no text
                    if (values[i][j] == 0) {
                        // checks if the value diagonally above it has text and moves that tile
                        if (values[i - 1][j + 1] != 0) {
                            values[i][j] = values[i - 1][j + 1];
                            values[i - 1][j + 1] = 0;
                            tileSlide = true;
                            merges[i][j] = false;
                            merges[i - 1][j + 1] = false;
                        }
                    }
                    // checks if the current value has text
                    else {
                        // if the value diagonally above it has the same value it merges them
                        if (values[i - 1][j + 1] == values[i][j]) {
                            values[i][j] = values[i][j] + values[i - 1][j + 1];
                            values[i - 1][j + 1] = 0;
                            tileSlide = true;
                            merges[i - 1][j + 1] = false;
                            merges[i][j] = true;
                            numberOfMerges++;
                        }
                    }
                }
            }
        }
    }

    /**
     * Slides all values in a 2d array up and to the right
     * @param values The 2d array on which the values will be slid up and to the right
     * @param merges The 2d array on which merges are kept track of
     * */
    public void diagonalSlideUpRight(int[][] values, boolean[][] merges) {
        // This loop starts at the location of the button and looks at the tile diagonally below it
        // It executes a slide or no slide according to that tile's value and then continues to the next tile
        for (int a = 0; a < Math.min(rows, columns) - 1; a++) {
            for (int i = 0; i <= rows - 2; i++) {
                for (int j = columns-1; j >= 1; j--) {
                    // checks if the current tile has no text
                    if (values[i][j] == 0) {
                        // checks if the value diagonally below it has text and moves that tile
                        if (values[i + 1][j - 1] != 0) {
                            values[i][j] = values[i + 1][j - 1];
                            values[i + 1][j - 1] = 0;
                            tileSlide = true;
                            merges[i][j] = false;
                            merges[i + 1][j - 1] = false;
                        }
                    }
                    // checks if the current value has text
                    else {
                        // if the value diagonally below it has the same value it merges them
                        if (values[i + 1][j - 1] == values[i][j]) {
                            values[i][j] = values[i][j] + values[i + 1][j - 1];
                            values[i + 1][j - 1] = 0;
                            tileSlide = true;
                            merges[i + 1][j - 1] = false;
                            merges[i][j] = true;
                            numberOfMerges++;
                        }
                    }
                }
            }
        }
    }

    /**
     * Slides all values in a 2d array down and to the right
     * @param values The 2d array on which the values will be slid down and to the right
     * @param merges The 2d array on which merges are kept track of
     * */
    public void diagonalSlideDownRight(int[][] values, boolean[][] merges) {
        // This loop starts at the location of the button and looks at the tile diagonally above it
        // It executes a slide or no slide according to that tile's value and then continues to the next tile
        for (int a = 0; a < Math.min(rows, columns) - 1; a++) {
            for (int i = rows-1; i >= 1; i--) {
                for (int j = columns-1; j >= 1; j--) {
                    // checks if the current tile has no text
                    if (values[i][j] == 0) {
                        // checks if the value diagonally above it has text and moves that tile
                        if (values[i - 1][j - 1] != 0) {
                            values[i][j] = values[i - 1][j - 1];
                            values[i - 1][j - 1] = 0;
                            tileSlide = true;
                            merges[i][j] = false;
                            merges[i - 1][j - 1] = false;
                        }
                    }
                    // checks if the current value has text
                    else {
                        // if the value diagonally above it has the same value it merges them
                        if (values[i - 1][j - 1] == values[i][j]) {
                            values[i][j] = values[i][j] + values[i - 1][j - 1];
                            values[i - 1][j - 1] = 0;
                            tileSlide = true;
                            merges[i - 1][j - 1] = false;
                            merges[i][j] = true;
                            numberOfMerges++;
                        }
                    }
                }
            }
        }
    }

    /**
     * Adjusts each button on the board according the numerical values on the board
     * It also merges the appropriate tiles and creates random text if necessary */
    public void adjustBoard() {
        // This loop checks each tile on the board
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (values[i][j] == 0) {
                    buttons[i][j].setText(null);
                }
                else {
                    buttons[i][j].setText(Integer.toString(values[i][j]));
                }
            }
        }
        // checks if a tile slide occurs and if it does, it generates random text by calling the generateRandomText method
        if (tileSlide) {
            numberOfSlides++;
            // generates random text
            generateRandomText();
            // checks if a merge occurs and proceeds appropriately
            checkForMerges();
        }
        tileSlide = false;
    }

    /** Generates text ona random open square of the board */
    public void generateRandomText() {
        // restores all text to its default
        restoreFormat();
        // checks for a game over
        checkForGameOver();
        if (!gameOver) {
            // creates a random column number
            int columnRandomNumber = random.nextInt(columns);
            // creates a random row  number
            int rowRandomNumber = random.nextInt(rows);
            // checks if the value contains text
            if (values[rowRandomNumber][columnRandomNumber] != 0) {
                // This loop keeps checking for a random open square
                while (values[rowRandomNumber][columnRandomNumber] != 0) {
                    columnRandomNumber = random.nextInt(4);
                    rowRandomNumber = random.nextInt(4);
                }
            }
            // sets the value of the random tile to 1
            values[rowRandomNumber][columnRandomNumber] = 1;
            // sets the button on the random tile's text to 1
            buttons[rowRandomNumber][columnRandomNumber].setText(Integer.toString(values[rowRandomNumber][columnRandomNumber]));
            // sets the button on the random tile's style
            buttons[rowRandomNumber][columnRandomNumber].setStyle(String.format("-fx-border-color: #F5F5F5; -fx-border-width: 0.9px, 0.9px, 0.9px, 0.9px; -fx-background-color: %s; -fx-text-fill: white;", redBackground));
            // sets the button on the random tile's background
            buttons[rowRandomNumber][columnRandomNumber].setBackground(redBackground);
            int finalColumnRandomNumber = columnRandomNumber;
            int finalRowRandomNumber = rowRandomNumber;
            // sets the button on the random tile's on mouse entered property
            buttons[rowRandomNumber][columnRandomNumber].setOnMouseEntered(e -> buttons[finalRowRandomNumber][finalColumnRandomNumber].setStyle("-fx-border-color: #000000; -fx-border-width: 0.9px, 0.9px, 0.9px, 0.9px; -fx-background-color: #FFFFFF; -fx-text-fill: red;"));
            int finalColumnRandomNumber1 = columnRandomNumber;
            int finalRowRandomNumber1 = rowRandomNumber;
            // sets the button on the random tile's on mouse exited property
            buttons[rowRandomNumber][columnRandomNumber].setOnMouseExited(e -> buttons[finalRowRandomNumber1][finalColumnRandomNumber1].setStyle(String.format("-fx-border-color: #F5F5F5; -fx-border-width: 0.9px, 0.9px, 0.9px, 0.9px; -fx-background-color: %s; -fx-text-fill: white;", redBackground)));
        }
    }

    /** Sets the properties for a merged tile * */
    public void checkForMerges() {
        // this loop goes through the board and sets a merged tile's color and style
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                // sets zero values to false
                if (values[i][j] == 0) {
                    merges[i][j] = false;
                }
                // checks if the merge is true and the value isn't one
                if (merges[i][j] && values[i][j] != 1) {
                    // sets the merged tile's style
                    buttons[i][j].setStyle(String.format("-fx-border-color: #F5F5F5; -fx-border-width: 0.9px, 0.9px, 0.9px, 0.9px; -fx-background-color: %s; -fx-text-fill: white;", greenBackground));
                    // sets the merged tile's background
                    buttons[i][j].setBackground(greenBackground);
                    int finalI = i;
                    int finalJ = j;
                    // sets the merged tile's on mouse entered property
                    buttons[i][j].setOnMouseEntered(e -> buttons[finalI][finalJ].setStyle("-fx-border-color: #000000; -fx-border-width: 0.9px, 0.9px, 0.9px, 0.9px; -fx-background-color: #FFFFFF; -fx-text-fill: green;"));
                    int finalI1 = i;
                    int finalJ1 = j;
                    // sets the merged tile's on mouse exited property
                    buttons[i][j].setOnMouseExited(e -> buttons[finalI1][finalJ1].setStyle(String.format("-fx-border-color: #F5F5F5; -fx-border-width: 0.9px, 0.9px, 0.9px, 0.9px; -fx-background-color: %s; -fx-text-fill: white;", greenBackground)));
                }
                merges[i][j] = false;
            }
        }
    }

    /** Restores all text on the board to its original format */
    public void restoreFormat() {
        // this loop goes through each value on the board and sets it back to its default settings at the beginning
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                // sets the style of the button
                buttons[i][j].setStyle(String.format("-fx-border-color: #F5F5F5; -fx-border-width: 0.9px, 0.9px, 0.9px, 0.9px; -fx-background-color: %s; -fx-text-fill: white", background));
                // sets the background of the button
                buttons[i][j].setBackground(background);
                int finalI1 = i;
                int finalJ1 = j;
                // sets the on mouse entered property of the button
                buttons[i][j].setOnMouseEntered(e -> buttons[finalI1][finalJ1].setStyle("-fx-border-color: #000000; -fx-border-width: 0.9px, 0.9px, 0.9px, 0.9px; -fx-background-color: #FFFFFF; -fx-text-fill: black"));
                int finalI = i;
                int finalJ = j;
                // sets the on mouse exited property of the button
                buttons[i][j].setOnMouseExited(e -> buttons[finalI][finalJ].setStyle(String.format("-fx-border-color: #F5F5F5; -fx-border-width: 0.9px, 0.9px, 0.9px, 0.9px; -fx-background-color: %s; -fx-text-fill: white", background)));
            }
        }
    }

    /** If all tiles are filled and no move can be done, the board is cleared and the game stats appear */
    public void checkForGameOver() {
        // Stores the game high score
        int highScore = 0;

        // a boolean for whether or not every tile on the board contains text
        boolean gameOver1 = true;
        // a boolean for whether or not every tile on the board contains text
        boolean gameOver2 = true;
        // a boolean for whether or not every tile on the board contains text
        boolean gameOver3 = true;
        // a boolean for whether or not every tile on the board contains text
        boolean gameOver4 = true;

        // This loop looks at the first column and checks if no possible moves can occur
        for (int i = 0; i <= rows-2; i++) {
            gameOver1 = values[i + 1][0] != 0 && values[i][1] != 0 && values[i + 1][1] != 0 && values[0][0] != values[i + 1][0] && values[0][0] != values[i][1] && values[0][0] != values[i + 1][1];
            if (!gameOver1) {
                break;
            }
        }

        // This loop looks at the last column and checks if no moves can occur
        for (int i = 0; i <= rows-2; i++) {
            gameOver2 = values[i+1][columns-2] != 0 && values[i+1][columns-1] != 0 && values[i][columns-1] != values[i+1][columns-2] && values[i][columns-1] != values[i+1][columns-1];
            if (!gameOver2) {
                break;
            }
        }

        // This loop looks at all tiles in the middle columns (not including the last row) and checks if no moves can occur
        for (int i = 0; i <= rows-2; i++) {
            for (int j = 1; j < columns-1; j++) {
                gameOver3 = values[i+1][j-1] != 0 && values[i+1][j] != 0 && values[i+1][j+1] != 0 && values[i][j+1] != 0 && values[i][j] != values[i+1][j-1] && values[i][j] !=  values[i+1][j] && values[i][j] != values[i+1][j+1] && values[i][j] != values[i][j+1];
                if (!gameOver3) {
                    break;
                }
            }
        }

        // This loop looks at the last row and checks for if the value to the right of it is not able to be merged
        for (int i = 0; i < columns-1; i++) {
            gameOver4 = values[rows-1][i] != values[rows-1][i+1];
            if (!gameOver4) {
                break;
            }
        }

        // Checks if the game is over
        if (gameOver1 && gameOver2 && gameOver3 && gameOver4) {
            // a boolean specifying that the game is over
            gameOver = true;
            // This loop looks at every tile on the board and finds the largest value as well as inverting the color and text of each tile to show that the game is over
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    // checks if the current tile has a higher value than the current high score
                    if (values[i][j] > highScore) {
                        highScore = values[i][j];
                    }
                }
            }

            // clears the grid pane
            gridPane.getChildren().clear();

            // makes a border for the grid pane
            gridPane.setBorder(new Border(new BorderStroke(Color.CRIMSON, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(5))));

            // Creates all the text to be put on the screen
            Text game_over = new Text("Game Over");
            Text moveStats = new Text("You Had " + numberOfSlides + " Tile Slides and " + numberOfMerges + " Tile Merges");
            Text score = new Text("Your High Score Was " + highScore);
            Text exit = new Text("Click Here to Exit");

            // sets the alignment of the grid pane and adds the text
            gridPane.setAlignment(Pos.CENTER);
            gridPane.getChildren().setAll(game_over, moveStats, score, exit);

            // sets the location of all text on the board
            gridPane.getChildren().get(0).setTranslateX(230);
            gridPane.getChildren().get(0).setTranslateY(-200);
            gridPane.getChildren().get(2).setTranslateX(250);
            gridPane.getChildren().get(2).setTranslateY(100);
            gridPane.getChildren().get(3).setTranslateX(400);
            gridPane.getChildren().get(3).setTranslateY(200);

            // sets the font and font size of the text
            game_over.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 100));
            moveStats.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 50));
            score.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 50));
            exit.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 25));

            // sets the effect of the text
            game_over.setEffect(new Glow(50));
            moveStats.setEffect(new Glow(50));
            score.setEffect(new Glow(50));
            exit.setEffect(new Glow(50));

            // sets the initial fill color of the text
            game_over.setFill(Color.RED.darker());
            moveStats.setFill(Color.SNOW.brighter().brighter().brighter());
            score.setFill(Color.SNOW.brighter().brighter().brighter());
            exit.setFill(Color.SNOW.brighter().brighter().brighter());

            // changes the text color on a mouse hover
            exit.setOnMouseEntered(e -> exit.setFill(Color.GREEN.brighter()));
            exit.setOnMouseExited(e -> exit.setFill(Color.SNOW.brighter().brighter().brighter()));

            // sets the on click exit property for the text
            exit.setOnMouseClicked(event -> System.exit(0));

            // creates the background image and adds it to the grid pane background
            Image space = new Image("Space.jpg", 1750,1750, true, true);
            BackgroundImage backgroundImage = new BackgroundImage(space, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
            Background spaceBackground = new Background(backgroundImage);
            gridPane.setBackground(spaceBackground);
        }
    }

    /**
     * The main method to run the program
     * @param args the command line arguments
     * */
    public static void main(String[] args) {
        // runs the GUI, passing in the command line arguments
        launch(args);
    }
}