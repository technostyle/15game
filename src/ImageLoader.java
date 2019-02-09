package FifteenGamePackage;

import java.io.File;
import java.io.IOException;
import java.awt.Image;
import javax.imageio.ImageIO;

public class ImageLoader {

    static int N = MainFrame.GetDimension();

    static Image numberImage[] = new Image[N * N];

    public ImageLoader() {
        StringBuilder dirBuilder = new StringBuilder();
        dirBuilder.append("./shared/img").append(Integer.toString(N)).append("/");
        String dirName = dirBuilder.toString();

        for (int i = 0; i < N * N; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(dirName).append(Integer.toString(i + 1)).append(".png");
            String fName = sb.toString();
            try {
        		numberImage[i] = ImageIO.read(new File(fName)); 
        	}
    	    catch (IOException e) {
        		e.printStackTrace();
            }
        }
    }
}
