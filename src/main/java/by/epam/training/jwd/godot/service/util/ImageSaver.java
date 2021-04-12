package by.epam.training.jwd.godot.service.util;

import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class ImageSaver {

    private static final int maxWidth = 400;
    private static final int maxHeight = 400;

    public ImageSaver(){}

    public String upload(Collection<Part> parts, String folderPath) throws IOException {
        String fileName = "";
        StringBuilder fullPath = new StringBuilder();
        fullPath.append(folderPath);
        for (Part part : parts) {
            fileName = part.getSubmittedFileName();
            fullPath.append(fileName);
            part.write(fullPath.toString());
        }
        resize(fullPath.toString());
        return fileName;
    }

    private void resize(String path) throws IOException {
        BufferedImage img = ImageIO.read(new File(path));
        Scalr.resize(img, maxWidth, maxHeight);
    }
}
