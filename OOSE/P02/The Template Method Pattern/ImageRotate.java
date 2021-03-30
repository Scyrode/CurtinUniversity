public class ImageRotate extends ImageModifier
{

    @Override
    protected int imageValueModifier(int value) {
        return value;
    }
    
    @Override
    protected int pixelRetrieve(ImageData image, int x, int y, int newHeight) {
        return image.getPixel(newHeight - y, x);
    }
    
}