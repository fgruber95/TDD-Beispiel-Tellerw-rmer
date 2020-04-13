package classes;

public class Waermegeraet {

    private Heizlement heizlement = new Heizlement();
    private Tellerstapel tellerstapel = new Tellerstapel();

    public Waermegeraet(){
    }

    public void setPowerStatus(String status){
        if(status == "einschalten"){
            this.heizlement.isReadyToUse = true;
        }else if(status == "ausschalten"){
            this.heizlement.isReadyToUse = false;
        }
    }

    public String checkIfReadyToUse(){
        if(this.heizlement.isReadyToUse){
            return "betriebsbereit";
        }else{
            return "außer Betrieb";
        }
    }

    public int getStackOfPlatesSize(){
        return this.tellerstapel.addedPlates.size();
    }

    public void clearStackOfPlates(){
        this.tellerstapel.addedPlates.clear();
    }

    public void addPlate(Teller teller){
        if(checkIfPlatesCanBeAdded() == "hinzufügen möglich") {
            this.tellerstapel.addedPlates.add(teller);
        }
    }

    public String checkIfPlatesCanBeAdded(){
        if(this.tellerstapel.addedPlates.size() <= this.tellerstapel.maxCapacity && this.heizlement.isReadyToUse){
            return "hinzufügen möglich";
        }else{
            return "hinzufügen nicht möglich";
        }
    }

    public void removePlate(){
        if(checkIfPlatesCanBeRemoved() == "entnehmen möglich") {
            this.tellerstapel.addedPlates.remove(this.tellerstapel.addedPlates.size() -1);
        }
    }

    public String checkIfPlatesCanBeRemoved(){
        if(this.tellerstapel.addedPlates.size() > this.tellerstapel.minCapacity && this.heizlement.isReadyToUse){
            return "entnehmen möglich";
        }else{
            return "entnehmen nicht möglich";
        }
    }

    public String checkIfPlatesCanBeTaken() {
        if(this.tellerstapel.addedPlates.size() > this.tellerstapel.minCapacity && this.heizlement.isReadyToUse){
            return "ansehen möglich";
        }else{
            return "ansehen nicht möglich";
        }
    }

    public void setMaxCapacity(int maxCapacity){
        this.tellerstapel.maxCapacity = maxCapacity;
    }

    public void powerOff(){
        int addedPlates = tellerstapel.addedPlates.size();

        if(addedPlates == 0) {
            this.heizlement.isReadyToUse = false;
        }
    }

    public String checkStatusAfterPowerOff(){
        if(this.heizlement.isReadyToUse){
            return "noch gefüllt";
        }else{
            return "ausgeschalten";
        }
    }


}
