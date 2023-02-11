package applications;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;

public class MainApplication extends Application {
    private Config configuration;

    @Override
    public void init() throws Exception {
        super.init();
        configuration = new Config();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Button inputButton = new Button("选择ecore文件");
        Button outputButton = new Button("生成ECL文件");
        TextField inputPathBox = new TextField();
        inputPathBox.setPrefWidth(220);
        inputPathBox.setEditable(false);

        AnchorPane anchorPane = new AnchorPane(inputButton, outputButton, inputPathBox);
        anchorPane.setPrefWidth(400);
        anchorPane.setPrefHeight(300);
        AnchorPane.setTopAnchor(inputButton, 75.0);
        AnchorPane.setLeftAnchor(inputButton, 50.0);
        AnchorPane.setTopAnchor(inputPathBox, 75.0);
        AnchorPane.setLeftAnchor(inputPathBox, 220.0);
        AnchorPane.setTopAnchor(outputButton, 150.0);
        AnchorPane.setLeftAnchor(outputButton, 200.0);

        inputPathBox.textProperty().addListener((observable, oldValue, newValue) -> {
            this.configuration.setInputPath(newValue);
        });

        inputButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("选择ecore文件");
            fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Ecore", "*.ecore")
            );
            inputPathBox.setText(fileChooser.showOpenDialog(stage).getPath());
        });

        outputButton.setOnAction(event -> {
            if (this.configuration.haveInputPath()) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("输出ECL文件");
                fileChooser.getExtensionFilters().add(
                        new FileChooser.ExtensionFilter("ECL", "*.ecl")
                );
                this.configuration.setOutputPath(fileChooser.showSaveDialog(stage));
                Loading loading = new Loading(stage);
                loading.show();
                new Thread(() -> {
                    try {
                        this.configuration.generateECLFile();
                        Desktop.getDesktop().open(this.configuration.getOutputFile());
                        loading.changeTip("ECL文件已生成, 正在打开...");
                        Thread.sleep(100);
                    } catch (Exception e) {
                        // TODO: 处理生成文件时产生的异常
                        e.printStackTrace();
                    } finally {
                        loading.closeStage();
                    }
                }).start();
            } else {
                Alert warningAlert = new Alert(Alert.AlertType.ERROR);
                warningAlert.setContentText("请选择ecore文件");
                warningAlert.show();
            }
        });

        Scene scene = new Scene(anchorPane, 550, 280);

        stage.setScene(scene);
        stage.setTitle("Ecore2ECL");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static class Loading {

        protected Stage stage;
        protected StackPane pane;
        protected Label message;

        protected Loading(Stage owner){
            message = new Label("请稍作等待...");
            message.setFont(Font.font(20));

            pane = new StackPane();
            pane.setMouseTransparent(true);
            pane.setPrefSize(owner.getWidth(), owner.getHeight());
            pane.getChildren().add(message);

            Scene scene = new Scene(pane);

            stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initOwner(owner);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.getIcons().addAll(owner.getIcons());
            stage.setX(owner.getX() + owner.getWidth() / 4);
            stage.setY(owner.getY() + owner.getHeight() / 4);
            stage.setHeight(owner.getHeight() / 2);
            stage.setWidth(owner.getWidth() / 2);
        }

        public void show() {
            Platform.runLater(() -> stage.show());
        }

        public void changeTip(String tip) {
            Platform.runLater(() -> message.setText(tip));
        }

        public void closeStage() {
            Platform.runLater(() -> stage.close());
        }
    }
}
