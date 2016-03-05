using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;
using ServerServiceLayer;
using ServerBusinessLogic;

namespace GUI
{
    /// <summary>
    /// Interaction logic for clientWindow.xaml
    /// </summary>
    public partial class clientMenu : Window
    {

        LoggedInUser user = null;

        private void Button_Click_4(object sender, RoutedEventArgs e)
        {

            var win = new Login();
            win.Show(); 
            Close();
        }
        public clientMenu(LoggedInUser u)
        {
            InitializeComponent();
            user = u;
            header.Content = header.Content + user.getName();

        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            ChangePassword ChangePW = new ChangePassword(user);
            ChangePW.Show();
        }

        private void Button_Click_1(object sender, RoutedEventArgs e)
        {
            MyCoupons CouponWindow = new MyCoupons(user);
            CouponWindow.Show();
            Close();
        }

        private void Button_Click_2(object sender, RoutedEventArgs e)
        {
            CouponSearch CSearch = new CouponSearch(user);
            CSearch.Show();
            Close();
        }

        private void Button_Click_3(object sender, RoutedEventArgs e)
        {
            BusinessSearch BSearch = new BusinessSearch(user);
            BSearch.Show();
            Close();
        }
    }
}