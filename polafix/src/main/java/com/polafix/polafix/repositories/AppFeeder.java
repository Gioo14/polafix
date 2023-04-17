package com.polafix.polafix.repositories;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.polafix.polafix.pojos.Serie;
import com.polafix.polafix.pojos.Subscription;
import com.polafix.polafix.pojos.Type;
import com.polafix.polafix.pojos.User;

@Component
public class AppFeeder implements CommandLineRunner{


    @Autowired
	protected UserRepository ur;
    @Autowired
    protected SerieRepository sr;

    @Override
    public void run(String... args) throws Exception {

        feedUsers();
        feedSeries();
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }

    private void feedUsers() {

        SimpleDateFormat dateParser = new SimpleDateFormat("dd-MM-yyyy");
		Date birth1 = null;
		try {
			birth1 = dateParser.parse("21-07-1999");
		} catch (ParseException e) {
			e.printStackTrace();
		}
        //String email, Subscription type, String IBAN, String name, String surname, Date dateOfBirth
		User u1 = new User("paperino@polafix.es", Subscription.NOTSUBSCRIBED, "0000111122223333", "Paperino", "De Paperis", birth1);
        
		//User u2 = new User("Lola","lola@carSharing.es");
		ur.save(u1);
		//ur.save(u2);
	}

    private void feedSeries(){
        //String idSerie, String name, Type type, String shortDescription
        Serie s1 = new Serie("0123", "Lost", Type.STANDARD, "A plane crashes and some people survives.What will they do? What is hidden on the island?");

        sr.save(s1);
    }
    
}
