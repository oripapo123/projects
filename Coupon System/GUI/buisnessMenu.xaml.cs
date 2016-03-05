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
using DataBase;
using ServerBusinessLogic;

namespace GUI
{
    /// <summary>
    /// Interaction logic for buisnessMenu.xaml
    /// </summary>
    public partial class buisnessMenu : Window
    {

        LoggedInUser user;
        
        private void Button_Click_2(object sender, RoutedEventArgs e)
        {
            var type = user.getUserType();
            if (type == 1)
            {
                var win = new buissnesOwnerMenu(user);
                win.Show();
            }
            if (type == 2)
            {
                var win = new systemManagerMenu(user);
                win.Show();
            }
            if (type == 0)
            {
                var win = new clientMenu(user);
                win.Show();
            }
            Close();
        }
        private DataBase.Business business;
        public buisnessMenu(DataBase.Business business, LoggedInUser u)
        {
            user = u;
            this.business = business;
            InitializeComponent();
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            BusinessCoupons BC = new BusinessCoupons(business, user);
            BC.Show();
            Close();
        }

        private void Button_Click_1(object sender, RoutedEventArgs e)
        {
            NewCouponWindow NCW = new NewCouponWindow(business, user);
            NCW.Show();
            Close();
        }
    }
}
