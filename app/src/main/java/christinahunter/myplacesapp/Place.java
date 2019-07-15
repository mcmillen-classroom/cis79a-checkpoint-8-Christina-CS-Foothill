package christinahunter.myplacesapp;

public class Place {

    private String mName;
    private String mDescription;

    public Place(String mName, String mDescription) {
        this.mName = mName;
        this.mDescription = mDescription;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}
