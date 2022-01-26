import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import javax.imageio.ImageIO;
import java.io.PrintWriter;

public class RunLengthCode {

	private static String ComputeCode(int[][] imgArr) {
		// write your logic here
		String RLC = "";
		boolean open = false;
		boolean close = false;
		boolean fstart = false ;
		int start = 0;
		int end = 0;
		
		for (int i = 0; i < imgArr.length; i++) {
			for (int j = 0; j < imgArr[0].length; j++) {
				if (imgArr[i][j] == 1) {
					if (open == false) {
						RLC = RLC + "(" + String.valueOf(i);
						open = true;
						close = true;
					}
					if (fstart == false) {
						fstart = true;
						start = j;
					}
					if ((j < imgArr[0].length - 1 && imgArr[i][j + 1] == 0) || j == imgArr[0].length - 1) {
						end = j;
						RLC = RLC +" " + String.valueOf(start) +" "+ String.valueOf(end);
						start = 0;
						end = 0;
						fstart = false;
					}
				}
			}
			if (close == true)
			RLC = RLC + ")";
			open = false;
			close = false;

		}

		return RLC;
	}

	public static void main(String[] args) {
		// write image path
		String path = "D:/Desktop/binary_triangle.jpg";

		BufferedImage image = null;

		try {

			File input_image = new File(path);
			// Reading input image
			image = ImageIO.read(input_image);
			System.out.println("Reading complete.");
		}

		catch (IOException e) {
			System.out.println("error in reading image");
		}

		// convert image to 2D array
		int width = image.getWidth();
		int height = image.getHeight();
		int[][] imgArr = new int[height][width];
		Raster raster = image.getData();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				imgArr[i][j] = raster.getSample(j, i, 0);

			}
		}

		//Write file name
		String fileName = "Triangle_RLE.txt";

		//saving the file
		try{
			PrintWriter x = new PrintWriter(fileName);
			x.println(ComputeCode(imgArr));
			x.close();
			System.out.println("Done");

		}
		catch(FileNotFoundException e){
			System.out.println("error in creating file");
		}

		//System.out.println(ComputeCode(imgArr));

	}// main ends here

}// class ends here
