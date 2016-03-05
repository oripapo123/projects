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
    /// Interaction logic for systemManagerMenu.xaml
    /// </summary>
    public partial class systemManagerMenu : Window
    {

        LoggedInUser user;
        
        private void Button_Click_7(object sender, RoutedEventArgs e)
        {
            var win = new Login();
            win.Show();
            Close();
        }
        public systemManagerMenu(LoggedInUser u)
        {
            user = u;
            InitializeComponent();
            header.Content = header.Content + user.getName();
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            ChangePassword ChangePW = new ChangePassword(user);
            ChangePW.Show();
        }

        private void Button_Click_1(object sender, RoutedEventArgs e)
        {
            NewBusinessOwner NBO = new NewBusinessOwner(user);
            NBO.Show();
            Close();
        }

        private void Button_Click_2(object sender, RoutedEventArgs e)
        {
            CouponSearch CS = new CouponSearch(user);
            CS.Show();
            Close();
        }

        private void Button_Click_3(object sender, RoutedEventArgs e)
        {
            NewCouponWindow NCW = new NewCouponWindow(user);
            NCW.Show();
            Close();
        }

        private void Button_Click_4(object sender, RoutedEventArgs e)
        {
            var child = new ApproveCoupon(user);
            child.Show();
            Close();
        }

        private void Button_Click_5(object sender, RoutedEventArgs e)
        {
            AddBusiness AD = new AddBusiness(user);
            AD.Show();
            Close();
        }

        private void Button_Click_6(object sender, RoutedEventArgs e)
        {
            EditBusiness ED = new EditBusiness(user);
            ED.Show();
            Close();
        }
    }
}
