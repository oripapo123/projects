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
    /// Interaction logic for buissnesOwnerMenu.xaml
    /// </summary>
    public partial class buissnesOwnerMenu : Window
    {

        LoggedInUser user;
        
        private void Button_Click_3(object sender, RoutedEventArgs e)
        {

            var win = new Login();
            win.Show(); 
            Close();
        }
        public buissnesOwnerMenu(LoggedInUser u)
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
            CouponSearch CSearch = new CouponSearch(user);
            CSearch.Show();
            Close();
        }

        private void Button_Click_2(object sender, RoutedEventArgs e)
        {
            MyBusinesses businesses = new MyBusinesses(user);
            businesses.Show();
            Close();
        }
    }
}
