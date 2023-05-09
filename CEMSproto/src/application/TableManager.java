package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableManager {
    public static <T> void createTable(TableView<T> tableView, ObservableList<String> columnList) {
        ObservableList<TableColumn<T, ?>> columns = FXCollections.observableArrayList();

        // create table columns dynamically
        for (String columnName : columnList) {
            String propertyName = columnName.replaceAll("\\s+", ""); // remove spaces from column name
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

    public static <T> void addDoubleClickFunctionality(TableView<T> tableView, String relativePath) {

        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && !tableView.getSelectionModel().isEmpty()) { //check whether the event was double click and the row contains a question

                //Get selected row data
                //Question rowData = questionsTableView.getSelectionModel().getSelectedItem();

                // Create a new pop-up window
                ScreenManager.popUpScreen(relativePath);
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