package glue;

import classes.Teller;
import classes.Waermegeraet;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class stepDefinitions {

    private Waermegeraet waermegeraet = new Waermegeraet();
    private String possible;
    private int sizeOfPlatesBefore;
    private int sizeOfPlatesAfter;

    @Before
    public void setup(){

    }

    //Scenario: Wärmegerät einschalten
    @Given("das Wärmegerät ist ausgeschaltet")
    public void das_Wärmegerät_ist_ausgeschaltet() {
        this.waermegeraet.setPowerStatus("ausschalten");
    }

    @When("ich das Wärmegerät einschalte")
    public void ich_das_Wärmegerät_einschalte() {
        this.waermegeraet.setPowerStatus("einschalten");
    }
    @Then("ist das Wärmegerät betriebsbereit")
    public void ist_das_Wärmegerät_betriebsbereit() {
        String expected = "betriebsbereit";
        String actual = this.waermegeraet.checkIfReadyToUse();
        assertEquals(expected, actual);
    }
    @Then("der Tellerstapel ist leer")
    public void der_Tellerstapel_ist_leer() {
        int expected = 0;
        int actual = this.waermegeraet.getStackOfPlatesSize();
        assertEquals(expected, actual);
    }

    //Scenario: Leeres Wärmegerät ausschalten
    @Given("das Wärmegerät ist eingeschaltet")
    public void das_Wärmegerät_ist_eingeschaltet() {
        this.waermegeraet.setPowerStatus("einschalten");
    }

    @Given("das Wärmegerät ist leer")
    public void das_Wärmegerät_ist_leer() {
        this.waermegeraet.clearStackOfPlates();
    }

    @When("ich das Wärmegerät ausschalte")
    public void ich_das_Wärmegerät_ausschalte() {
        this.waermegeraet.powerOff();
    }

    @Then("ist das Wärmegerät ausser Betrieb")
    public void ist_das_Wärmegerät_ausser_Betrieb() {
        String expected = "außer Betrieb";
        String actual = this.waermegeraet.checkIfReadyToUse();
        assertEquals(expected, actual);
    }
    @Then("es können keine Teller hinzugefügt werden")
    public void es_können_keine_Teller_hinzugefügt_werden() {
         String exptected = "hinzufügen nicht möglich";
         String actual = this.waermegeraet.checkIfPlatesCanBeAdded();
         assertEquals(exptected, actual);
    }


    //Scenario: Gefülltes Wärmegerät ausschalten

    @Given("das Wärmegerät ist gefüllt")
    public void das_Wärmegerät_ist_gefüllt() {
        this.waermegeraet.addPlate(new Teller());
    }

    @Then("erhalte ich einen Hinweis, dass das Wärmegerät noch gefüllt ist")
    public void erhalte_ich_einen_Hinweis_dass_das_Wärmegerät_noch_gefüllt_ist() {
        String expected = "noch gefüllt";
        String acutal = this.waermegeraet.checkStatusAfterPowerOff();
        assertEquals(expected, acutal);
    }
    @Then("das Wärmegerät bleibt in Betrieb")
    public void das_Wärmegerät_bleibt_in_Betrieb() {
        String expected = "betriebsbereit";
        String actual = this.waermegeraet.checkIfReadyToUse();
        assertEquals(expected, actual);
    }


    //Scenario: Volles Wärmegerät ausschalten
    @Given("das Wärmegerät ist voll")
    public void das_Wärmegerät_ist_voll() {
        for(int i=0; i < 10; i++){
            this.waermegeraet.addPlate(new Teller());
        }
    }

    //Scenario Outline: Teller hinzufügen

    @Given("die maximale Kapazität des Tellerstapels ist {int}")
    public void die_maximale_Kapazität_des_Tellerstapels_ist(int maxCapacity) {
        this.waermegeraet.setMaxCapacity(maxCapacity);
    }
    @Given("im Wärmegerät sind {int} Teller")
    public void im_Wärmegerät_sind_Telleranzahl_Teller(int stackOfPlateSize) {
        for(int i=0; i < stackOfPlateSize; i++){
            this.waermegeraet.addPlate(new Teller());
        }
    }
    @When("ich einen Teller hinzufüge")
    public void ich_einen_Teller_hinzufüge() {
        this.sizeOfPlatesBefore = this.waermegeraet.getStackOfPlatesSize();
        this.possible = this.waermegeraet.checkIfPlatesCanBeAdded();
        this.waermegeraet.addPlate(new Teller());
        this.sizeOfPlatesAfter = this.waermegeraet.getStackOfPlatesSize();
    }
    @Then("sind {int} Teller im Wärmegerät")
    public void sind_NeueTelleranzahl_Teller_im_Wärmegerät(int stackOfPlateSize) {
        int actual = this.waermegeraet.getStackOfPlatesSize();
        assertEquals(stackOfPlateSize, actual);
    }
    @Then("eine Hinweismeldung informiert mich ob das hinzufügen erfolgreich war")
    public void eine_Hinweismeldung_informiert_mich_ob_das_hinzufügen_erfolgreich_war(io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> notice = dataTable.transpose().asLists(String.class);
        assertEquals(notice.get(0).get(1), Integer.toString(this.sizeOfPlatesBefore));
        assertEquals(notice.get(1).get(1), Integer.toString(this.sizeOfPlatesAfter));
        assertEquals(notice.get(2).get(1), this.possible);
    }


    //Scenario Outline: Teller entnehmen
    @When("ich nehme einen Teller aus dem Stapel")
    public void ich_nehme_einen_Teller_aus_dem_Stapel() {
        this.possible = this.waermegeraet.checkIfPlatesCanBeRemoved();
        this.sizeOfPlatesBefore = this.waermegeraet.getStackOfPlatesSize();
        this.waermegeraet.removePlate();
        this.sizeOfPlatesAfter = this.waermegeraet.getStackOfPlatesSize();
    }

    @Then("eine Hinweismeldung informiert mich ob das entnehmen erfolgreich war")
    public void eine_Hinweismeldung_informiert_mich_ob_das_entnehmen_erfolgreich_war(io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> notice = dataTable.transpose().asLists(String.class);
        assertEquals(notice.get(0).get(1), Integer.toString(this.sizeOfPlatesBefore));
        assertEquals(notice.get(1).get(1), Integer.toString(this.sizeOfPlatesAfter));
        assertEquals(notice.get(2).get(1), this.possible);
    }

    //Scenario Outline: Sichtprüfung

    @When("ich nehme einen Teller zur Ansicht entnehme")
    public void ich_nehme_einen_Teller_zur_Ansicht_entnehme() {
        this.sizeOfPlatesBefore = this.waermegeraet.getStackOfPlatesSize();
        this.possible = this.waermegeraet.checkIfPlatesCanBeTaken();
    }
    @Then("informiert mich eine Hinweismeldung ob dies möglich ist")
    public void informiert_mich_eine_Hinweismeldung_ob_dies_möglich_ist(io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> notice = dataTable.transpose().asLists(String.class);
        assertEquals(notice.get(0).get(1), Integer.toString(this.sizeOfPlatesBefore));
        assertEquals(notice.get(1).get(1), this.possible);
    }
}
