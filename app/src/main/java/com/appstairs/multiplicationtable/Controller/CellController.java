package com.appstairs.multiplicationtable.Controller;

import com.appstairs.multiplicationtable.Model.Cell;
import com.appstairs.multiplicationtable.View.MainFragment;

//TODO This singleton was created to maintain and communicate the model(Cell) with the view
//TODO Because the project has very few tasks and functionality, we have nothing to write here meantime.
public class CellController {
    private Cell model;
    private MainFragment view;
    private static final CellController ourInstance = new CellController();

    public static CellController getInstance() {
        return ourInstance;
    }

    private CellController() {}
    private CellController(Cell model, MainFragment view) {
        this.model = model;
        this.view = view;
    }

    public void updateView(){
        //TODO we can access the view public functions: view.<my function>
    }
}
