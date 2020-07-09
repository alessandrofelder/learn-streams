import LearnStreams.ComputeMeanGreyScaleOp;
import net.imagej.ops.AbstractOpTest;
import net.imglib2.Cursor;
import net.imglib2.img.Img;
import net.imglib2.img.array.ArrayImgs;
import net.imglib2.type.numeric.real.DoubleType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ComputeMeanGreyScaleOpTest extends AbstractOpTest {

    @Test
    public void testBackgroundMeanIsZero() {
        final Img<DoubleType> backgroundImage = getImageWithOnlyBackground();
        DoubleType backgroundMean = (DoubleType) ops.run(ComputeMeanGreyScaleOp.class, backgroundImage);
        assertEquals("Background image does not have mean 0", 0, backgroundMean.getRealDouble(),1e-12);
    }

    @Test
    public void testForegroundMeanIs255() {
        final Img<DoubleType> foregroundImage = getImageWithOnlyForeground();
        DoubleType foregroundMean = (DoubleType) ops.run(ComputeMeanGreyScaleOp.class, foregroundImage);
        assertEquals("Foreground image does not have mean 255", 255.0, foregroundMean.getRealDouble(),1e-12);
    }


    private static Img<DoubleType> getImageWithOnlyBackground() {
        final long[] imageDimensions = { 100, 100, 100 };
        final Img<DoubleType> backgroundImage = ArrayImgs.doubles(imageDimensions[0],
                imageDimensions[1], imageDimensions[2]);
        return backgroundImage;
    }

    private static Img<DoubleType> getImageWithOnlyForeground() {
        final long[] imageDimensions = { 100, 100, 100 };
        final Img<DoubleType> foregroundImage = ArrayImgs.doubles(imageDimensions[0],
                imageDimensions[1], imageDimensions[2]);
        Cursor<DoubleType> cursor = foregroundImage.cursor();
        while(cursor.hasNext())
        {
            cursor.fwd();
            cursor.get().setReal(255.0);
        }
        return foregroundImage;
    }
}
