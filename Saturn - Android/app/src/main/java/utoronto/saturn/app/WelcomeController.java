package utoronto.saturn.app;

public class WelcomeController {
    private WelcomeController view;
    private Object model;

    public WelcomeController(Object model, WelcomeController view){
        this.model = model;
        this.view = view;
    }

}