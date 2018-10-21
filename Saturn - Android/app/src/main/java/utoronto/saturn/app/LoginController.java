package utoronto.saturn.app;

public class LoginController {
    private LoginView view;
    private Object model;

    public LoginController(Object model, LoginView view){
        this.model = model;
        this.view = view;
    }

}
