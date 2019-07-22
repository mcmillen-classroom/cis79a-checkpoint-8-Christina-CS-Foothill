package christinahunter.myplacesapp;

public class Inhabitant {

    String mInhabitantName;
    String mInhabitantDescription;
    String mInhabitantId;

    public Inhabitant(){

    }

    public Inhabitant(String mInhabitantName, String mInhabitantDescription, String mInhabitantId) {
        this.mInhabitantName = mInhabitantName;
        this.mInhabitantDescription = mInhabitantDescription;
        this.mInhabitantId = mInhabitantId;
    }

    public String getmInhabitantName() {
        return mInhabitantName;
    }

    public void setmInhabitantName(String mInhabitantName) {
        this.mInhabitantName = mInhabitantName;
    }

    public String getmInhabitantDescription() {
        return mInhabitantDescription;
    }

    public void setmInhabitantDescription(String mInhabitantDescription) {
        this.mInhabitantDescription = mInhabitantDescription;
    }

    public String getmInhabitantId() {
        return mInhabitantId;
    }

    public void setmInhabitantId(String mInhabitantId) {
        this.mInhabitantId = mInhabitantId;
    }

}
