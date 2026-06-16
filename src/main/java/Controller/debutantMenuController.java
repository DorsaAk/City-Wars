package Controller;


import javafx.application.Platform;
import javafx.scene.input.MouseEvent;
import view.debutantMenu;
import view.loginMenu;
import view.signupMenu;

public class debutantMenuController {
    public void function2(MouseEvent mouseEvent) {
        loginMenu signUpMenu = new loginMenu();
        try {
            debutantMenu.stage.close();
            signUpMenu.start(debutantMenu.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public void function1(MouseEvent mouseEvent) {
        signupMenu signupmenu = new signupMenu();
        try {
            debutantMenu.stage.close();
            signupmenu.start(debutantMenu.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
