package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableManager {
    public static <T> ObservableList<TableColumn<T, ?>> createTable(TableView<T> tableView, ObservableList<String> columnList, ObservableList<T> data) {
        ObservableList<TableColumn<T, ?>> columns = FXCollections.observableArrayList();

        // create table columns dynamically
        for (String columnName : columnList) {
            TableColumn<T, ?> column = new TableColumn<>(columnName);
            column.setCellValueFactory(new PropertyValueFactory<>(columnName));
            tableView.getColumns().add(column);
            columns.add(column);
        }

        // add data to the table dynamically
        tableView.getItems().addAll(data);
        return columns;
    }

    // Adds checkbox column
    //todo: add the check boxes ticking functionality
    public static <T> void addCheckBoxesToTable(TableView<T> tableView) {
        TableColumn<T, Boolean> checkboxColumn = new TableColumn<>("Select");
        checkboxColumn.setCellValueFactory(new PropertyValueFactory<>("selected"));
        checkboxColumn.setCellFactory(CheckBoxTableCell.forTableColumn(checkboxColumn));
//        checkboxColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.1));
        tableView.getColumns().add(0, checkboxColumn);
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