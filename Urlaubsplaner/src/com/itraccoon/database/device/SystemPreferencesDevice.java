package com.itraccoon.database.device;

import com.itraccoon.constants.Constants;

public class SystemPreferencesDevice {
	public static String getSetInitialBeroValues() {
		return "INSERT INTO SYSTEMPREFERENCES"
				+ "(SYST_BERO_HIGH, SYST_BERO_MID, SYST_BERO_LOW)"
				+ "VALUES"
				+ "('" + Constants.BERO_HIGH + "','" + Constants.BERO_MID + "','" + Constants.BERO_LOW + "')";
	}

	public static String getSelectBeroHigh() {
		return "SELECT SYST_BERO_HIGH FROM SYSTEMPREFERENCES";
	}

	public static String getSelectBeroMid() {
		return "SELECT SYST_BERO_MID FROM SYSTEMPREFERENCES";
	}

	public static String getSelectBeroLow() {
		return "SELECT SYST_BERO_LOW FROM SYSTEMPREFERENCES";
	}

	public static String getSetBeroHigh() {
		return "UPDATE SYSTEMPREFERENCES SET SYST_BERO_HIGH = ?";
	}

	public static String getSetBeroMid() {
		return "UPDATE SYSTEMPREFERENCES SET SYST_BERO_MID = ?";
	}

	public static String getSetBeroLow() {
		return "UPDATE SYSTEMPREFERENCES SET SYST_BERO_LOW = ?";
	}

}
