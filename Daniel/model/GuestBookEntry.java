package model;

import java.util.Date;

public class GuestBookEntry {

	static int count = 0;
	
    int id;

    String name;

    String message;

    Date  date;
    
    public GuestBookEntry(String name, String message )
    {
    	super();
        this.id = count++;
        this.name = name;
        this.message = message;
        this.date = new Date();
    }

    
    public GuestBookEntry( int id, String name, String message )
    {
    	super();
        this.id = id;
        this.name = name;
        this.message = message;
        this.date = new Date();
    }
    
    public GuestBookEntry() {
    	super();
    	this.date = new Date();
    	this.id = count++;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId( Integer id )
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage( String message )
    {
        this.message = message;
    }

}