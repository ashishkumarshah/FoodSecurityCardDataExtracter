package com.phodu.govt.util.foodsecurity;

import java.util.ArrayList;
import java.util.List;

public class FoodSecurityCard {
	public String toString() {
		return headOfHouseHold;
	}

	private List<FoodSecurityCardMember> familyMembers = new ArrayList<FoodSecurityCardMember>(4);
	private String cardNumber;
	private String cardType;
	private String fpShopNumber;
	private String uid;
	private String headOfHouseHold;
	private String fatherHusbandName;
	private String address;
	private String mandal;
	private String District;
	private String gasConnection;
	private String lpgConsumerNumber;
	private int familySize = 0;

	public int getFamilySize() {
		return familySize;
	}

	public void setFamilySize(int familySize) {
		this.familySize = familySize;
	}

	public FoodSecurityCard(String headOfFamily, String fatherName, String uid, String address, int familySize) {
		this.headOfHouseHold = headOfFamily;
		this.fatherHusbandName = fatherName;
		this.uid = uid;
		this.address = address;
		this.familySize = familySize;
	}

	public List<FoodSecurityCardMember> getFamilyMembers() {
		return familyMembers;
	}

	public void addFamilyMember(FoodSecurityCardMember familyMember) {
		this.familyMembers.add(familyMember);
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getFpShopNumber() {
		return fpShopNumber;
	}

	public void setFpShopNumber(String fpShopNumber) {
		this.fpShopNumber = fpShopNumber;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getHeadOfHouseHold() {
		return headOfHouseHold;
	}

	public void setHeadOfHouseHold(String headOfHouseHold) {
		this.headOfHouseHold = headOfHouseHold;
	}

	public String getFatherHusbandName() {
		return fatherHusbandName;
	}

	public void setFatherHusbandName(String fatherHusbandName) {
		this.fatherHusbandName = fatherHusbandName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMandal() {
		return mandal;
	}

	public void setMandal(String mandal) {
		this.mandal = mandal;
	}

	public String getDistrict() {
		return District;
	}

	public void setDistrict(String district) {
		District = district;
	}

	public String getGasConnection() {
		return gasConnection;
	}

	public void setGasConnection(String gasConnection) {
		this.gasConnection = gasConnection;
	}

	public String getLpgConsumerNumber() {
		return lpgConsumerNumber;
	}

	public void setLpgConsumerNumber(String lpgConsumerNumber) {
		this.lpgConsumerNumber = lpgConsumerNumber;
	}
}
