import java.io.*;
import java.util.*;

import java.awt.image.*;  //java.awt.image.BufferedImage
import javax.imageio.*;
import javax.imageio.stream.ImageOutputStream;

class SquashImage {

   public static void main(String[] args) throws IOException {
   
      File squashThis = new File("face.jpg");
      File squashedImage = new File("compressface.jpg");

      BufferedImage bufferedImage = ImageIO.read(squashThis); //by reading input image 
       
      OutputStream os =new FileOutputStream(squashedImage); 
      ImageOutputStream imageOS = ImageIO.createImageOutputStream(os);

      Iterator<ImageWriter> squashwriters =  ImageIO.getImageWritersByFormatName("jpg");
      ImageWriter swriter = (ImageWriter) squashwriters.next();
      swriter.setOutput(imageOS);

      ImageWriteParam instructor = swriter.getDefaultWriteParam();
      instructor.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
      instructor.setCompressionQuality(0.1f); 
      
      swriter.write(null, new IIOImage(bufferedImage, null, null), instructor);
       
      os.close();
      imageOS.close();
      swriter.dispose();

   }
}