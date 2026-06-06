package com.omrbranch.pojo.statelist;

import java.util.ArrayList;

import com.omrbranch.pojo.postmanbasicauthlogin.Datum;
import com.omrbranch.pojo.postmanbasicauthlogin.PostmanBasicAuthLogin_Output_Pojo;
import com.omrbranch.utility.BaseClass;

import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StateList_Output_Pojo {
	 
	
	public int status;
    public String message;
    public ArrayList<StateList> data;
	
	


}
