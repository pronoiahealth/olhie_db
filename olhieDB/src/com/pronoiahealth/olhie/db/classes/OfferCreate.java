package com.pronoiahealth.olhie.db.classes;

import java.util.Set;

import com.orientechnologies.orient.core.index.OIndex;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.metadata.schema.OProperty;
import com.orientechnologies.orient.core.metadata.schema.OSchema;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.metadata.schema.OClass.INDEX_TYPE;

public class OfferCreate {

	public OfferCreate() {
	}

	public static void drop(OSchema schema) {
		OClass oClass = schema.getClass("Offer");
		if (oClass != null) {
			// Indexes
			Set<OIndex<?>> idxs = oClass.getIndexes();
			if (idxs != null && idxs.size() > 0) {
				for (OIndex<?> idx : idxs) {
					idx.delete();
				}
			}

			// class
			schema.dropClass("Offer");
		}
	}

	public static OClass create(OSchema schema) {
		// Create Book table
		OClass oClass = schema.createClass("Offer");

		// peerSessionId STRING 0 255
		OProperty prop = oClass.createProperty("peerSessionId", OType.STRING);
		prop.setMin("0");
		prop.setMax("255");
		// offererSessionId STRING 1 255
		prop = oClass.createProperty("offererSessionId", OType.STRING);
		prop.setMin("1");
		prop.setMax("255");
		prop.setNotNull(true);
		// expiredDT DATETIME
		prop = oClass.createProperty("expiredDT", OType.DATETIME);
		// createdDT DATE
		prop = oClass.createProperty("createdDT", OType.DATETIME);
		prop.setNotNull(true);
		// channelId STRING 1 255
		prop = oClass.createProperty("channelId", OType.STRING);
		prop.setMin("1");
		prop.setMax("255");
		prop.setNotNull(true);
		// offerType STRING 1 20
		prop = oClass.createProperty("offerType", OType.STRING);
		prop.setMin("1");
		prop.setMax("20");
		prop.setNotNull(true);
		// closedDT DATETIME
		prop = oClass.createProperty("closedDT", OType.DATETIME);
		// rejectedDT DATETIME
		prop = oClass.createProperty("rejectedDT", OType.DATETIME);
		// acceptedDT DATETIME
		prop = oClass.createProperty("acceptedDT", OType.DATETIME);
		// peerId STRING 6 20
		prop = oClass.createProperty("peerId", OType.STRING);
		prop.setMin("6");
		prop.setMax("20");
		prop.setNotNull(true);
		// offererId STRING 6 20
		prop = oClass.createProperty("offererId", OType.STRING);
		prop.setMin("6");
		prop.setMax("20");
		prop.setNotNull(true);

		// Idexes
		// channelIs_Idx UNIQUE channelId
		oClass.createIndex("channelIs_Idx", INDEX_TYPE.UNIQUE, "channelId");

		return oClass;
	}

}
