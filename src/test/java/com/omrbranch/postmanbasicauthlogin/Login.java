package com.omrbranch.postmanbasicauthlogin;

import com.omrbranch.address.Address;
import com.omrbranch.pojo.postmanbasicauthlogin.PostmanBasicAuthLogin_Output_Pojo;
import com.omrbranch.utility.BaseClass;

import io.restassured.response.Response;
import lombok.Data;

public class Login extends BaseClass {
	public static String logtoken;

	public void login() {
		initRestAssured();
		addHeader("accept", "application/json");
		addBasicAuthentication("hemanathan879@gmail.com", "Hemanth@2341");

		Response response = sendRequest("POST", "https://www.omrbranch.com/api/postmanBasicAuthLogin" + "");

		PostmanBasicAuthLogin_Output_Pojo postmanBasicAuthLogin_Output_Pojo = response
				.as(PostmanBasicAuthLogin_Output_Pojo.class);
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		String first_name = postmanBasicAuthLogin_Output_Pojo.getData().getFirst_name();
		System.out.println(first_name);
		logtoken = postmanBasicAuthLogin_Output_Pojo.getData().getLogtoken();

	}

	public static void main(String[] args) {
		Login l = new Login();
		l.login();
		Address add = new Address();
		add.selectState();
		add.cityList();
		add.addUserAddress();
		add.updateUserAddress();
		add.getUserAddress();
		add.deleteAddress();

	}

}
