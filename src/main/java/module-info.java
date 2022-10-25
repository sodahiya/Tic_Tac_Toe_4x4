module com.example.tic_tac_toe_4x4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tic_tac_toe_4x4 to javafx.fxml;
    exports com.example.tic_tac_toe_4x4;
}