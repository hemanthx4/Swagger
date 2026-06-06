package com.omrbranch.pojo.addtocart;

import com.omrbranch.pojo.citylist.CityList_Input_Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddToCart_Input_Pojo {
	public String product_id;
	public String product_variation_id;
	public String type;

	public void testing1() {
		System.out.println("Hemanth");
	}

	public void testing2() {
		System.out.println("sam");
	}

	public void testing3() {
		System.out.println("karthi");
	}

}
