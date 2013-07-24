package com.pronoiahealth.olhie.db.classes;

import java.util.Set;

import com.orientechnologies.orient.core.index.OIndex;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.metadata.schema.OProperty;
import com.orientechnologies.orient.core.metadata.schema.OSchema;
import com.orientechnologies.orient.core.metadata.schema.OType;

public class PasswordCreate {

	public PasswordCreate() {
	}

	public static void drop(OSchema schema) {
		OClass oClass = schema.getClass("Password");
		if (oClass != null) {
			// Indexes
			Set<OIndex<?>> idxs = oClass.getIndexes();
			if (idxs != null && idxs.size() > 0) {
				for (OIndex<?> idx : idxs) {
					idx.delete();
				}
			}

			// class
			schema.dropClass("Password");
		}
	}

	public static OClass create(OSchema schema) {
		// Create Book table
		OClass oClass = schema.createClass("Password");

		// pwdSalt STRING 1 250
		OProperty prop = oClass.createProperty("pwdSalt", OType.STRING);
		prop.setMin("1");
		prop.setMax("255");
		prop.setNotNull(true);
		// pwdDigest STRING 1 250
		prop = oClass.createProperty("pwdDigest", OType.STRING);
		prop.setMin("1");
		prop.setMax("250");
		prop.setNotNull(true);
		// userId STRING 6 20
		prop = oClass.createProperty("userId", OType.STRING);
		prop.setMin("6");
		prop.setMax("20");
		prop.setNotNull(true);

		// Idexes
		// channelIs_Idx UNIQUE channelId
		// oClass.createIndex("channelIs_Idx", INDEX_TYPE.UNIQUE, "channelId");

		return oClass;
	}

}
