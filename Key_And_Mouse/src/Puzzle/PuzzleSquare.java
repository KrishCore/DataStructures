package Puzzle;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PuzzleSquare
{
    private BufferedImage[][] bufferedImages = new BufferedImage[4][4];

    public PuzzleSquare(String path) throws IOException {
        BufferedImage img = ImageIO.read(new File(path));

        int size = Math.min(img.getWidth(), img.getHeight());
        int tile = size/4;

        for (int r = 0; r < 4; r++)
            for (int c = 0; c < 4; c++)
                bufferedImages[r][c] = img.getSubimage(c * tile, r * tile, tile, tile);
    }

    public BufferedImage getPortion(int r, int c)
    {
        return bufferedImages[r][c];
    }
}
