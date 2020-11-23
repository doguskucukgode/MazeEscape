package com.mex.mazeescape.projector;

import com.mex.mazeescape.config.AppConfig;
import com.mex.mazeescape.model.ImageIndex;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PngCreator {

    private final BufferedImage bufferedImage;
    private final List<ImageIndex> imageIndexList;

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
}
