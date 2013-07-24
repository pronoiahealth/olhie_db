package com.pronoiahealth.olhie.db.classes;

import java.util.Set;

import com.orientechnologies.orient.core.index.OIndex;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.metadata.schema.OClass.INDEX_TYPE;
import com.orientechnologies.orient.core.metadata.schema.OProperty;
import com.orientechnologies.orient.core.metadata.schema.OSchema;
import com.orientechnologies.orient.core.metadata.schema.OType;

public class CommentCreate {

	public CommentCreate() {
	}

	public static void drop(OSchema schema) {
		OClass oClass = schema.getClass("Comment");
		if (oClass != null) {
			// Indexes
			Set<OIndex<?>> idxs = oClass.getIndexes();
			if (idxs != null && idxs.size() > 0) {
				for (OIndex<?> idx : idxs) {
					idx.delete();
				}
			}

			// class
			schema.dropClass("Comment");
		}
	}

	public static OClass create(OSchema schema) {
		// Create Book table
		OClass oClass = schema.createClass("Comment");

		// comment STRING
		OProperty prop = oClass.createProperty("comment", OType.STRING);
		prop.setNotNull(true);
		// submittedDate DATETIME
		prop = oClass.createProperty("submittedDate", OType.DATETIME);
		prop.setNotNull(true);
		// eMailAddress STRING 0 60
		prop = oClass.createProperty("eMailAddress", OType.STRING);
		prop.setMin("0");
		prop.setMax("60");
		// userId STRING 6 20
		prop = oClass.createProperty("userId", OType.STRING);
		prop.setMin("6");
		prop.setMax("20");

		// Idexes
		// submittedDate_idx NOTUNIQUE submittedDate
		oClass.createIndex("submittedDate_idx", INDEX_TYPE.NOTUNIQUE,
				"submittedDate");

		return oClass;
	}

}
