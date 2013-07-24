package com.pronoiahealth.olhie.db;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.metadata.schema.OSchema;
import com.pronoiahealth.olhie.db.classes.BookCreate;
import com.pronoiahealth.olhie.db.classes.BookassetCreate;
import com.pronoiahealth.olhie.db.classes.BookassetdescriptionCreate;
import com.pronoiahealth.olhie.db.classes.BookcommentCreate;
import com.pronoiahealth.olhie.db.classes.BookstarratingCreate;
import com.pronoiahealth.olhie.db.classes.CommentCreate;
import com.pronoiahealth.olhie.db.classes.LoggedInSessionCreate;
import com.pronoiahealth.olhie.db.classes.NewsItemCreate;
import com.pronoiahealth.olhie.db.classes.OfferCreate;
import com.pronoiahealth.olhie.db.classes.PasswordCreate;
import com.pronoiahealth.olhie.db.classes.UserBookRelationshipCreate;
import com.pronoiahealth.olhie.db.classes.UserCreate;

/**
 * DBCreate.java<br/>
 * Responsibilities:<br/>
 * 1. Assumes the database server is running and the olhie database is present
 * 
 * @author John DeStefano
 * @version 1.0
 * @since Jul 18, 2013
 * 
 */
public class DBCreate {
	private ODatabaseDocumentTx db;

	public DBCreate() {
		super();

		// Create database
		db = new ODatabaseDocumentTx("remote:localhost/olhie");
		if (db.isClosed()) {
			db.open("admin", "admin");
		}

		// Get the schema
		final OSchema schema = db.getMetadata().getSchema();

		// Bookasset
		BookassetCreate.drop(schema);
		OClass bookAsset = BookassetCreate.create(schema);

		// Bookassetdescription
		BookassetdescriptionCreate.drop(schema);
		OClass bookAssetDescription = BookassetdescriptionCreate.create(schema,
				bookAsset);

		// Book
		BookCreate.drop(schema);
		OClass book = BookCreate.create(schema, bookAssetDescription);

		// Bookcomment
		BookcommentCreate.drop(schema);
		BookcommentCreate.create(schema);

		// Bookstarrating
		BookstarratingCreate.drop(schema);
		BookstarratingCreate.create(schema);

		// Comment
		CommentCreate.drop(schema);
		CommentCreate.create(schema);

		// NewsItem
		NewsItemCreate.drop(schema);
		NewsItemCreate.create(schema);

		// Offer
		OfferCreate.drop(schema);
		OfferCreate.create(schema);

		// Password
		PasswordCreate.drop(schema);
		PasswordCreate.create(schema);

		// User
		UserCreate.drop(schema);
		OClass user = UserCreate.create(schema);

		// UserBookRelationship
		UserBookRelationshipCreate.drop(schema);
		UserBookRelationshipCreate.create(schema, user, book);
		
		// LoggedInSessionCreate
		LoggedInSessionCreate.drop(schema);
		LoggedInSessionCreate.create(schema);
	}

	public static void main(String[] args) {
		try {
			new DBCreate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
