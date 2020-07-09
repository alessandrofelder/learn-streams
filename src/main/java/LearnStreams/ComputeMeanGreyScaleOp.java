package LearnStreams;

import net.imagej.ops.Op;
import net.imagej.ops.special.function.AbstractUnaryFunctionOp;
import net.imglib2.Cursor;
import net.imglib2.img.Img;
import net.imglib2.type.numeric.real.DoubleType;
import org.scijava.plugin.Plugin;

import java.util.ArrayList;

@Plugin(name = "Get the mean grey scale value for the input image", type = Op.class)
public class ComputeMeanGreyScaleOp extends
        AbstractUnaryFunctionOp<Img, DoubleType> {

    @Override
    public DoubleType calculate(Img img) {
        Cursor<DoubleType> cursor = img.cursor();
        // can we do this with streams?
        ArrayList<DoubleType> greyscaleValues = new ArrayList<>();
        while(cursor.hasNext()) {
            cursor.fwd();
            greyscaleValues.add(cursor.get());
        }
        return ops().stats().mean(greyscaleValues);
    }
}