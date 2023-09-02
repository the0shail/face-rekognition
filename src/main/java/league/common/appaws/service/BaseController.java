package league.common.appaws.service;

import javafx.scene.Node;
import league.common.appaws.Main;

public class BaseController implements Controller {
    private Node view;
    @Override
    public Node getView() { return view; }

    @Override
    public void setView(Node view) {
        this.view = view;
    }

    @Override
    public void Show() {
        PreShowing();
        Main.getNavigation().Show(this);
        PostShowing();
    }

    public void PreShowing(){}
    public void PostShowing(){}
}
