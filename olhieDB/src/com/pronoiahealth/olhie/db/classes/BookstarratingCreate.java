package com.pronoiahealth.olhie.db.classes;

import java.util.Set;

import com.orientechnologies.orient.core.index.OIndex;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.metadata.schema.OProperty;
import com.orientechnologies.orient.core.metadata.schema.OSchema;
import com.orientechnologies.orient.core.metadata.schema.OType;

public class BookstarratingCreate {

	public BookstarratingCreate() {
	}

	public static void drop(OSchema schema) {
		OClass bookClass = schema.getClass("Bookstarrating");
		if (bookClass != null) {
			// Indexes
			Set<OIndex<?>> idxs = bookClass.getIndexes();
			if (idxs != null && idxs.size() > 0) {
				for (OIndex<?> idx : idxs) {
					idx.delete();
				}
			}

			// class
			schema.dropClass("Bookstarrating");
		}
	}

	public static OClass create(OSchema schema) {
		// Create Book table
		OClass bookClass = schema.createClass("Bookstarrating");

		// stars INTEGER
		OProperty prop = bookClass.createProperty("stars", OType.INTEGER);
		prop.setNotNull(true);
		// userId STRING 6 20
		prop = bookClass.createProperty("userId", OType.STRING);
		prop.setMin("6");
		prop.setMax("20");
		prop.setNotNull(true);
		// bookId STRING
		prop = bookClass.createProperty("bookId", OType.STRING);
		prop.setNotNull(true);

		// Idexes
		// bookId_Idx NOTUNIQUE bookId
		// bookClass.createIndex("bookId_Idx", INDEX_TYPE.NOTUNIQUE, "bookId");

		return bookClass;
	}

}
