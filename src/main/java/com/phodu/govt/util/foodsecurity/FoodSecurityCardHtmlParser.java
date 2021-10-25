package com.phodu.govt.util.foodsecurity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FoodSecurityCardHtmlParser {
	public static final String HEAD_OF_FAMILY_IDENTIFIER = "Name of Head of Household :";
	public static final String PATRIARCHY_IDENTIFIER = "Father/Husband Name :";
	public static final String UID_IDENTIFIER = "UID :";
	public static final String ADDRESS_IDENTIFIER = "Address :";
	public static final String FAMILY_START = "Family Members Details SNo Name UID DOB Photo";
	public static final String FAMILY_END = "Issued Date";
	String inputFileName;

	public FoodSecurityCardHtmlParser(String inputFileName) {
		this.inputFileName = inputFileName;

	}

	public List<FoodSecurityCard> parse() throws IOException {
		List<FoodSecurityCard> foodSecurityCardList = new ArrayList<FoodSecurityCard>();
		File htmlFile = new File(inputFileName);
		Document document = Jsoup.parse(htmlFile, null);
		Elements elements = document.getElementsByTag("body");
		Elements foodSecurityCards = elements.get(0).children();
		for (Element foodSecurityCard : foodSecurityCards) {
			Element foodSecurityCardDetail = foodSecurityCard.child(0);
			Elements foodSecurityCardDetailItems = foodSecurityCardDetail.children();
			FoodSecurityCard card = parseIndividual(foodSecurityCardDetailItems);
			if (card != null)
				foodSecurityCardList.add(card);
		}
		return foodSecurityCardList;
	}

	private FoodSecurityCard parseIndividual(Elements foodSecurityCardDetailItems) {
		int size = foodSecurityCardDetailItems.size();
		boolean uidFound = false;
		boolean headOfHouseHoldFound = false;
		boolean fatherNameFound = false;
		boolean addressFound = false;
		boolean familyStartFound = false;
		boolean familyEndFound = false;
		int familySize = 0;
		String headOfFamily = null, address = null, uid = null, fatherName = null;
		List<FoodSecurityCardMember> members = new ArrayList<FoodSecurityCardMember> ();

		for (int i = 0; i < size; i++) {
			Element element = foodSecurityCardDetailItems.get(i);
			String text = element.text();

			if (!uidFound && text.startsWith(UID_IDENTIFIER)) {
				uid = removePrefixFromText(text, UID_IDENTIFIER);
				if (uid != null) {
					uidFound = true;
				}
			} else if (!headOfHouseHoldFound && text.startsWith(HEAD_OF_FAMILY_IDENTIFIER)) {
				headOfFamily = removePrefixFromText(text, HEAD_OF_FAMILY_IDENTIFIER);
				if (headOfFamily != null) {
					headOfHouseHoldFound = true;
				}
			} else if (!fatherNameFound && text.startsWith(PATRIARCHY_IDENTIFIER)) {
				fatherName = removePrefixFromText(text, PATRIARCHY_IDENTIFIER);
				if (fatherName != null) {
					fatherNameFound = true;
				}
			} else if (!addressFound && text.startsWith(ADDRESS_IDENTIFIER)) {
				address = removePrefixFromText(text, ADDRESS_IDENTIFIER);
				if (address != null) {
					addressFound = true;
				}
			} else if (!familyStartFound && text.startsWith(FAMILY_START)) {
				familyStartFound = true;
			} else if (familyStartFound && !familyEndFound && text.startsWith(FAMILY_END)) {
				familyEndFound = true;
			} else if (familyStartFound && !familyEndFound) {
				FoodSecurityCardMember member = FoodSecurityCardMember.buildFromText(text);
				members.add(member);
				familySize++;
			}
		}
		FoodSecurityCard foodSecurityCard = new FoodSecurityCard(headOfFamily, fatherName, uid, address, familySize);
		for (FoodSecurityCardMember member : members) {
			foodSecurityCard.getFamilyMembers().add(member);
		}
		return foodSecurityCard;
	}

	private String removePrefixFromText(String text, String prefix) {
		int index = text.indexOf(prefix);
		if (index > -1) {
			index = index + prefix.length();
			return text.substring(index).trim();
		}
		return null;
	}

}
