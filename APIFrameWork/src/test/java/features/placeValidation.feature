Feature: Validating place API
@AddPlace
Scenario Outline: Verify if place is successfully added using Add place API

Given Add Place Payload "<Name>" "<Address>" "<Language>"
When user calls "addPlaceAPI" with "Post" request 
Then the api call got success with Status Code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify place_ID created maps to "<Name>" using "getPlaceAPI"
Examples:
|Name   |Address                  |Language|
|Kishore|129, side layout, cohen09|Hindi|
|Arjun|129, side layout, cohen09|French|

@DeletePlace
Scenario: Verify if Delete Place Functionality is Working
Given Delete Place Payload
When user calls "deletePlaceAPI" with "Post" request
Then the api call got success with Status Code 200
And "status" in response body is "OK"