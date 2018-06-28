package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import Algorithm.Processor;

public class main 
{
	private static Scanner reader;
	private static Processor processor = new Processor();
	
	public static void main( String args[] )
	{
		reader = new Scanner( System.in );
		int choice = askForUserInput();
		String bestPath = "";
		switch( choice ) 
		{
			case 1: 
				bestPath = readCLI();
				break;
			case 2: 
				bestPath = readFile();
				break;
			default: 
				System.out.println( "Exiting.." );
				break;
		}
		System.out.println( bestPath );
		reader.close();
	}
	
	public static int askForUserInput()
	{
		// Give user selection of input
		System.out.println( "MINIMUM/MAXMIMUM TRIANGLE PATHS!" );
		System.out.println( "Choose:" );
		System.out.println( "1) Input through CLI" );
		System.out.println( "2) Input through file" );
		System.out.println( "3) Exit" );
		
		// Read input
		int choice = reader.nextInt();
		reader.nextLine();
		
		// Return user choice
		return choice;
	}
	
	public static String readCLI()
	{
		//Read user input in string
		String userInput = "";
		
		// Change user input to upper case to match EOF even if lower case
		while( true )
		{
			userInput = reader.nextLine();
			if( userInput.toUpperCase().equals( "EOF" ) )
			{
				break;
			}
			// split spaces... such that "6 3" -> [6,3]
			processor.processNodes( userInput.split( "\\s+" ) );
		}
		
		// Return answer
		return processor.getBestPath();
	}
	
	public static String readFile()
	{
		//Read user input in string
		System.out.println( "Enter path of file:" );
		String file = reader.next();
		
		// Check if file exists
		File f = new File( file );
		if( f.exists() && !f.isDirectory() )
		{
			try
			{
				FileReader fr = new FileReader( f );
				BufferedReader br = new BufferedReader( fr );
				String line;
				
				while( ( line = br.readLine() ) != null )
				{
					processor.processNodes( line.split( "\\s+" ) );
				}
				
				// Close all readers
				fr.close();
				br.close();
			}
			catch( IOException e ) 
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println( "File provided is not valid! Exiting.." );
		}
		
		// Return answer
		return processor.getBestPath();
	}
}
