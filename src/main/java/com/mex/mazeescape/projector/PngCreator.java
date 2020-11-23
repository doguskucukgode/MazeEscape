package com.mex.mazeescape.projector;

import com.mex.mazeescape.config.AppConfig;
import com.mex.mazeescape.model.ImageIndex;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PngCreator {

    private BufferedImage bufferedImage;
    private List<ImageIndex> imageIndexList;

    public PngCreator(BufferedImage bufferedImage, List<ImageIndex> imageIndexList) {
        this.bufferedImage = bufferedImage;
        this.imageIndexList = imageIndexList;
    }

    public void exportImage() throws IOException {
        Graphics2D g = bufferedImage.createGraphics();
        Map<RenderingHints.Key, Object> map = new HashMap<>();
        map.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        map.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        map.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        RenderingHints renderHints = new RenderingHints(map);
        g.setRenderingHints(renderHints);
        /*
        for (ImageIndex imageIndex: imageIndexList) {
            g.setColor(Color.red);
            g.drawRect((imageIndex.getImageStartY() + imageIndex.getImageEndY()) /2,
                    (imageIndex.getImageStartX() + imageIndex.getImageEndX()) /2, 2,2);
        }
         */

        for (int i = 0; i+1 < imageIndexList.size(); i=i+2) {
            ImageIndex startIndex = imageIndexList.get(i);
            ImageIndex endIndex = imageIndexList.get(i+1);
            LineArrow lineArrow = new LineArrow((startIndex.getImageStartY() + startIndex.getImageEndY()) /2,
                    (startIndex.getImageStartX() + startIndex.getImageEndX()) /2,
                    (endIndex.getImageStartY() + endIndex.getImageEndY()) /2,
                    (endIndex.getImageStartX() + endIndex.getImageEndX()) /2, Color.BLACK, 1);
            lineArrow.draw(g);
        }
        ImageIO.write(bufferedImage, "PNG", new File(AppConfig.OUTPUT_FILE));
    }

    public static void drawCenteredCircle(Graphics2D g, int x, int y, int r) {
        x = x-(r/2);
        y = y-(r/2);
        g.fillOval(x,y,r,r);
    }
}
