package com.phodu.govt.util.foodsecurity;

public class FoodSecurityCardMember {
	private String name;
	private String dob;
	private String uid;

	public FoodSecurityCardMember(String name, String dob, String uid) {
		super();
		this.name = name;
		this.dob = dob;
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public String getDob() {
		return dob;
	}

	public String getUid() {
		return uid;
	}

	public static FoodSecurityCardMember buildFromText(String text) {
		String parts[] = text.split(" ");
		String name = "NA", dob = "NA", uid = "NA";
		boolean isNameCompleted = false, isDateOfBirthDetected = false, isUidDetected = false;
		for (int i = 1; i < parts.length; i++) {
			String part = parts[i];
			if (!isNameCompleted && !Character.isDigit(part.trim().charAt(0))) {
				if (i == 1) {
					name = "";
				}
				name = name + " " + parts[i];
			} else {
				isNameCompleted = true;
			}

			if (isNameCompleted && !isUidDetected) {
				uid = parts[i];
				isUidDetected = true;
				continue;
			}

			if (isNameCompleted && isUidDetected && !isDateOfBirthDetected) {
				dob = parts[i];
				isDateOfBirthDetected = true;
				continue;
			}
		}
		FoodSecurityCardMember member = new FoodSecurityCardMember(name, dob, uid);
		return member;
	}

	@Override
	public String toString() {
		return name;
	}

}
