Feature: Kapazitätsmanagement im Wäremegerät

  Scenario: Wärmegerät einschalten
    Given das Wärmegerät ist ausgeschaltet
    When ich das Wärmegerät einschalte
    Then ist das Wärmegerät betriebsbereit
    And der Tellerstapel ist leer

  Scenario: Leeres Wärmegerät ausschalten
    Given das Wärmegerät ist eingeschaltet
    And das Wärmegerät ist leer
    When ich das Wärmegerät ausschalte
    Then ist das Wärmegerät ausser Betrieb
    And es können keine Teller hinzugefügt werden

  Scenario: Gefülltes Wärmegerät ausschalten
    Given das Wärmegerät ist eingeschaltet
    And das Wärmegerät ist gefüllt
    When ich das Wärmegerät ausschalte
    Then erhalte ich einen Hinweis, dass das Wärmegerät noch gefüllt ist
    And das Wärmegerät bleibt in Betrieb

  Scenario: Volles Wärmegerät ausschalten
    Given das Wärmegerät ist eingeschaltet
    And das Wärmegerät ist voll
    When ich das Wärmegerät ausschalte
    Then erhalte ich einen Hinweis, dass das Wärmegerät noch gefüllt ist
    And das Wärmegerät bleibt in Betrieb

  Scenario: Teller hinzufügen
    Given das Wärmegerät ist eingeschaltet
    And die maximale Kapazität des Tellerstapels ist 10
    And im Wärmegerät sind 9 Teller
    When ich einen Teller hinzufüge
    Then sind 10 Teller im Wärmegerät
    And eine Hinweismeldung informiert mich ob das hinzufügen erfolgreich war
      |Telleranzahl |NeueTelleranzahl |Hinweismeldung     |
      |9            |10               |hinzufügen möglich |

  Scenario: Teller entnehmen
    Given das Wärmegerät ist eingeschaltet
    And die maximale Kapazität des Tellerstapels ist 10
    And im Wärmegerät sind 10 Teller
    When ich nehme einen Teller aus dem Stapel
    Then sind 9 Teller im Wärmegerät
    And eine Hinweismeldung informiert mich ob das entnehmen erfolgreich war
      |Telleranzahl |NeueTelleranzahl |Hinweismeldung    |
      |10           |9                |entnehmen möglich |

  Scenario: Sichtprüfung
    Given das Wärmegerät ist eingeschaltet
    And die maximale Kapazität des Tellerstapels ist 10
    And im Wärmegerät sind 9 Teller
    When ich nehme einen Teller zur Ansicht entnehme
    Then informiert mich eine Hinweismeldung ob dies möglich ist
      |Telleranzahl |Hinweismeldung  |
      |9            |ansehen möglich |