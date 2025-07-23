package input;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputString {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String inputText;
		String close = "close";
		String clearFile="clear";
		String outputFile = "File.txt";
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter writer;
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
				
				if(inputText.equalsIgnoreCase(close))writer.close();
				else if(inputText.equalsIgnoreCase(clearFile)) {
					System.out.println("Начало чистки");
					writer.close();
					clearFile(outputFile);
				}
				else if(!inputText.equalsIgnoreCase(close)) {
					System.out.println("Вы напечатали " + inputText);
					writer.write(inputText+" ");
					writer.close();
				}
					
				
			} while(!inputText.equalsIgnoreCase(close));
		} catch (IOException e) {
			System.out.println("проблемы с записью в файл");
			inputText=close;	
		}
		}
		
	static BufferedWriter createWriter(String outputFile) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true));
		return writer;
	}
	static void clearFile(String outputFile) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
			System.out.println("чистка");
			writer.write("");
			writer.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
