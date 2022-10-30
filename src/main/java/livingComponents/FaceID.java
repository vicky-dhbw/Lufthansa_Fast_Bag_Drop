package livingComponents;

public class FaceID {
    private String[][] faceID;

    public void setFaceID(String[][] faceID){
        this.faceID=faceID;
    }
    public FaceID(){
        faceID=null;
    }

    public String[][] getFaceID(){
        return faceID;
    }
}
