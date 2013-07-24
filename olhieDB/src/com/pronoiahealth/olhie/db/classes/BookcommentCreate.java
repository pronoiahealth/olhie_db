package com.pronoiahealth.olhie.db.classes;

import java.util.Set;

import com.orientechnologies.orient.core.index.OIndex;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.metadata.schema.OProperty;
import com.orientechnologies.orient.core.metadata.schema.OSchema;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.metadata.schema.OClass.INDEX_TYPE;

public class BookcommentCreate {

	public BookcommentCreate() {
	}

	public static void drop(OSchema schema) {
		OClass bookClass = schema.getClass("Bookcomment");
		if (bookClass != null) {
			// Indexes
			Set<OIndex<?>> idxs = bookClass.getIndexes();
			if (idxs != null && idxs.size() > 0) {
				for (OIndex<?> idx : idxs) {
					idx.delete();
				}
			}

			// class
			schema.dropClass("Bookcomment");
		}
	}

	public static OClass create(OSchema schema) {
		// Create Book table
		OClass bookClass = schema.createClass("Bookcomment");

		// bookId STRING 2
		OProperty prop = bookClass.createProperty("bookId", OType.STRING);
		prop.setMin("2");
		prop.setNotNull(true);
		// authorId STRING 6 20
		prop = bookClass.createProperty("authorId", OType.STRING);
		prop.setMin("6");
		prop.setMax("20");
		prop.setNotNull(true);
		// createDT DATETIME
		prop = bookClass.createProperty("createDT", OType.DATETIME);
		prop.setNotNull(true);
		// comment STRING
		prop = bookClass.createProperty("comment", OType.STRING);
		prop.setNotNull(true);

		// Idexes
		// bookId_Idx NOTUNIQUE bookId
		bookClass.createIndex("bookId_Idx", INDEX_TYPE.NOTUNIQUE, "bookId");

		return bookClass;
	}

}
