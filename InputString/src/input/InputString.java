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
		String outputFile = "File.txt";
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
			
			do {
				do {
					try {
						inputText=reader.readLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						inputText=close;
					}
				} while (inputText==null);
				if(!inputText.equalsIgnoreCase(close)) {
					System.out.println("Вы напечатали " + inputText);
					writer.write(inputText+" ");
				}
			} while(!inputText.equalsIgnoreCase(close));
			writer.close();
		} catch (IOException e) {
			System.out.println("проблемы с записью в файл");
		}	
		
	}

}
