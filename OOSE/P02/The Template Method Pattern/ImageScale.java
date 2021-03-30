public class ImageScale extends ImageModifier
{

    @Override
    protected int imageValueModifier(int value) {
        return value / 2;
    }
    
    @Override
    protected int pixelRetrieve(ImageData image, int x, int y, int newHeight) {
        return image.getPixel(x * 2, y * 2);
    }
    
}