package livingComponents;

import identityRelevants.IDCard;

public class Employee extends Human{

    private IDCard idCard;

    public Employee() throws Exception {
        idCard=new IDCard();
    }

    public IDCard getIdCard() {
        return idCard;
    }

    public void setIdCard(IDCard idCard) {
        this.idCard = idCard;
    }
}
