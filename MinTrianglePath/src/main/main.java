package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import Algorithm.Node;
import Algorithm.Processor;

public class main 
{
	private static Scanner reader;
	
	public static void main( String args[] )
	{
		reader = new Scanner( System.in );
		int choice = askForUserInput();
		String bestPath = "";
		switch( choice ) 
		{
			case 1: 
				bestPath = readCLI().toString();
				break;
			case 2: 
				bestPath = readFile().toString();
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
		
		// Return user choice
		return choice;
	}
	
	public static Node readCLI()
	{
		//Read user input in string
		String userInput = "";
		Processor processor = new Processor();
		
		// Change user input to upper case to match EOF even if lower case
		while( !userInput.toUpperCase().equals( "EOF" ) )
		{
			userInput = reader.next();
			processor.processNodes( userInput.split( "\\s+" ) );
		}
		
		// Return answer
		return processor.getBestNode();
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
					// Begin processing user input here
					System.out.println( line );
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
		return "";
	}
}
