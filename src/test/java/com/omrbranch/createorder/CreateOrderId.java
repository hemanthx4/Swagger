package com.omrbranch.createorder;

import java.util.ArrayList;
import java.util.List;

import com.omrbranch.address.Address;
import com.omrbranch.pojo.addtocart.AddToCart_Input_Pojo;
import com.omrbranch.pojo.adduseraddress.AddUserAddress_Input_Pojo;
import com.omrbranch.pojo.adduseraddress.AddUserAddress_Output_Pojo;
import com.omrbranch.pojo.citylist.CityList;
import com.omrbranch.pojo.citylist.CityList_Input_Pojo;
import com.omrbranch.pojo.citylist.CityList_Output_Pojo;
import com.omrbranch.pojo.getcartitems.CartItemList_Pojo;
import com.omrbranch.pojo.getcartitems.GetCartItems_Output_Pojo;
import com.omrbranch.pojo.getsearchresult.GetSearchResultList_pojo;
import com.omrbranch.pojo.getsearchresult.GetSearchResult_Input_Pojo;
import com.omrbranch.pojo.getsearchresult.GetSearchResult_Output_Pojo;
import com.omrbranch.pojo.getsearchresult.Option;
import com.omrbranch.pojo.getsearchresult.Variation;
import com.omrbranch.pojo.searchproduct.Datum;
import com.omrbranch.pojo.searchproduct.SearchProduct_Input_Pojo;
import com.omrbranch.pojo.searchproduct.SearchProduct_Output_Pojo;
import com.omrbranch.postmanbasicauthlogin.Login;
import com.omrbranch.utility.BaseClass;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class CreateOrderId extends BaseClass {
	String txtCategoryId;
	String txtProductId;
	String txtVariationId;
	
	String txtVariationID ;

	public void SearchProduct() {
		initRestAssured();

		// Header
		List<Header> lstHeader = new ArrayList<Header>();

		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");

		lstHeader.add(h1);
		lstHeader.add(h2);

		Headers headers = new Headers(lstHeader);

		addHeaders(headers);

		// Basic Auth
		addBasicAuthentication("hemanathan879@gmail.com", "Hemanth@2341");

//		addPayload("{")

		SearchProduct_Input_Pojo searchProduct_Input_Pojo = new SearchProduct_Input_Pojo("nuts");

		addPayload(searchProduct_Input_Pojo);

		Response response = sendRequest("POST", "https://www.omrbranch.com/api/searchProduct");

		SearchProduct_Output_Pojo Output_Pojo = response.as(SearchProduct_Output_Pojo.class);
		ArrayList<Datum> data = Output_Pojo.getData();

		for (Datum searchproduct_Pojo : data) {
			String text = searchproduct_Pojo.getText();
			if (text.equals("Tata Sampann 100% Iranian Pistachios Roasted & Salted in Fruit & Nuts")) {
				int category_id = searchproduct_Pojo.getCategory_id();
				txtCategoryId = String.valueOf(category_id);
				System.out.println("Category Id" + txtCategoryId);
				int id = searchproduct_Pojo.getId();
				txtProductId = String.valueOf(id);
				System.out.println("product Id" + txtProductId);
				break;
			}
		}
	}

	public void getSearchResult() {
		initRestAssured();

		List<Header> lstHeader = new ArrayList<Header>();

		Header h1 = new Header("accept", "application/json");

		Header h2 = new Header("Content-Type", "application/json");

		// Bearer Token

		Header h3 = new Header("Authorization", "Bearer " + Login.logtoken);

		lstHeader.add(h1);

		lstHeader.add(h2);

		lstHeader.add(h3);

		Headers headers = new Headers(lstHeader);

		addHeaders(headers);

		GetSearchResult_Input_Pojo searchProduct_Input_Pojo = new GetSearchResult_Input_Pojo(txtCategoryId,
				txtProductId, "category");

		addPayload(searchProduct_Input_Pojo);

		Response response = sendRequest("POST", "https://www.omrbranch.com/api/getSearchResult");

		GetSearchResult_Output_Pojo getSearchProduct_Output_Pojo =

				response.as(GetSearchResult_Output_Pojo.class);

		ArrayList<GetSearchResultList_pojo> data = getSearchProduct_Output_Pojo.getData();

		for (GetSearchResultList_pojo getSearchResultList_pojo : data) {

			ArrayList<Variation> variations = getSearchResultList_pojo.getVariations();

			for (Variation variationslst : variations) {

				String specifications = variationslst.getSpecifications();

				if (specifications.equals("1 kg")) {
					ArrayList<Option> options = variationslst.getOptions();
					int variation_id = options.get(0).getVariation_id();
 txtVariationID = String.valueOf(variation_id);
					System.out.println("Variation ID :" + txtVariationID);

				}

			}
		}

	}

	public void addToCart() {

		initRestAssured();

		List<Header> lstHeader = new ArrayList<Header>();

		Header h1 = new Header("accept", "application/json");

		Header h2 = new Header("Authorization", "Bearer " + Login.logtoken);

		Header h3 = new Header("Content-Type", "application/json");

		lstHeader.add(h1);

		lstHeader.add(h2);

		lstHeader.add(h3);

		Headers headers = new Headers(lstHeader);

		addHeaders(headers);

		AddToCart_Input_Pojo addToCart_Input_Pojo = new AddToCart_Input_Pojo(

				txtProductId,

				txtVariationID,

				"plus"

		);

		addPayload(addToCart_Input_Pojo);

		Response response = sendRequest("POST", "https://www.omrbranch.com/api/addToCart");

		System.out.println(response.asPrettyString());

	}
	public void getCartItems() {



		initRestAssured();

		List<Header> lstHeader = new ArrayList<Header>();

		Header h1 = new Header("accept", "application/json");

		Header h2 = new Header("Authorization", "Bearer " + Login.logtoken);

		lstHeader.add(h1);

		lstHeader.add(h2);

		Headers headers = new Headers(lstHeader);

		addHeaders(headers);



		Response response = sendRequest("GET", "https://www.omrbranch.com/api/getCartItems");

		GetCartItems_Output_Pojo getCartItems_Output_Pojo = response.as(GetCartItems_Output_Pojo.class);

		ArrayList<CartItemList_Pojo> data = getCartItems_Output_Pojo.getData();

		for (CartItemList_Pojo getCartList : data) {

			int cart_id = getCartList.getCart_id();

			System.out.println("Cart id : " + cart_id);



		}



		System.out.println(response.asPrettyString());

	}

	public static void main(String[] args) {
		Login l = new Login();
		l.login();
		CreateOrderId create = new CreateOrderId();
		create.SearchProduct();
		create.getSearchResult();
		create.addToCart();
		create.getCartItems();

	}
}
