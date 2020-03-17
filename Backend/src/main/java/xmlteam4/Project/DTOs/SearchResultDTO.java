package xmlteam4.Project.DTOs;

import java.util.ArrayList;

public class SearchResultDTO {
    private ArrayList<String> ownPaperIds = new ArrayList<>();
    private ArrayList<String> otherPaperIds = new ArrayList<>();


    public ArrayList<String> getOwnPaperIds() {
        return ownPaperIds;
    }

    public void setOwnPaperIds(ArrayList<String> ownPaperIds) {
        this.ownPaperIds = ownPaperIds;
    }

    public ArrayList<String> getOtherPaperIds() {
        return otherPaperIds;
    }

    public void setOtherPaperIds(ArrayList<String> otherPaperIds) {
        this.otherPaperIds = otherPaperIds;
    }
}
