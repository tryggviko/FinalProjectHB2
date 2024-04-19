module hi.com {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens vidmot to javafx.fxml;
    exports vidmot;
    exports vinnsla;
}