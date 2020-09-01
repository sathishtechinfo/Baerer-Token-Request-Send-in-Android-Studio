package com.example.mvpdemo.data.network.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse{

	@SerializedName("website_link")
	private Object websiteLink;

	@SerializedName("image")
	private Object image;

	@SerializedName("block_status")
	private int blockStatus;

	@SerializedName("device_id")
	private String deviceId;

	@SerializedName("mobile")
	private String mobile;

	@SerializedName("last_name")
	private String lastName;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("device_type")
	private String deviceType;

	@SerializedName("email_verified_at")
	private Object emailVerifiedAt;

	@SerializedName("token")
	private String token;

	@SerializedName("youtube_link")
	private Object youtubeLink;

	@SerializedName("country_code")
	private String countryCode;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("user_bio")
	private Object userBio;

	@SerializedName("device_token")
	private String deviceToken;

	@SerializedName("instagram_link")
	private Object instagramLink;

	@SerializedName("id")
	private int id;

	@SerializedName("first_name")
	private String firstName;

	@SerializedName("email")
	private String email;

	public Object getWebsiteLink(){
		return websiteLink;
	}

	public Object getImage(){
		return image;
	}

	public int getBlockStatus(){
		return blockStatus;
	}

	public String getDeviceId(){
		return deviceId;
	}

	public String getMobile(){
		return mobile;
	}

	public String getLastName(){
		return lastName;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public String getDeviceType(){
		return deviceType;
	}

	public Object getEmailVerifiedAt(){
		return emailVerifiedAt;
	}

	public String getToken(){
		return token;
	}

	public Object getYoutubeLink(){
		return youtubeLink;
	}

	public String getCountryCode(){
		return countryCode;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public Object getUserBio(){
		return userBio;
	}

	public String getDeviceToken(){
		return deviceToken;
	}

	public Object getInstagramLink(){
		return instagramLink;
	}

	public int getId(){
		return id;
	}

	public String getFirstName(){
		return firstName;
	}

	public String getEmail(){
		return email;
	}
}