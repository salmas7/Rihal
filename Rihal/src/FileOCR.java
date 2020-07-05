/*
 * By Shaikha Al-Mashaykhi
 * Results of the the Current code:
 * [1] can browse and open a .pdf file and .csv
 * [2] extract the text from a pdf file page and writes the text in output.txt file.
 * [3] extract an image from a pdf file page and renders it and saves it in PNG format : p1.PNG
 * --- 
 * Functions that did not work
 * [4] extract text from the image inside the pdf or and from the new image file saved from the pdf.
 * ---
 * Functions not written/finished
 * [5]Formatting data read from the image file and writes them in console or new .txt file. 
 * ---
 * Other Issues not fixed
 * [6] debugging the ClassNotFound error for AsposeOCR class and onnxruntime
 * [7] classpath and sources issues
 * ---
 * Important Libraries imported for the task for:
 * [8] AsposeOCR, Pdfbox, JavaSwing, comminlogging, fontbox
 */
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;

public class FileOCR {

	public static void main(String[] args) throws IOException, ParseException {
		
		PDDocument pd = null;
		// Make a file chooser
		JFileChooser fc = new JFileChooser();
		
		BufferedWriter wr = null;
		FileReader fr = null; // Create a FileReader reference
		FileWriter fw = null; // Create a FileWriter reference

		//PDF file filter
		FileNameExtensionFilter pdfFile = new FileNameExtensionFilter("PDF Files", "pdf");
		fc.setFileFilter(pdfFile);
		
		//Show the open dialog		
		int returnVal = fc.showOpenDialog(new JFrame());

		if(returnVal == JFileChooser.APPROVE_OPTION) 
		{
			File selectedFile = fc.getSelectedFile();
			String outF = "C:/Users/Shaikha Al-Mashaykhi/eclipse-workspace/Rihal/output.txt"; //the path of the output.txt 
			
			
			try {
				//store the path of the file selected from JFileChooser as String
				String filePath = selectedFile.getCanonicalPath();
				
				//to print the file path on the console
				System.out.println(filePath);
				 
				//getSelectedFile() returns a File object.
				fr = new FileReader(fc.getSelectedFile());
				
				File f = new File(filePath);
				
				//load the document
				pd = PDDocument.load(f);
				
				//PDFRenderer Class
				PDFRenderer pr = new PDFRenderer(pd);
				//render the image from the pdf
				BufferedImage bi = pr.renderImage(1);
				//output image path
				ImageIO.write(bi, "PNG", new File("C:/Users/Shaikha Al-Mashaykhi/eclipse-workspace/Rihal/p1.PNG"));
				
				/*
				 * This part has error issues regarding AsposeOCR
				 */
//			   //use the new exported image file to exract text
//				String dataDir = Utils.getSharedDataDir(FileOCR.class);
//				String imagePath = dataDir + "p1.png";
//				//Create api instance
//				AsposeOCR api1 = new AsposeOCR();
//			 	// Recognize page by full path to file
//				String result = api.RecognizePage(imagePath);
//				System.out.println("Result: " + result);
//			
//				String api = new AsposeOCR().RecognizePage(ImageIO.read(new File(imagePath)));
//				//BufferedImage loaded = ImageIO.read(new File(imagePath));
//				String result = api;
//				System.out.println("Result BufferedImage: " + result);

				
				//This works for texts extraction from PDF (works on some files only)
				PDFTextStripper stripper = new PDFTextStripper();
				//int numpg = pd.getNumberOfPages();
		        stripper.setStartPage(2);
		        stripper.setEndPage(2); 
		        stripper.setAddMoreFormatting(true);
		         
		         //System.out.println(numpg);
		         wr = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outF)));
		         stripper.writeText(pd, wr);
		         //wr.write(stripper.getText(pd));
		          
		         
				// Create a FileWriter object : OutF is the path for the output file
				fw = new FileWriter(outF);
		        
				 			
				wr.flush();
				wr.close();
				fw.close();
				fr.close();
				pd.close(); 		

			}catch(FileNotFoundException e) {
				e.printStackTrace();
			}
					
	 } //end of if statement for file chooser
					
	}
 }//end of main class

