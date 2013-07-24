package com.pronoiahealth.olhie.db.classes;

import java.util.Set;

import com.orientechnologies.orient.core.index.OIndex;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.metadata.schema.OProperty;
import com.orientechnologies.orient.core.metadata.schema.OSchema;
import com.orientechnologies.orient.core.metadata.schema.OType;

public class BookassetdescriptionCreate {

	public BookassetdescriptionCreate() {
	}

	public static void drop(OSchema schema) {
		OClass bookClass = schema.getClass("Bookassetdescription");
		if (bookClass != null) {
			// Indexes
			Set<OIndex<?>> idxs = bookClass.getIndexes();
			if (idxs != null && idxs.size() > 0) {
				for (OIndex<?> idx : idxs) {
					idx.delete();
				}
			}

			// class
			schema.dropClass("Bookassetdescription");
		}
	}

	public static OClass create(OSchema schema, OClass Bookasset) {
		// Create Book table
		OClass bookClass = schema.createClass("Bookassetdescription");

		// bookAssetList LINKLIST LINKLIST Bookasset
		OProperty prop = bookClass
				.createProperty("bookAssetList", OType.LINKLIST, Bookasset);
		// bookId STRING
		prop = bookClass.createProperty("bookId", OType.STRING);
		prop.setNotNull(true);
		// removedDate DATETIME
		prop = bookClass.createProperty("removedDate", OType.DATETIME);
		// removed BOOLEAN
		prop = bookClass.createProperty("removed", OType.BOOLEAN);
		prop.setNotNull(true);
		// createdDate DATETIME
		prop = bookClass.createProperty("createdDate", OType.DATETIME);
		prop.setNotNull(true);
		// description STRING 1 250
		prop = bookClass.createProperty("description", OType.STRING);
		prop.setMin("1");
		prop.setMax("250");
		prop.setNotNull(true);

		// return
		return bookClass;
	}
}
