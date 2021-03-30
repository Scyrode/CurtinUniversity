public abstract class ImageModifier
{

    protected abstract int imageValueModifier(int value);
    protected abstract int pixelRetrieve(ImageData image, int x, int y, int newHeight);
    
    ImageData modify(ImageData oldImage)
    {

        int newWidth = imageValueModifier(oldImage.getWidth());
        int newHeight = imageValueModifier(oldImage.getHeight());
        ImageData newImage = new ImageData(newWidth, newHeight);
        for (int y = 0; y < newHeight; y++)
        {
            for (int x = 0; x < newWidth; x++)
            {
                newImage.setPixel(x, y, pixelRetrieve(oldImage, x, y, newHeight));
            }
        }

        return newImage;
        
    }
    
}