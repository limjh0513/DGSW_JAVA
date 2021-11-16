package kr.hs.dgsw.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CounterRepositoryWithFile implements CounterRepository {
	private File file;
	private final static String FILE_PATH = "counter.txt";

	public CounterRepositoryWithFile() {
		file = new File(FILE_PATH);
		System.out.println(file.getAbsolutePath());
	}

	@Override
	public void addCount() {
		int count = getCount();
		count++;
		
		try {
			writeToFile(count);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeToFile(int count) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write(count);
		
		writer.close();
	}

	@Override
	public int getCount() {
		int count = 0;
		try {
			String a = readFile();
			count = Integer.parseInt(a);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	private String readFile() throws Exception {
		if (file.exists()) {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			reader.close();
			return line;
		} else {
			return "0";
		}
	}

}
