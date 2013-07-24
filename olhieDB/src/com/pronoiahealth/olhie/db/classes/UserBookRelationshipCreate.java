package com.pronoiahealth.olhie.db.classes;

import java.util.Set;

import com.orientechnologies.orient.core.index.OIndex;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.metadata.schema.OProperty;
import com.orientechnologies.orient.core.metadata.schema.OSchema;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.metadata.schema.OClass.INDEX_TYPE;

public class UserBookRelationshipCreate {

	public UserBookRelationshipCreate() {
	}

	public static void drop(OSchema schema) {
		OClass oClass = schema.getClass("UserBookRelationship");
		if (oClass != null) {
			// Indexes
			Set<OIndex<?>> idxs = oClass.getIndexes();
			if (idxs != null && idxs.size() > 0) {
				for (OIndex<?> idx : idxs) {
					idx.delete();
				}
			}

			// class
			schema.dropClass("UserBookRelationship");
		}
	}

	public static OClass create(OSchema schema, OClass user, OClass book) {
		// Create Book table
		OClass oClass = schema.createClass("UserBookRelationship");

		// inactiveDate DATETIME
		OProperty prop = oClass.createProperty("inactiveDate", OType.DATETIME);
		// effectiveDate DATETIME
		prop = oClass.createProperty("effectiveDate", OType.DATETIME);
		prop.setNotNull(true);
		// activeRelationship BOOLEAN
		prop = oClass.createProperty("activeRelationship", OType.BOOLEAN);
		prop.setNotNull(true);
		// userRelationship STRING 5 25
		prop = oClass.createProperty("userRelationship", OType.STRING);
		prop.setMin("5");
		prop.setMax("25");
		prop.setNotNull(true);
		// bookId STRING
		prop = oClass.createProperty("bookId", OType.STRING);
		prop.setNotNull(true);
		// userId STRING
		prop = oClass.createProperty("userId", OType.STRING);
		prop.setNotNull(true);
		// theUser LINK User
		prop = oClass.createProperty("theUser", OType.LINK, user);
		prop.setNotNull(true);
		// theBook LINK Book
		prop = oClass.createProperty("theBook", OType.LINK, book);
		prop.setNotNull(true);
		// lastViewedDate DATETIME
		prop = oClass.createProperty("lastViewedDate", OType.DATETIME);
		prop.setNotNull(true);
		
		// Idexes
		// userId_Idx NOTUNIQUE userId
		oClass.createIndex("userId_Idx", INDEX_TYPE.NOTUNIQUE, "userId");
		// bookId_Idx NOTUNIQUE bookId
		oClass.createIndex("bookId_Idx", INDEX_TYPE.NOTUNIQUE, "bookId");

		return oClass;
	}

}
