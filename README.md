# Getting started with Serenity and Cucumber

Serenity BDD is a library that makes it easier to write high quality automated acceptance tests, with powerful reporting and living documentation features. It has strong support for both web testing with Selenium, and API testing using RestAssured.

Serenity strongly encourages good test automation design, and supports several design patterns, including classic Page Objects, the newer Lean Page Objects/ Action Classes approach, and the more sophisticated and flexible Screenplay pattern.

The latest version of Serenity supports Cucumber 6.x.

## The starter project
#### SELENIUM:

1. Realizar con Python y Selenium unos tests que recorran las secciones del menú “HOME” y “WHO WE ARE”, para extraer el valor de los párrafos que
   estén dentro de los divs con la clase “text”.
2. Esas secciones se encuentran en la web https://bcncgroup.com/

#### PYTHON o JAVA:

1. Realizar un test en Python o Java que compruebe la respuesta del API siguiente (al menos de los 5 primeros elementos, recorrer los datos para ver si el
   texto coincide con lo esperado.): https://jsonplaceholder.typicode.com/albums
2. Agregar a los tests el “flujo necesario” para los supuestos casos de que el API tuviese autenticación con:
   • Realizar esas llamadas incluyendo el grant_type client credentials de OAuth 2.0
   • Realizar esas llamadas incluyendo el grant_type authorization code de OAuth 2.0
3. Es importante aportar en el README instrucciones sobre cómo ejecutar las pruebas.
4. Agregar en el README “BDD given-when-then” para el ejercicio.

#### Enlaces de soporte:
OAUTH 2.0:

• Oauth 2.0 y los grant types (hay que entender qué es OAuth 2.0 y entender el concepto de grant_type):

• https://www.developerro.com/2019/03/19/oauth-authentication-grant-
types/#:~:text=OAuth%202.0%20es%20una%20especificaci%C3%B3n,access_token)%20para%20un%20servicio%20HTTP.

### The project directory structure
The project has build scripts for both Maven and Gradle, and follows the standard directory structure used in most Serenity projects:
```Gherkin
src
  + main                          Test runners and supporting code
    + java                          
      + starter                          
        + pages                          Page object reference
        + tasks                          Tasks to user complete
  + test
    + java                        Test runners and supporting code
   + starter
      + pages                          Page object reference
      + tasks                          Tasks to user complete
    + resources
      + features                  Feature files
     + search                  Feature file subdirectories 
             apiTestBCNC.feature
             getText.feature
```

Serenity 2.2.13 introduced integration with WebdriverManager to download webdriver binaries.

## The sample scenario
Both variations of the sample project uses the sample Cucumber scenario. 
#### 
```Gherkin
Feature: Extracción de texto en secciones del menú en bcncgroup.com

   Scenario: Verificar la extracción de texto en la sección HOME
      Given go to web "https://bcncgroup.com/"
      Then check to "HOME" has loaded
      When click in "Accept"
      Then should see text and view all text divs classes
      When click in "WHO WE ARE"
      Then check to "WHO WE ARE" has loaded
      Then should see text and view all text divs classes
```

```Gherkin
Feature: Albums API

   Scenario: Obtener lista de álbumes
      Given que el usuario solicita la lista de álbumes
      When la solicitud se realiza correctamente
      Then la respuesta debe contener los siguientes datos
         | id | title                                    | userId |
         | 1  | quidem molestiae enim                    | 1      |
         | 2  | sunt qui excepturi placeat culpa         | 1      |
         | 3  | omnis laborum odio                       | 1      |
         | 4  | non esse culpa molestiae omnis sed optio | 1      |
         | 5  | eaque aut omnis a                        | 1      |
```

The `BcngroupHomePage` class is typical of a light-weight Page Object: it is responsible uniquely for locating elements on the page, and it does this by defining locators or occasionally by resolving web elements dynamically. Use Map for return selector.
```java
  @Override
public Map<String, By> mapSelectors() {
   mapSelectors.put("HOMEpage", By.tagName("h1"));
   mapSelectors.put("Accept", By.linkText("Accept"));
   mapSelectors.put("allTextDivsClass", By.cssSelector("div.text"));
   mapSelectors.put("WHO WE ARE", By.cssSelector("a[href='https://bcncgroup.com/who-we-are/']"));
   return mapSelectors;
}
```
The `AbstractPage` and `SDFactory` used a Abstactat and static methods, for code diferents platforms or countries i prefed used this singleton desing

#### Others
The main advantage of the approach used in this example is not in the lines of code written, although Serenity does reduce a lot of the boilerplate code that you would normally need to write in a web test. The real advantage is in the use of many small, stable classes, each of which focuses on a single job. This application of the _Single Responsibility Principle_ goes a long way to making the test code more stable, easier to understand, and easier to maintain.

## Executing the tests
To run the sample project, you can either just run the `CucumberTestSuite` test runner class, or run either `mvn verify` or `gradle test` from the command line.

By default, the tests will run using Chrome. You can run them in Firefox by overriding the `driver` system property, e.g.
```json
$ mvn clean verify -Ddriver=chrome
```
Or
```json
$ gradle clean test -Pdriver=chrome
```

The test results will be recorded in the `target/site/serenity` directory.

## Generating the reports
Since the Serenity reports contain aggregate information about all of the tests, they are not generated after each individual test (as this would be extremenly inefficient). Rather, The Full Serenity reports are generated by the `serenity-maven-plugin`. You can trigger this by running `mvn serenity:aggregate` from the command line or from your IDE.

## Simplified WebDriver configuration and other Serenity extras
The sample projects both use some Serenity features which make configuring the tests easier. In particular, Serenity uses the `serenity.conf` file in the `src/test/resources` directory to configure test execution options.  
### Webdriver configuration
The WebDriver configuration is managed entirely from this file, as illustrated below:
```java
webdriver {
    driver = chrome
}
headless.mode = true

chrome.switches="""--start-maximized;--test-type;--no-sandbox;--ignore-certificate-errors;
                   --disable-popup-blocking;--disable-default-apps;--disable-extensions-file-access-check;
                   --incognito;--disable-infobars,--disable-gpu"""

```

Serenity uses WebDriverManager to download the WebDriver binaries automatically before the tests are executed.
