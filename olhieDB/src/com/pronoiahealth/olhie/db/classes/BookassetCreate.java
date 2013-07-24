package com.pronoiahealth.olhie.db.classes;

import java.util.Set;

import com.orientechnologies.orient.core.index.OIndex;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.metadata.schema.OClass.INDEX_TYPE;
import com.orientechnologies.orient.core.metadata.schema.OProperty;
import com.orientechnologies.orient.core.metadata.schema.OSchema;
import com.orientechnologies.orient.core.metadata.schema.OType;

public class BookassetCreate {

	public BookassetCreate() {
	}

	public static void drop(OSchema schema) {
		OClass bookClass = schema.getClass("Bookasset");
		if (bookClass != null) {
			// Indexes
			Set<OIndex<?>> idxs = bookClass.getIndexes();
			if (idxs != null && idxs.size() > 0) {
				for (OIndex<?> idx : idxs) {
					idx.delete();
				}
			}

			// class
			schema.dropClass("Bookasset");
		}
	}

	public static OClass create(OSchema schema) {
		// Create Book table
		OClass bookClass = schema.createClass("Bookasset");

		// size LONG
		OProperty prop = bookClass.createProperty("size", OType.LONG);
		// contentType STRING 0 250
		prop = bookClass.createProperty("contentType", OType.STRING);
		prop.setMin("0");
		prop.setMax("250");
		// bookassetdescriptionId STRING
		prop = bookClass.createProperty("bookassetdescriptionId", OType.STRING);
		prop.setNotNull(true);
		// createdDate DATETIME
		prop = bookClass.createProperty("createdDate", OType.DATETIME);
		prop.setNotNull(true);
		// authorId STRING 6 20
		prop = bookClass.createProperty("authorId", OType.STRING);
		prop.setMin("6");
		prop.setMax("20");
		prop.setNotNull(true);
		// base64Data STRING
		prop = bookClass.createProperty("base64Data", OType.STRING);
		prop.setNotNull(true);
		// itemName STRING 1 250
		prop = bookClass.createProperty("itemName", OType.STRING);
		prop.setMin("1");
		prop.setMax("250");
		prop.setNotNull(true);
		// itemType STRING 1 50
		prop = bookClass.createProperty("itemType", OType.STRING);
		prop.setNotNull(true);
		prop.setMin("1");
		prop.setMax("50");

		// Idexes
		// authorId_Idx NOTUNIQUE authorId
		bookClass.createIndex("authorId_Idx", INDEX_TYPE.NOTUNIQUE, "authorId");

		return bookClass;
	}
}
