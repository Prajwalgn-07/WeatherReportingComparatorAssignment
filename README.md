## File Structure

<img width="849" alt="tree" src="https://user-images.githubusercontent.com/85623711/129868806-16d8a78a-85c8-4b4f-b1ce-af9355726b8f.png">

## To run the test suite(default browser is chrome)
`mvn clean test`

## To run the specific test
`mvn -Dtest=VarianceTest/ComparatorTest test`

## To run the test suite on specific browser
`mvn -Dbrowser=chrome/firefox test`

## To run the test suite on specific browser with headless mode
`mvn -Dbrowser=chrome/firefox -Dheadless=true/false test`

## To run the specific test on specific browser with headless mode
`mvn -Dtest=VarianceTest/ComparatorTest -Dbrowser=chrome/firefox -Dheadless=true/false test`
