package demoClass;

import java.util.ArrayList;
import java.util.List;

public class TManageCom {
    private String manageComPid;
    private String manageComId;
    private String name;

    private List<TManageCom> tManageComs = new ArrayList<>();

    public TManageCom() {
    }

    public TManageCom(String manageCom, String upManageCom, String name, List<TManageCom> tManageComs) {
        this.manageComId = manageCom;
        this.manageComPid = upManageCom;
        this.name = name;
        this.tManageComs = tManageComs;
    }

    public String getManageCom() {
        return manageComId;
    }

    public void setManageCom(String manageCom) {
        this.manageComId = manageCom;
    }

    public String getUpManageCom() {
        return manageComPid;
    }

    public void setUpManageCom(String upManageCom) {
        this.manageComPid = upManageCom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TManageCom> gettManageComs() {
        return tManageComs;
    }

    public void settManageComs(List<TManageCom> tManageComs) {
        this.tManageComs = tManageComs;
    }
}
