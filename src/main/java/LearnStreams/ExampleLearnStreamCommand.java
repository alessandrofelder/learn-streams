package LearnStreams;

import net.imagej.Dataset;
import net.imagej.ImageJ;
import net.imagej.ImgPlus;
import net.imagej.ops.OpService;
import net.imglib2.img.Img;
import net.imglib2.type.numeric.RealType;
import org.scijava.ItemIO;
import org.scijava.command.Command;
import org.scijava.command.ContextCommand;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

import java.io.File;

@Plugin(type = Command.class, menuPath = "Plugins>Learn Streams>Example")
public class ExampleLearnStreamCommand<T extends RealType> extends ContextCommand {
    @Parameter
    private OpService opService;

    @Parameter
    private ImgPlus<T> inputImgPlus;

    @Parameter
    private double radius;

    @Parameter(type= ItemIO.OUTPUT)
    private ImgPlus<T> outputImgPlus;

    @Override
    public void run() {
        outputImgPlus = new ImgPlus<T>((Img) opService.filter().gauss(inputImgPlus.getImg(), radius), inputImgPlus.getName()+"(filtered)");
    }

    /**
     * This main function serves for development purposes.
     * It allows you to run the plugin immediately out of
     * your integrated development environment (IDE).
     *
     * @param args whatever, it's ignored
     * @throws Exception
     */
    public static void main(final String... args) throws Exception {
        // create the ImageJ application context with all available services
        final ImageJ ij = new ImageJ();
        ij.ui().showUI();

        // ask the user for a file to open
        final File file = ij.ui().chooseFile(null, "open");

        if (file != null) {
            // load the dataset
            final Dataset dataset = ij.scifio().datasetIO().open(file.getPath());

            // show the image
            ij.ui().show(dataset);

            // invoke the plugin
            ij.command().run(ExampleLearnStreamCommand.class, true);
        }
    }
}