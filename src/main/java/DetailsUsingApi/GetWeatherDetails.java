package DetailsUsingApi;

import Data.ApiWeatherData;
import io.restassured.response.Response;
import utils.PropertyReader;

import java.beans.Visibility;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class GetWeatherDetails {
    public  String baseUrl;
    public  String apiKey;
    public  String units;
    PropertyReader propertyReader;
    public GetWeatherDetails() throws IOException {
        propertyReader=new PropertyReader("/Users/prajwal/Desktop/testvagrant /assignment projects/WeatherReportingComparatorAssignment/src/main/resources/api.properties");
        this.baseUrl=propertyReader.getProperty("baseUrl");
        this.apiKey=propertyReader.getProperty("apiKey");
        this.units= propertyReader.getProperty("units");
    }
    public Response getResponse(String location){
        Response details=given()
                .params("q",location)
                .params("appid",this.apiKey)
                .params("units",this.units)
                .when()
                .get(this.baseUrl);
        return details;
    }
    public ApiWeatherData getWeatherDetails(String location){
        Response response=getResponse(location);
        String temperature=response.jsonPath().getString("main.temp");
        String humidity=response.jsonPath().getString("main.humidity");
        String visibility=response.jsonPath().getString("visibility");
        return setWeatherDetails(temperature,visibility,humidity);
    }
    public ApiWeatherData setWeatherDetails(String temperature,String visibility,String humidity){
        ApiWeatherData apiWeatherData=new ApiWeatherData();
        apiWeatherData.setApiHumidity(humidity);
        apiWeatherData.setApiTemperature(temperature);
        apiWeatherData.setApiVisibility(visibility);
        return apiWeatherData;
    }

}
