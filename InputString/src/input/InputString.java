package input;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputString {
	static String CLOSE = "close";
	static String CLEAR_FILE="clear";
	String inputText;
	String outputFile = "File.txt";
	int currentLineLength=0;
	int desireLineLenght=15;
	

	public InputString(String outputFile, int desireLineLenght) {
		this.outputFile = outputFile;
		this.desireLineLenght = desireLineLenght;
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String outputFile = "File.txt";
		int desireLineLenght=15;
		InputString myInputString = new InputString(outputFile, desireLineLenght);
		myInputString.inputString();
		}
	
	protected void inputString() {
		BufferedWriter writer;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			do {
				writer = new BufferedWriter(new FileWriter(outputFile, true));
				do {
					try {
						inputText=reader.readLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						inputText="Ошибка чтения ввода";
					}
				} while (inputText==null);
				if (inputText!=CLOSE&inputText!=CLEAR_FILE) {
					currentLineLength+=inputText.length();
				}
				//расставляем переносы строк
				textTransfer();
				
				//условия для записи в файл/отработки команд
				if(inputText.equalsIgnoreCase(CLOSE))writer.close();
				else if(inputText.equalsIgnoreCase(CLEAR_FILE)) {
					System.out.println("Начало очистки...");
					writer.close();
					clearFile(outputFile);
				}
				else if(!inputText.equalsIgnoreCase(CLOSE)) {
					System.out.println("Вы напечатали " + inputText);
					if (inputText.length()==0) {
						writer.write("\n");
						writer.close();
					}
					else if(inputText.charAt(inputText.length()-1)=='\n') {
						writer.write(inputText);
						writer.close();
						}
					else writer.write(inputText+" ");
					writer.close();
				}	
				
			} while(!inputText.equalsIgnoreCase(CLOSE));
		} catch (IOException e) {
			System.out.println("проблемы с записью в файл");
			inputText=CLOSE;	
		}
	}
	
	private void textTransfer() {
		String tempText=inputText;
		if (currentLineLength==desireLineLenght&!inputText.equalsIgnoreCase(CLOSE)
				&!inputText.equalsIgnoreCase(CLEAR_FILE)) {
			inputText=inputText+"\n";
			currentLineLength=0;
			
		}
		else if(inputText.equalsIgnoreCase("\n")) {
			currentLineLength=0;
		}
		else if (currentLineLength>desireLineLenght&!inputText.equalsIgnoreCase(CLOSE)
				&!inputText.equalsIgnoreCase(CLEAR_FILE)) {
			inputText=textTransferCurrentMoreDesire(tempText);
		}
	}
	private String textTransferCurrentMoreDesire(String tempText) {

		String firstSubstring;
		String secondSubstring;
		
		for (int i=(tempText.length()-(currentLineLength-desireLineLenght));
				i<tempText.length();i++) {
			if (i==tempText.length()-1) {
				if (tempText.charAt(i)==' ') {
					firstSubstring=tempText.substring(0, i);
					secondSubstring="\n";
					tempText=firstSubstring+secondSubstring;
					currentLineLength=0;
					return tempText;
				}
				else {
					firstSubstring=tempText;
					secondSubstring="\n";
					tempText=firstSubstring+secondSubstring;
					currentLineLength=0;
					return tempText;
				}
				
			}
			else {
				if (tempText.charAt(i)==' ') {
					firstSubstring=tempText.substring(0, i);
					
					if (tempText.substring(i+1).length()>desireLineLenght) {
						currentLineLength=tempText.substring(i+1).length();
						tempText = firstSubstring + "\n"+ textTransferCurrentMoreDesire(tempText.substring(i+1));
						return tempText;
					}
					else {
						secondSubstring="\n" + tempText.substring(i+1);
						tempText=firstSubstring+secondSubstring;
						currentLineLength=inputText.substring(i+1).length();
						return tempText;
					}
				}
			}
		}return tempText;
	}
	private static void clearFile(String outputFile) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
			System.out.println("Файл очищен.");
			writer.write("");
			writer.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
