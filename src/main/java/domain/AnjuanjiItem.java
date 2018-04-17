package domain;

import org.bson.types.ObjectId;


public class AnjuanjiItem {
    private ObjectId _id;

    private String OWNER;

    private String  OTHEROWNER;

    private String OLDOWNER;

    private String HOUSEPLACE;

    private String GATHERDATE;

    private String PIGEONHOLEDATE;

    public AnjuanjiItem() {
        this._id = _id;
        this.OWNER = OWNER;
        this.OTHEROWNER = OTHEROWNER;
        this.OLDOWNER = OLDOWNER;
        this.HOUSEPLACE = HOUSEPLACE;
        this.GATHERDATE = GATHERDATE;
        this.PIGEONHOLEDATE = PIGEONHOLEDATE;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getOWNER() {
        return OWNER;
    }

    public void setOWNER(String OWNER) {
        this.OWNER = OWNER;
    }

    public String getOTHEROWNER() {
        return OTHEROWNER;
    }

    public void setOTHEROWNER(String OTHEROWNER) {
        this.OTHEROWNER = OTHEROWNER;
    }

    public String getOLDOWNER() {
        return OLDOWNER;
    }

    public void setOLDOWNER(String OLDOWNER) {
        this.OLDOWNER = OLDOWNER;
    }

    public String getHOUSEPLACE() {
        return HOUSEPLACE;
    }

    public void setHOUSEPLACE(String HOUSEPLACE) {
        this.HOUSEPLACE = HOUSEPLACE;
    }

    public String getGATHERDATE() {
        return GATHERDATE;
    }

    public void setGATHERDATE(String GATHERDATE) {
        this.GATHERDATE = GATHERDATE;
    }

    public String getPIGEONHOLEDATE() {
        return PIGEONHOLEDATE;
    }

    public void setPIGEONHOLEDATE(String PIGEONHOLEDATE) {
        this.PIGEONHOLEDATE = PIGEONHOLEDATE;
    }
}
