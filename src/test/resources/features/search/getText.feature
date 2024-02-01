Feature: Extracción de texto en secciones del menú en bcncgroup.com

  Scenario: Verificar la extracción de texto en la sección HOME
    Given go to web "https://bcncgroup.com/"
    Then check to "HOME" has loaded
    When click in "Accept"
    Then should see text and view all text divs classes
    When click in "WHO WE ARE"
    Then check to "WHO WE ARE" has loaded
    Then should see text and view all text divs classes