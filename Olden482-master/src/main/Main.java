package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/FirstScreen.fxml"));
        stage.setTitle("First Screen");
        stage.setScene(new Scene(root, 1200, 600));
        stage.show();
}

    public static void main(String[] args){

        InHouse part1 = new InHouse(1, "Tire Nut", 10, 55.00, 4, 50, 111);
        InHouse part2 = new InHouse(2, "Radiator", 4, 20.00, 1, 50, 112);
        InHouse part3 = new InHouse(3, "Steering Wheel", 15, 200.00, 1, 50, 113);

        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addPart(part3);

        Product product1 = new Product(1000, "Smooth Tires", 2.00, 15, 1, 50);
        Product product2 = new Product(1001, "Overheating Radiator", 55.00, 26, 1, 50);
        Product product3 = new Product(1002, "Slick Brakes", 67.00, 32, 1, 50);

        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        Inventory.addProduct(product3);

        launch(args);
    }
}