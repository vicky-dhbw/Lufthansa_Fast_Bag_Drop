package identityRelevants;

public enum BookingClass {
    B(0), //Business
    P(1), //Premium Economy
    E(2); //Economy

    private final int classCode;

    BookingClass(int classCode) {
        this.classCode = classCode;
    }
    public int getClassCode(){
        return classCode;
    }
}
