package application;

import application.ManageQuestionsScreen.UpdateQuestionController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.Function;

public class TableManager {


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

    public static <T> void importData(TableView<T> tableView, ObservableList<T> data) {
        // add data to the table dynamically
        tableView.getItems().addAll(data);
    }
    public static <T> void MakeFilterListForSearch(FilteredList<T> filteredData, TextField searchField, Function<T, String> getTextFunction) {
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(item -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                String itemText = getTextFunction.apply(item);
                return itemText.toLowerCase().contains(lowerCaseFilter);
            });
        });
    }
    // Adds checkbox column
    //todo: add the check boxes ticking functionality
    public static <T> void addCheckBoxesToTable(TableView<T> tableView) {
        TableColumn<T, Boolean> checkboxColumn = new TableColumn<>("Select");
        checkboxColumn.setCellValueFactory(new PropertyValueFactory<>("selected"));
        checkboxColumn.setCellFactory(CheckBoxTableCell.forTableColumn(checkboxColumn));
//        checkboxColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.1));
        tableView.getColumns().add(0, checkboxColumn);

        //todo: alternative and probably better way to implement check boxes
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
    //TODO: fix the boolean mess
    public static <T> void addDoubleClickFunctionality(TableView<T> tableView, String relativePath, boolean isManageQuestionTable) {

        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && !tableView.getSelectionModel().isEmpty()) { //check whether the event was double click and the row contains a question
                if (isManageQuestionTable) {
                    FXMLLoader loader = new FXMLLoader(TableManager.class.getResource(relativePath));

                    try {
                        Parent root = loader.load();

                        UpdateQuestionController updateQuestionController = loader.getController();

//                //Get selected row data
                        Question rowData = (Question) tableView.getSelectionModel().getSelectedItem();

                        updateQuestionController.setQuestion(rowData);

                        Node source = (Node) event.getSource();

                        // Get the Stage object that contains the source node
                        Stage stage = (Stage) source.getScene().getWindow();
                        updateQuestionController.setManage(stage);
                        stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                else {
                    // Create a new pop-up window
                    ScreenManager.popUpScreen(relativePath);
                }
            }
        });
    }


    //todo: add a method to change the width of columns in the table
    public static <T> void resizeColumns(TableView<T> tableView, double[] multipliers) {
        ObservableList<TableColumn<T, ?>> columns = tableView.getColumns();
//        System.out.println(columns.size());
//        System.out.println(multipliers.length);


        if (columns.size() != multipliers.length) {
            throw new IllegalArgumentException("Number of columns does not match the number of multipliers");
        }
        int index = 0;

        for (TableColumn<T, ?> column : columns) {
            column.prefWidthProperty().bind(tableView.widthProperty().multiply(multipliers[index]));
            index++;
        }
    }
}