Feature: Validating Place API's

  Scenario: Verify if Places is being Successfully added using AddPlaceAPI
    Given Add Place Payload
    When User calls "AddPlaceAPI" with Post http request
    Then The API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"

  Scenario Outline: By using EXAMPLES table verify if Places is being Successfully added using AddPlaceAPI
    Given Add Place Payload with "<Name>" "<Language>" "<Address>"
    When User calls "addPlaceAPI" with Post http request
    Then The API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    Examples:
      | Name    | Language | Address     |
      | My home | Turkish  | Istikbal St |
      | My job  | English  | Pendergrass |

  Scenario Outline: By using ENUM Class verify if Places is being Successfully added using AddPlaceAPI
    Given Add Place Payload with "<Name>" "<Language>" "<Address>"
    When User calls "addPlaceAPI" with "POST" http request with ENUM
    Then The API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    Examples:
      | Name  | Language | Address     |
      | My AA | Turkish  | Istikbal St |
#      | My BB | English  | Pendergrass |

  @AddPlace @Regression
  Scenario Outline: Verification by suong Place ID
    Given Add Place Payload with "<Name>" "<Language>" "<Address>"
    When User calls "addPlaceAPI" with "POST" http request with ENUM
    Then The API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And Verify place_ID created maps to "<Name>" using "getPlaceAPI"
    Examples:
      | Name  | Language | Address     |
#      | My AA | Turkish  | Istikbal St |
      | My BB | English  | Pendergrass |

  @DeletePlace @Regression
  Scenario: Verify if Delete Place functionality is working
    Given DeletePlace Payload
    When User calls "deletePlaceAPI" with "POST" http request with ENUM
    Then The API call got success with status code 200
    And "status" in response body is "OK"