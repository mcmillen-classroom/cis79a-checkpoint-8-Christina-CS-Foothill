package christinahunter.myplacesapp;

public class Place {

    private String mName;
    private String mDescription;

    String mPlaceId;

    public Place(){

    }

    public Place(String mName, String mDescription) {
        this.mName = mName;
        this.mDescription = mDescription;
        mPlaceId = "";
    }

    public Place(String mName, String mDescription, String mPlaceId) {
        this.mName = mName;
        this.mDescription = mDescription;
        this.mPlaceId = mPlaceId;
    }


    public String getmPlaceId() {
        return mPlaceId;
    }

    public void setmPlaceId(String mPlaceId) {
        this.mPlaceId = mPlaceId;
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

    @Override
    public String toString() {
        return "Place{" +
                "mName='" + mName + '\'' +
                ", mDescription='" + mDescription + '\'' +
                ", mPlaceId='" + mPlaceId + '\'' +
                '}';
    }
}
