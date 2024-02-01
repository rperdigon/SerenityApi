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