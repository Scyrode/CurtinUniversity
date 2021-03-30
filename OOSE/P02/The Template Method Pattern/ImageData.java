public class ImageData
{

    private int[][] image;
    private int width;
    private int height;

    public ImageData(int inWidth, int inHeight)
    {

        image = new int[inHeight][inWidth];
        width = inWidth;
        height = inHeight;

    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setPixel(int x, int y, int value)
    {
        image[y][x] = value;
    }

    public int getPixel(int x, int y)
    {
        return image[y][x];
    }
    
}