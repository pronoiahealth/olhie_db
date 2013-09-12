package com.pronoiahealth.olhie.db.classes;

import java.util.Set;

import com.orientechnologies.orient.core.index.OIndex;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.metadata.schema.OClass.INDEX_TYPE;
import com.orientechnologies.orient.core.metadata.schema.OProperty;
import com.orientechnologies.orient.core.metadata.schema.OSchema;
import com.orientechnologies.orient.core.metadata.schema.OType;

public class BookCreate {

	public static OClass create(OSchema schema, OClass Bookassetdescription) {
		// Create Book table
		OClass bookClass = schema.createClass("Book");
		
		// lastUpdated DATETIME
		OProperty prop = bookClass
				.createProperty("lastUpdated", OType.DATETIME);
		prop.setNotNull(true);
		// logoFileName STRING 250
		prop = bookClass.createProperty("logoFileName", OType.STRING);
		prop.setMax("250");
		// base64LogoData STRING
		prop = bookClass.createProperty("base64LogoData", OType.STRING);
		// base64FrontCovver
		prop = bookClass.createProperty("base64FrontCover", OType.STRING);
		// base64BackCover
		prop = bookClass.createProperty("base64BackCover", OType.STRING);
		// bookDescriptions LINKLIST LINKLIST Bookassetdescription
		bookClass.createProperty("bookDescriptions", OType.LINKLIST, Bookassetdescription );
		// createdDate DATETIME
		prop = bookClass.createProperty("createdDate", OType.DATETIME);
		// authorId STRING 6 20
		prop = bookClass.createProperty("authorId", OType.STRING);
		prop.setMin("6");
		prop.setMax("20");
		prop.setNotNull(true);
		// coverName STRING 1 50
		prop = bookClass.createProperty("coverName", OType.STRING);
		prop.setMin("1");
		prop.setMax("50");
		prop.setNotNull(true);
		// category STRING 1 50
		prop = bookClass.createProperty("category", OType.STRING);
		prop.setMin("1");
		prop.setMax("50");
		prop.setNotNull(true);
		// keywords STRING 0 250
		prop = bookClass.createProperty("keywords", OType.STRING);
		prop.setMin("0");
		prop.setMax("250");
		// introduction STRING 10
		prop = bookClass.createProperty("introduction", OType.STRING);
		prop.setMin("10");
		prop.setNotNull(true);
		// bookTitle STRING 1 250
		prop = bookClass.createProperty("bookTitle", OType.STRING);
		prop.setMin("1");
		prop.setMax("250");
		prop.setNotNull(true);
		// active BOOLEAN
		prop = bookClass.createProperty("active", OType.BOOLEAN);
		prop.setNotNull(true);
		// actDate DATETIME
		prop = bookClass.createProperty("actDate", OType.DATETIME);
		// solrUpdate
		prop = bookClass.createProperty("solrUpdate", OType.DATETIME);
		
		// Create indexes
		bookClass.createIndex("bookTitle_Idx", INDEX_TYPE.NOTUNIQUE, "bookTitle");
		
		// return the book class
		return bookClass;
	}
	
	public static void drop(OSchema schema) {		
		OClass bookClass = schema.getClass("Book");
		if (bookClass != null) {
			// Indexes
			Set<OIndex<?>> idxs = bookClass.getIndexes();
			if (idxs != null && idxs.size() > 0) {
				for (OIndex<?> idx : idxs) {
					idx.delete();
				}
			}
			
			// class
			schema.dropClass("Book");
		}
	}

}
