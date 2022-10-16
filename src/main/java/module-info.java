module org.openjfx {
    requires transitive javafx.controls;
	requires transitive javafx.graphics;
	requires javafx.fxml;
	requires javafx.base;
	requires com.fasterxml.jackson.databind;
    exports application;
    exports view;
    exports model;
}