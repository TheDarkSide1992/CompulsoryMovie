package dk.easv.GUI.Model;

import dk.easv.BLL.LogicManager;

public class MovieInfoModel {
    LogicManager logic = new LogicManager();

    public String getMovieInfo(String title, int year) {
        return logic.getMovieInfo(title, year);
    }
}
