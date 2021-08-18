## File Structure

<img width="720" alt="Screenshot 2021-08-18 at 8 58 06 PM" src="https://user-images.githubusercontent.com/85623711/129926695-fd94a6e1-d3ea-4fcf-88d1-f61ae9e7f717.png">

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
