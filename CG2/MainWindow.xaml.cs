using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Interop;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using static System.Net.Mime.MediaTypeNames;

namespace CG2
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }
        private BitmapSource Bitmap2BitmapImage(Bitmap bitmap)
        {
            BitmapSource i = Imaging.CreateBitmapSourceFromHBitmap(
                           bitmap.GetHbitmap(),
                           IntPtr.Zero,
                           Int32Rect.Empty,
                           BitmapSizeOptions.FromEmptyOptions());
            return i;
        }
        private void Button_Click(object sender, RoutedEventArgs e)
        {
            var uri = new Uri("C:\\Users\\Admin\\Downloads\\765.jpg");
            Bitmap img = new Bitmap("C:\\Users\\Admin\\Downloads\\765.jpg");
            image1.Source = Bitmap2BitmapImage(img);
            //System.Threading.Thread.Sleep(2000);
            var width = img.Width;
            var height = img.Height;
            Bitmap result = new Bitmap(width, height, System.Drawing.Imaging.PixelFormat.Format32bppPArgb);
            int a = 0;
            int r = 0;
            int g = 0;
            int b = 0;
            System.Drawing.Color pixel;
            double parameter = 200;
            double contrast = Math.Pow((100 + parameter) / 100, 2.0); 
            for (int x = 0; x < width; x++)
            {
                for (int y = 0; y < height; y++)
                {
                    pixel = img.GetPixel(x, y);
                    a = pixel.A;
                    r = pixel.R;
                    r = ((int)(((r / 255.0 - 0.5) * contrast) * 255.0));
                    if (r < 0)
                    {
                        r = 0;
                    }
                    else if (r > 255)
                    {
                        r = 255;
                    }
                    g = pixel.G;
                    g = ((int)(((g / 255.0 - 0.5) * contrast) * 255.0));
                    if (g < 0)
                    {
                        g = 0;
                    }
                    else if (g > 255)
                    {
                        g = 255;
                    }
                    b = pixel.B;
                    b = ((int)(((b / 255.0 - 0.5) * contrast) * 255.0));
                    if (b < 0)
                    {
                        b = 0;
                    }
                    else if (b > 255)
                    {
                        b = 255;
                    }
                    result.SetPixel(x, y, System.Drawing.Color.FromArgb((byte)a, (byte)r, (byte)g, (byte)b));
                }
            }
            image1.Source = Bitmap2BitmapImage(result);
        }

        private void Window_Loaded(object sender, RoutedEventArgs e)
        {
            var uri = new Uri("C:\\Users\\Admin\\Downloads\\765.jpg");
            Bitmap img = new Bitmap("C:\\Users\\Admin\\Downloads\\765.jpg");
            image1.Source = Bitmap2BitmapImage(img);
        }
    }
}
