package util;

import application.manageQuestionsScreen.UpdateQuestionController;
import entity.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * class containing functions related to table creation
 * and interactivity
 */
public class TableManager {

    /**
     * creates a new table object
     *
     * @param tableView  the table object that is being built
     * @param columnList a list of the table's columns
     */
    public static <T> void createTable(TableView<T> tableView, ObservableList<String> columnList) {
        ObservableList<TableColumn<T, ?>> columns = FXCollections.observableArrayList();

        // create table columns dynamically
        for (String columnName : columnList) {
            String propertyName = columnName.toLowerCase().replaceAll("\\s+", "_"); // remove spaces from column name
            TableColumn<T, ?> column = new TableColumn<>(columnName);
            column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
            tableView.getColumns().add(column);
            columns.add(column);
        }
    }

    /**
     * imports data into the given table
     *
     * @param tableView the table object that is receiving the data
     * @param data      the list of table entries that are being imported
     */
    public static <T> void importData(TableView<T> tableView, ObservableList<T> data) {
        // add data to the table dynamically
        tableView.getItems().addAll(data);
    }

    /**
     * Creates a filtered list of the table's entries based on user input
     *
     * @param filteredData    the original list of entries
     * @param searchField     the text field where the input is read
     * @param getTextFunction the function that reads the entity's text value that represents the searchable text
     */
    public static <T> void MakeFilterListForSearch(FilteredList<T> filteredData, TextField searchField,
                                                   Function<T, String> getTextFunction) {
        searchField.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(item -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }

            String lowerCaseFilter = newValue.toLowerCase();
            String itemText = getTextFunction.apply(item);
            return itemText.toLowerCase().contains(lowerCaseFilter);
        }));
    }

    /**
     * adds a checkbox for every element in the given table
     *
     * @param tableView the relevant table object
     */
    public static <T> void addCheckBoxesToTable(TableView<T> tableView) {
        TableColumn<T, Boolean> checkboxColumn = new TableColumn<>("Select");
        checkboxColumn.setCellValueFactory(new PropertyValueFactory<>("selected"));
        checkboxColumn.setCellFactory(CheckBoxTableCell.forTableColumn(checkboxColumn));
//        checkboxColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.1));
        tableView.getColumns().add(0, checkboxColumn);

        //TODO: alternative and probably better way to implement check boxes
        //        questionCheckBoxColumn.setCellFactory(column -> new TableCell<>() {
//            private final CheckBox checkBox = new CheckBox();
//
//            @Override
//            protected void updateItem(CheckBox item, boolean empty) {
//                super.updateItem(item, empty);
//                if (empty) {
//                    setGraphic(null);
//                } else {
//                    setGraphic(checkBox);
//                }
//            }
//        });
    }

    //TODO: convert the controller to a generic one so the method
    // will work for every double click,
    // not only on updateQuestion screen.
    // the method of the controller should not be invoked here.
    public static <T> void addDoubleClickFunctionality(TableView<T> tableView, String relativePath, Consumer<String> setFunctionsFunction) {

        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && !tableView.getSelectionModel().isEmpty()) { //check whether the event was double click and the row contains a question
                setFunctionsFunction.accept(relativePath);
            }
        });
    }

    /**
     * resizes every column in the table based on given percentage values
     *
     * @param tableView   the table object that is receiving the column size values
     * @param multipliers the column size values
     */
    public static <T> void resizeColumns(TableView<T> tableView, double[] multipliers) {
        ObservableList<TableColumn<T, ?>> columns = tableView.getColumns();
        int index = 0;

        for (TableColumn<T, ?> column : columns) {
            column.prefWidthProperty().bind(tableView.widthProperty().multiply(multipliers[index]));
            index++;
        }
    }
}