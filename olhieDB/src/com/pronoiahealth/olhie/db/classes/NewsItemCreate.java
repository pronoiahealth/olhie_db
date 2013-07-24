package com.pronoiahealth.olhie.db.classes;

import java.util.Set;

import com.orientechnologies.orient.core.index.OIndex;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.metadata.schema.OProperty;
import com.orientechnologies.orient.core.metadata.schema.OSchema;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.metadata.schema.OClass.INDEX_TYPE;

public class NewsItemCreate {

	public NewsItemCreate() {
	}

	public static void drop(OSchema schema) {
		OClass oClass = schema.getClass("NewsItem");
		if (oClass != null) {
			// Indexes
			Set<OIndex<?>> idxs = oClass.getIndexes();
			if (idxs != null && idxs.size() > 0) {
				for (OIndex<?> idx : idxs) {
					idx.delete();
				}
			}

			// class
			schema.dropClass("NewsItem");
		}
	}

	public static OClass create(OSchema schema) {
		// Create Book table
		OClass oClass = schema.createClass("NewsItem");

		// datePublished DATETIME
		OProperty prop = oClass.createProperty("datePublished", OType.DATETIME);
		prop.setNotNull(true);
		// active BOOLEAN
		prop = oClass.createProperty("active", OType.BOOLEAN);
		prop.setNotNull(true);
		// story STRING 1 500
		prop = oClass.createProperty("story", OType.STRING);
		prop.setNotNull(true);
		// href STRING 0 250
		prop = oClass.createProperty("href", OType.STRING);
		prop.setMin("0");
		prop.setMax("250");
		// title STRING 1 250
		prop = oClass.createProperty("title", OType.STRING);
		prop.setMin("1");
		prop.setMax("250");
		prop.setNotNull(true);
		// bookTitle STRING 1 250
		prop = oClass.createProperty("bookTitle", OType.STRING);
		prop.setMin("1");
		prop.setMax("250");
		prop.setNotNull(true);
		// introduction STRING 10
		prop = oClass.createProperty("introduction", OType.STRING);
		prop.setMin("1");
		prop.setNotNull(true);
		// keywords STRING 0 250
		prop = oClass.createProperty("keywords", OType.STRING);
		prop.setMin("0");
		prop.setMax("250");
		// category STRING 1 50
		prop = oClass.createProperty("category", OType.STRING);
		prop.setMin("1");
		prop.setMax("250");
		prop.setNotNull(true);
		// coverName STRING 1 50
		prop = oClass.createProperty("coverName", OType.STRING);
		prop.setMin("1");
		prop.setMax("250");
		prop.setNotNull(true);
		// authorId STRING 6 20
		prop = oClass.createProperty("authorId", OType.STRING);
		prop.setMin("6");
		prop.setMax("20");
		prop.setNotNull(true);


		// Idexes
		// active_idx	NOTUNIQUE	active
		oClass.createIndex("active_idx", INDEX_TYPE.NOTUNIQUE,
				"active");
		// title_Idx	NOTUNIQUE	title
		oClass.createIndex("title_Idx", INDEX_TYPE.NOTUNIQUE,
				"title");

		return oClass;
	}

}
