package Controller;

import Model.FullName;
import View.FullNameView;

public class FullNameController {
    private FullName model;
    private FullNameView view;

    public FullNameController(FullNameView view, FullName model) {
        this.model = model;
        this.view = view;
    }


    public void setFullName(FullName fullName) {
        this.model = fullName;
    }

    public void updateView() {
        view.showFullName(model.getName(), model.getLastName());
    }
}
