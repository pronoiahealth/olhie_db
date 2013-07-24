package com.pronoiahealth.olhie.db.classes;

import java.util.Set;

import com.orientechnologies.orient.core.index.OIndex;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.metadata.schema.OClass.INDEX_TYPE;
import com.orientechnologies.orient.core.metadata.schema.OProperty;
import com.orientechnologies.orient.core.metadata.schema.OSchema;
import com.orientechnologies.orient.core.metadata.schema.OType;

public class UserCreate {

	public UserCreate() {
		// TODO Auto-generated constructor stub
	}

	public static void drop(OSchema schema) {
		OClass oClass = schema.getClass("User");
		if (oClass != null) {
			// Indexes
			Set<OIndex<?>> idxs = oClass.getIndexes();
			if (idxs != null && idxs.size() > 0) {
				for (OIndex<?> idx : idxs) {
					idx.delete();
				}
			}

			// class
			schema.dropClass("User");
		}
	}

	public static OClass create(OSchema schema) {
		// Create Book table
		OClass oClass = schema.createClass("User");

		// email STRING 5 250
		OProperty prop = oClass.createProperty("email", OType.STRING);
		prop.setMin("5");
		prop.setMax("255");
		prop.setNotNull(true);
		// role STRING 1 20
		prop = oClass.createProperty("role", OType.STRING);
		prop.setMin("1");
		prop.setMax("20");
		prop.setNotNull(true);
		// userId STRING 6 20
		prop = oClass.createProperty("userId", OType.STRING);
		prop.setMin("6");
		prop.setMax("20");
		prop.setNotNull(true);
		// firstName STRING 0 25
		prop = oClass.createProperty("firstName", OType.STRING);
		prop.setMin("0");
		prop.setMax("25");
		// lastName STRING 1 50
		prop = oClass.createProperty("lastName", OType.STRING);
		prop.setMin("1");
		prop.setMax("50");
		prop.setNotNull(true);
		// resetPwd BOOLEAN
		prop = oClass.createProperty("resetPwd", OType.BOOLEAN);
		prop.setNotNull(true);

		// Idexes
		// lastName_Idx NOTUNIQUE lastName
		oClass.createIndex("lastName_Idx", INDEX_TYPE.NOTUNIQUE, "lastName");
		// user_Idx UNIQUE userId
		oClass.createIndex("user_Idx", INDEX_TYPE.UNIQUE, "userId");

		return oClass;
	}
}
