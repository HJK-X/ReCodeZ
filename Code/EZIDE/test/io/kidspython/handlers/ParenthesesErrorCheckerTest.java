package io.kidspython.handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import io.kidspython.CodeFile;
import org.junit.*;

public class ParenthesesErrorCheckerTest {
	static String[] correctOutput = { "/Testing/sample1Output.txt", "/Testing/sample2Output.txt",
			"/Testing/sample3Output.txt", "/Testing/sample4Output.txt", "/Testing/sample5Output.txt",
			"/Testing/sample6Output.txt" };
	static String[] output = { "/Testing/sample1.py", "/Testing/sample2.py", "/Testing/sample3.py",
			"/Testing/sample4.py", "/Testing/sample5.py", "/Testing/sample6.py" };

	@Test
	public void testParenError1() throws FileNotFoundException, IOException {
		for (int i = 0; i < output.length; i++) {
			String filePath = System.getProperty("user.dir") + output[i];
			CodeFile file = new CodeFile(filePath);
			CodeFileTraverser fileTraverser = new CodeFileTraverser(file);
			ErrorChecker parentheses = new ParenthesesErrorChecker();
			ErrorChecker colons = new ColonErrorChecker();

			parentheses.setSuccessor(colons);

			fileTraverser.traverse(parentheses);

			String message = parentheses.getMessage();

			String correct = IOUtils
					.toString(new FileInputStream(new File(System.getProperty("user.dir") + correctOutput[i])));
			Assert.assertEquals(correct, message);

		}
	}
}
