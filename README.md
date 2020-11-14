> # JUnit-Template
JUnit 5 + Allure Reporting template project.

> ## Dependencies

- JUnit - Testing Framework
- Allure - Reporting Framework
- Slf4j - Logging Framework
- Selenium - Browswer Automation Framework
- WebDriverManager - Setup drivers for Selenium
- Rest-Assured - Rest API Framework
- Jackson-Databind - JSON Manipulation Framework

> ## Project Setup

1. Download the project from this page by clicking on Code > Download ZIP or with the provided HTTPS or SSH options.
2. Open the project using an IDE such as Intellij or Eclipse.
3. Once the IDE has opened the project, run ```mvn test```
4. On first run, the IDE should install all required maven dependencies.
5. Verify that the results show "Tests run: 3, Failures: 0, Errors: 0, Skipped: 0" and "BUILD SUCCESS"
6. Open pom.xml then update the group ID to your company domain in reverse domain name notation. (Example if your company domain is found at example.com then the reverse domain name notation would be com.example)
7. Update the artifactId to be the project you are currently working on.
8. Update the version according to your situation.
9. Decide the versions of the libraries in the properties section.
10. Add or remove libraries according to your situation.
11. If using a main class that is not App.java then update the pom.xml build/plugin section of the maven-assembly-plugin to the new main class. Then update the final name as required.
12. Update the test-workflow.yml based on your situation.
13. Add the allure folder .allure/allure-2.8.1 to your path in order to be able to call allure executable from anywhere.
14. In order to generate reports using allure, open a command prompt in the project's main folder. Then run ```allure serve target/allure-results/```

> ## Build Setup

- maven-compiler-plugin: sets the java version.
- maven-surefire-plugin: runs junit tests and provides results to allure for reporting.
- maven-jar-plugin: supresses generation of the default jar that is created during the package phase.
- maven-assembly-plugin: generates a jar with dependencies packed in (fat jar) with the name: ${project.artifactId}-${project.version}.jar (Example: Project-Template-1.0.0.jar)
- .github/workflows/test-workflow.yml will: 
    - run on push to main branch, on pull request to main branch, and at 00:00 on Sunday.
    - checkout your code to a ubuntu server running the latest version of ubuntu.
    - setup JDK 11.0.6
    - cache all required maven packages based on pom.xml
    - build the project with maven using the following command ```mvn -B package --file pom.xml```
    - copy the generated jar file to a directory called output
    - generate allure results as json and html files.
    - package the allure results and the jar file into a zip file called reports+jar that will be put in the artifacts section of the Actions tab on github under "Java CI".
