# See
# https://github.com/intuit/karate#syntax-guide
# for how to write feature scenarios
Feature: As a developer i want to know if my git search uri is running and returning expected result

  Scenario: Is the get-hot-repo-last-week uri available and functioning
    * def hotReposLastWeek =
              """
              {
                html_url : '#string',
                watchers_count : '#number',
                language : '##string',
                description : '##string',
                name : '#string'
              }
              """
    Given url microserviceUrl
    And path '/get-hot-repo-last-week'
    Given param numberOfRepos = 20
    When method GET
    Then status 200
    And match response == '#[20] hotReposLastWeek'