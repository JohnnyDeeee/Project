package org.loderunner.table;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintStream;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Account {

	// Fields
	private static String USERNAME;
	private static String PASSWORD;
	private static int score = 0;
	private static final String DIR = System.getProperty("user.dir");
	private static File file = new File(DIR + "\\bin\\org\\loderunner\\" + "Accounts.txt");

	// Properties
	public static String getUsername() {
		return USERNAME;
	}

	public static String getPassword() {
		return PASSWORD;
	}

	public static void setScore(int amount) {
		score = amount;
	}

	public static int getScore() {
		return score;
	}

	// Constructor
	public Account(String username, String password) {
		USERNAME = username;
		PASSWORD = password;
	}

	// Methods
	public static void writeToFile(String username, String password) {
		try {
			String content = username + "," + password + "," + 0;

			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.append(content);
			bw.newLine();
			bw.close();
			JOptionPane
			.showMessageDialog(null, "Succesfully registered your account, you can now login.", "Succes!", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String[] readFromFile(String username) {
		String[] acInfo = new String[3];

		try {
			if (file.exists()) {
				Scanner sc = new Scanner(file);
				int lineNum = 0;

				while (sc.hasNextLine()) {
					String line = sc.nextLine();
					lineNum++;

					if (line.contains(username)) {
						String[] parts = line.split(",");
						acInfo[0] = parts[0];
						acInfo[1] = parts[1];
						acInfo[2] = parts[2];
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return acInfo;
	}

	public static boolean accountExists(String username) {
		String[] account = readFromFile(username);

		if (account[0] == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public static void replaceSelected(String username, String password, int newScore) {
		try {
			if (file.exists()) {
				BufferedReader br = new BufferedReader(new FileReader(file));
		        String line;
		        String output = "";
				String[] oldAccountInfo = Account.readFromFile(username);
				int oldScore = Integer.parseInt(oldAccountInfo[2]);

				while ((line = br.readLine()) != null) output += line + '\r'+'\n';

				if (output.contains(username) && newScore > oldScore) {
					output = output.replace(username + "," + password + "," + Integer.toString(oldScore), username + "," + password + "," + Integer.toString(newScore));
				}
				
				System.out.println("---------------------------" + '\n' + output + '\n' + "------------------------");//DEBUG

				FileOutputStream File = new FileOutputStream(file);
		        File.write(output.getBytes());
				
				/*
				FileWriter fw = new FileWriter(file.getAbsoluteFile(), false);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(output);
				bw.close(); 
				*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
