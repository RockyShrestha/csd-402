/*
 * Rakesh Shrestha
 * CSD-402 Java for Programmers
 * Professor Darrell Payne
 * Bellevue University
 * Module 11.2 Written Assignment
 * November 22, 2026
 *
 * Program: BorderPaneGridPaneDemo
 *
 * Purpose:
 *   Demonstrates two JavaFX layout panes, BorderPane and GridPane,
 *   working together in one window.
 *
 *   BorderPane (root of the scene) splits the window into five regions:
 *     TOP    - HBox header with the application title
 *     LEFT   - VBox navigation menu with three buttons
 *     CENTER - GridPane student registration form
 *     RIGHT  - VBox with short tips about each layout
 *     BOTTOM - HBox status bar that shows messages
 *
 *   GridPane (center region) arranges the form in rows and columns:
 *     column 0 holds the labels, column 1 holds the input controls,
 *     the comments TextArea spans two columns, and a CheckBox turns
 *     the grid lines on and off so the cell structure is visible.
 *
 * How to test:
 *   1. Run the program.
 *   2. Resize the window. The top and bottom regions stretch sideways,
 *      the left and right regions keep their width, and the center
 *      region (the form) takes the extra space.
 *   3. Check "Show grid lines" to see the GridPane rows and columns.
 *   4. Fill in the form and click Submit. The status bar confirms the
 *      entry. Leaving the name or email blank, or entering an email
 *      without an @ symbol (a basic check only), shows an error message.
 *   5. Click Clear to reset the form, or use the left menu buttons.
 *
 * Main method is included for IDEs with limited JavaFX support.
 */
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class BorderPaneGridPaneDemo extends Application {

  // Status label lives in the BOTTOM region. It is a field so the
  // event handlers in other methods can update its text.
  private final Label statusLabel = new Label("Ready");

  // Form controls are fields so Submit and Clear can read and reset them.
  private final TextField nameField = new TextField();
  private final TextField emailField = new TextField();
  private final ComboBox<String> courseBox = new ComboBox<>();
  private final TextArea commentsArea = new TextArea();

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {

    // ---------------------------------------------------------------
    // BorderPane: the root pane. Each region is filled by a helper
    // method so the code for each part of the window stays separate.
    // ---------------------------------------------------------------
    BorderPane root = new BorderPane();

    root.setTop(createHeader());       // HBox across the top
    root.setLeft(createMenu());        // VBox down the left side
    root.setCenter(createForm());      // GridPane in the middle
    root.setRight(createTips());       // VBox down the right side
    root.setBottom(createStatusBar()); // HBox across the bottom

    // Margin around the center node only. BorderPane provides static
    // methods to set constraints on an individual child.
    BorderPane.setMargin(root.getCenter(), new Insets(10));

    // Place the BorderPane in a scene           width  height
    Scene scene = new Scene(root,                760,   420);

    primaryStage.setTitle("BorderPane and GridPane Demo");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  /*
   * TOP region: an HBox holding the title label.
   * BorderPane stretches the top node to the full width of the window
   * and keeps it at its preferred height.
   */
  private HBox createHeader() {
    Label title = new Label("Student Registration");
    title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
    title.setStyle("-fx-text-fill: white;");

    HBox header = new HBox(title);
    header.setPadding(new Insets(12));
    header.setAlignment(Pos.CENTER_LEFT);
    header.setStyle("-fx-background-color: #4b3570;"); // Bellevue purple
    return header;
  }

  /*
   * LEFT region: a VBox of navigation buttons.
   * BorderPane keeps the left node at its preferred width, so the menu
   * does not grow when the window is made wider.
   */
  private VBox createMenu() {
    Button homeButton = new Button("Home");
    Button formButton = new Button("Register");
    Button aboutButton = new Button("About");

    // Make every button the same width as the widest one
    for (Button b : new Button[] {homeButton, formButton, aboutButton}) {
      b.setMaxWidth(Double.MAX_VALUE);
    }

    // Each button just reports which region was clicked
    homeButton.setOnAction(e -> statusLabel.setText("LEFT region: Home clicked"));
    formButton.setOnAction(e -> {
      nameField.requestFocus();
      statusLabel.setText("LEFT region: Register clicked, cursor moved to the form");
    });
    aboutButton.setOnAction(e -> statusLabel.setText(
        "About: BorderPane is the root, GridPane is in the center region"));

    VBox menu = new VBox(8, homeButton, formButton, aboutButton);
    menu.setPadding(new Insets(10));
    menu.setPrefWidth(110);
    menu.setStyle("-fx-background-color: #e8e4f0;");
    return menu;
  }

  /*
   * CENTER region: a GridPane holding the registration form.
   * add(node, column, row) places a node in a cell. Both indexes are
   * zero based, and the column comes BEFORE the row.
   */
  private GridPane createForm() {
    GridPane grid = new GridPane();
    grid.setHgap(10);                    // space between columns
    grid.setVgap(10);                    // space between rows
    grid.setPadding(new Insets(10));     // space between grid and its edge

    // Column 0 (labels) has a fixed width. Column 1 (inputs) grows
    // to fill any extra space when the window is resized.
    ColumnConstraints labelColumn = new ColumnConstraints(90);
    ColumnConstraints inputColumn = new ColumnConstraints();
    inputColumn.setHgrow(Priority.ALWAYS);
    grid.getColumnConstraints().addAll(labelColumn, inputColumn);

    // Prompt text and choices for the input controls
    nameField.setPromptText("First and last name");
    emailField.setPromptText("name@example.com");
    courseBox.getItems().addAll("CSD-402 Java for Programmers",
        "CSD-420 Advanced Java Programming", "CSD-380 DevOps");
    courseBox.setValue("CSD-402 Java for Programmers");
    courseBox.setMaxWidth(Double.MAX_VALUE);
    commentsArea.setPromptText("Optional comments");
    commentsArea.setPrefRowCount(3);
    commentsArea.setWrapText(true);

    // Row 0 to row 2: label in column 0, control in column 1
    grid.add(new Label("Name:"), 0, 0);
    grid.add(nameField, 1, 0);

    grid.add(new Label("Email:"), 0, 1);
    grid.add(emailField, 1, 1);

    grid.add(new Label("Course:"), 0, 2);
    grid.add(courseBox, 1, 2);

    // Row 3: label spans two columns -> add(node, col, row, colSpan, rowSpan)
    grid.add(new Label("Comments:"), 0, 3, 2, 1);

    // Row 4: TextArea starts in column 0 and spans both columns
    grid.add(commentsArea, 0, 4, 2, 1);

    // Row 5: grid line toggle on the left, buttons on the right
    CheckBox gridLinesBox = new CheckBox("Show grid lines");
    gridLinesBox.setOnAction(e -> {
      // gridLinesVisible draws the rows, columns, and gaps.
      // Oracle recommends it for debugging a GridPane layout.
      grid.setGridLinesVisible(gridLinesBox.isSelected());
      statusLabel.setText(gridLinesBox.isSelected()
          ? "Grid lines ON: 2 columns x 6 rows"
          : "Grid lines OFF");
    });

    Button submitButton = new Button("Submit");
    Button clearButton = new Button("Clear");
    submitButton.setDefaultButton(true); // Enter key triggers Submit
    submitButton.setOnAction(e -> submitForm());
    clearButton.setOnAction(e -> clearForm());

    // An empty Region acts as a spring that pushes the buttons right
    Region spacer = new Region();
    HBox.setHgrow(spacer, Priority.ALWAYS);

    HBox actionRow = new HBox(8, gridLinesBox, spacer, clearButton, submitButton);
    actionRow.setAlignment(Pos.CENTER_LEFT);

    // The action row spans both columns so the CheckBox text is not
    // cut off by the fixed 90 pixel width of column 0
    grid.add(actionRow, 0, 5, 2, 1);

    // Cell alignment is set per child with static GridPane methods.
    // Here the single-cell labels in column 0 (Name, Email, Course)
    // are right aligned so they sit next to their input fields.
    for (Node node : grid.getChildren()) {
      if (node instanceof Label && GridPane.getColumnSpan(node) == null) {
        GridPane.setHalignment(node, HPos.RIGHT);
      }
    }

    return grid;
  }

  /*
   * RIGHT region: a VBox with short notes about each layout.
   */
  private VBox createTips() {
    Label heading = new Label("Layout tips");
    heading.setFont(Font.font("Arial", FontWeight.BOLD, 13));

    Label tip1 = new Label("BorderPane: five regions. Center gets the extra space.");
    Label tip2 = new Label("GridPane: add(node, column, row). Indexes start at 0.");
    tip1.setWrapText(true);
    tip2.setWrapText(true);

    VBox tips = new VBox(8, heading, tip1, tip2);
    tips.setPadding(new Insets(10));
    tips.setPrefWidth(160);
    tips.setStyle("-fx-background-color: #f4f4f4;");
    return tips;
  }

  /*
   * BOTTOM region: an HBox status bar holding the status label.
   */
  private HBox createStatusBar() {
    HBox statusBar = new HBox(statusLabel);
    statusBar.setPadding(new Insets(6, 10, 6, 10));
    statusBar.setStyle("-fx-background-color: #d9d9d9;");
    return statusBar;
  }

  /*
   * Called by the Submit button. Checks that the required fields are
   * filled in, then shows a confirmation in the status bar.
   */
  private void submitForm() {
    String name = nameField.getText().trim();
    String email = emailField.getText().trim();

    if (name.isEmpty() || email.isEmpty()) {
      statusLabel.setText("Error: name and email are required");
      statusLabel.setStyle("-fx-text-fill: #b00020;");
      return;
    }
    // Basic email check only: confirms an @ symbol is present.
    // This is not full email validation, which is outside the scope
    // of this layout demonstration.
    if (!email.contains("@")) {
      statusLabel.setText("Error: email address needs an @ symbol");
      statusLabel.setStyle("-fx-text-fill: #b00020;");
      return;
    }

    statusLabel.setStyle("-fx-text-fill: black;");
    statusLabel.setText("Registered " + name + " for " + courseBox.getValue());
  }

  /*
   * Called by the Clear button. Resets every control in the form.
   */
  private void clearForm() {
    nameField.clear();
    emailField.clear();
    commentsArea.clear();
    courseBox.setValue("CSD-402 Java for Programmers");
    statusLabel.setStyle("-fx-text-fill: black;");
    statusLabel.setText("Form cleared");
  }

  /*
   * Main method only needed for IDE with limited JavaFX support.
   * Not needed for running from command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
