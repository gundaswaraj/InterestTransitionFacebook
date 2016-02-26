import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.codehaus.jackson.util.BufferRecycler;

public class Confidence {
	public static String path = "/Users/Swaraj/Desktop/asmt.txt";

	public static int readLines() throws IOException {
		FileReader frr = new FileReader(path);
		BufferedReader brr = new BufferedReader(frr);

		String aLine;
		int numberOfLines = 0;

		while ((aLine = brr.readLine()) != null) {
			numberOfLines++;
		}
		brr.close();
		return numberOfLines;

	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = null;
		String sCurrentLine;
		double a, b, x, y, z;

		br = new BufferedReader(new FileReader(path));
		String[] text = new String[readLines()];
		int i = 0;
		while ((sCurrentLine = br.readLine()) != null) {

			text[i] = sCurrentLine;
			i++;
		}
		Map<String, Integer> ip = new HashMap<String, Integer>();
		Map<String, Double> confidence = new TreeMap<String, Double>();
		for (int k = 0; k < text.length; k++) {
			String[] temp = text[k].split("\\t");
			ip.put(temp[0], Integer.parseInt(temp[1]));
		}

		for (String s : ip.keySet()) {
			if (s.length() == 3) {
				a = 0;
				b = 0;
				String[] temp2 = s.split(",");
				x = ip.get(s);
				y = ip.get(temp2[0]);
				z = ip.get(temp2[1]);
				a = x / z;
				b = x / y;
				confidence.put(temp2[0] + "," + temp2[1], a);
				confidence.put(temp2[1] + "," + temp2[0], b);
			}
			if (s.length() == 5) {
				String[] temp3 = s.split(",");
				String first = temp3[0];
				String second = temp3[1];
				String third = temp3[2];
				String S1 = second + "," + third;
				String S2 = first + "," + third;
				String S3 = first + "," + second;
				double _Total = ip.get(s);
				double _FS = ip.get(first + "," + second);
				double _ST = ip.get(second + "," + third);
				double _TF = ip.get(first + "," + third);

				double M = _Total / _FS;
				double N = _Total / _TF;
				double O = _Total / _ST;

				confidence.put(first + "," + second + "," + third, M);
				confidence.put(first + "," + third + "," + second, N);
				confidence.put(second + "," + third + "," + first, O);

				// confidence.put(first+","+second+","+third, (double)
				// (ip.get(first+","+second+","+third)/ip.get(first+","+second)));
				// confidence.put(first+","+third+","+second, (double)
				// (ip.get(first+","+second+","+third)/ip.get(first+","+third)));
				// confidence.put(second+","+third+","+first, (double)
				// (ip.get(first+","+second+","+third)/ip.get(second+","+third)));

				// confidence.put(S1+","+first, (double)
				// (ip.get(first+","+second+","+third)/ip.get(first)));
				// confidence.put(S2+","+second, (double)
				// (ip.get(first+","+second+","+third)/ip.get(second)));
				// confidence.put(S3+","+third, (double)
				// (ip.get(first+","+second+","+third)/ip.get(third)));

			}
		}
		String op = null;
		for (String s2 : confidence.keySet()) {
			String[] temp4 = s2.split(",");
			if (temp4.length == 2) {
				op = "Confidence(" + temp4[0] + "==>" + temp4[1] + ")";
			}
			if (temp4.length == 3) {
				op = "Confidence(" + temp4[0] + "," + temp4[1] + "==>"
						+ temp4[2] + ")";
			}

			System.out.println(op + ":=" + confidence.get(s2));
		}
	}
}
