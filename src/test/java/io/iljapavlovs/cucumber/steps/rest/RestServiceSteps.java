package io.iljapavlovs.cucumber.steps.rest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import io.cucumber.guice.ScenarioScoped;
import io.cucumber.java.en.When;
import io.iljapavlovs.cucumber.config.EnvironmentConfig;
import java.io.IOException;
import javax.inject.Inject;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@ScenarioScoped
public class RestServiceSteps {

  private final OkHttpClient client = new OkHttpClient();

  @Inject
  private EnvironmentConfig environmentConfig;

//  public RestServiceSteps(EnvironmentConfig environmentConfig) {
//    this.environmentConfig = environmentConfig;
//  }

//  Configuration configuration = ConfigurationManager.getConfiguration();


  @When("REST - phone code for {string} number is {string}")
  public void restPhoneCodeForNumberIs(String number, String expectedCountry) throws IOException {

    HttpUrl httpUrl = new HttpUrl.Builder()
        .scheme("http")
        .host(environmentConfig.getServiceHost())
        .port(environmentConfig.getServicePort())
        .addPathSegment("countries")

        .addQueryParameter("phoneNumber", number)

        .build();

    Request request = new Request.Builder()
        .url(httpUrl)
        .get()
        .build();

    try (Response response = client.newCall(request).execute()) {
      final String responseBody = response.body().string();

      assertThat(responseBody).isEqualTo("{\"countries\":["
          + "\"" + expectedCountry + "\"],"
          + "\"phoneNumber\":\"" + number + "\"}");
    }

  }


}
