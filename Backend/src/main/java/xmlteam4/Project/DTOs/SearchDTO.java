package xmlteam4.Project.DTOs;

import xmlteam4.Project.model.ScientificPaperCategory;
import xmlteam4.Project.model.ScientificPaperStatus;

import java.util.ArrayList;

public class SearchDTO {
    private Boolean basic;
    private String text;
    private Long revised;
    private Long received;
    private Long accepted;
    private String version;
    private ScientificPaperStatus status;
    private ScientificPaperCategory category;
    private ArrayList<String> keywords;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getRevised() {
        return revised;
    }

    public void setRevised(Long revised) {
        this.revised = revised;
    }

    public Long getReceived() {
        return received;
    }

    public void setReceived(Long received) {
        this.received = received;
    }

    public Long getAccepted() {
        return accepted;
    }

    public void setAccepted(Long accepted) {
        this.accepted = accepted;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public ScientificPaperStatus getStatus() {
        return status;
    }

    public void setStatus(ScientificPaperStatus status) {
        this.status = status;
    }

    public ScientificPaperCategory getCategory() {
        return category;
    }

    public void setCategory(ScientificPaperCategory category) {
        this.category = category;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(ArrayList<String> keywords) {
        this.keywords = keywords;
    }

    public Boolean getBasic() {
        return basic;
    }

    public void setBasic(Boolean basic) {
        this.basic = basic;
    }
}
