package xmlteam4.Project.DTOs;

import xmlteam4.Project.model.ScientificPaperCategory;
import xmlteam4.Project.model.ScientificPaperStatus;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;

public class SearchDTO {
    @NotNull
    private Boolean basic;
    private String text;
    private Date revised;
    private Date received;
    private Date accepted;
    private String version;
    private ScientificPaperStatus status;
    private ScientificPaperCategory category;
    private ArrayList<String> keywords = new ArrayList<>();

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getRevised() {
        return revised;
    }

    public void setRevised(Date revised) {
        this.revised = revised;
    }

    public Date getReceived() {
        return received;
    }

    public void setReceived(Date received) {
        this.received = received;
    }

    public Date getAccepted() {
        return accepted;
    }

    public void setAccepted(Date accepted) {
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
