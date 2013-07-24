package com.pronoiahealth.olhie.db.classes;

import java.util.Set;

import com.orientechnologies.orient.core.index.OIndex;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.metadata.schema.OProperty;
import com.orientechnologies.orient.core.metadata.schema.OSchema;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.metadata.schema.OClass.INDEX_TYPE;

public class LoggedInSessionCreate {

	public LoggedInSessionCreate() {
	}

	public static void drop(OSchema schema) {
		OClass oClass = schema.getClass("LoggedInSession");
		if (oClass != null) {
			// Indexes
			Set<OIndex<?>> idxs = oClass.getIndexes();
			if (idxs != null && idxs.size() > 0) {
				for (OIndex<?> idx : idxs) {
					idx.delete();
				}
			}

			// class
			schema.dropClass("LoggedInSession");
		}
	}

	public static OClass create(OSchema schema) {
		// Create
		OClass oClass = schema.createClass("LoggedInSession");

		// active BOOLEAN
		OProperty prop = oClass.createProperty("active", OType.BOOLEAN);
		prop.setNotNull(true);

		// sessionStartDT DATETIME
		prop = oClass.createProperty("sessionStartDT", OType.DATETIME);
		prop.setNotNull(true);

		// lookupName STRING 1 255
		prop = oClass.createProperty("lookupName", OType.STRING);
		prop.setMin("1");
		prop.setMax("255");
		prop.setNotNull(true);

		// userId STRING 6 20
		prop = oClass.createProperty("userId", OType.STRING);
		prop.setMin("6");
		prop.setMax("20");
		prop.setNotNull(true);
		
		// erraiSessionId STRING 1 255
		prop = oClass.createProperty("erraiSessionId", OType.STRING);
		prop.setMin("1");
		prop.setMax("255");
		prop.setNotNull(true);

		// sessionEndDT DATETIME
		prop = oClass.createProperty("sessionEndDT", OType.DATETIME);

		// Idexes
		// erraiSessionId_Idx NOTUNIQUE erraiSessionId
		oClass.createIndex("erraiSessionId_Idx", INDEX_TYPE.NOTUNIQUE,
				"erraiSessionId");

		return oClass;
	}
}
