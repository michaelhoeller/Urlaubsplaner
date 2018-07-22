package com.itraccoon.filehandling;

import javax.xml.bind.JAXBException;

public class ImportUserlist {

	public ImportUserlist() {
		try {
			load();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	private void load() throws JAXBException {
		// JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);
		// Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		//
		// Users users = (Users) jaxbUnmarshaller.unmarshal(new
		// File(Constants.EXPORT_LOCATION));
		//
		// Runtime.getInstance().setUserlist(users.getUserList());
	}

}
