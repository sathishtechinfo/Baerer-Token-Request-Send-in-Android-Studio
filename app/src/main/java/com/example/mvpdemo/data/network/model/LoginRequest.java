package com.example.mvpdemo.data.network.model;

import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginRequest{

	@SerializedName("password")
	private String password;

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@SerializedName("email")
	private String email;

	public String getPassword(){
		return password;
	}

	public String getEmail(){
		return email;
	}
	@Override
	public String toString(){
		return
				"LoginPageRequest{" +
						"password = '" + password + '\'' +
						",email = '" + email + '\'' +
						"}";
	}
	public JSONObject toJSON(){
		JSONObject jsonObject= new JSONObject();
		try {
			jsonObject.put("email", getEmail());
			jsonObject.put("password", getPassword());

			return jsonObject;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}