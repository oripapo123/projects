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
using DataBase;

namespace GUI
{

    public partial class BusinessCoupons : Window
    {

        ISL isl = new ISL();
        LoggedInUser user;
        
        private void Button_Click_1(object sender, RoutedEventArgs e)
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

        public BusinessCoupons(DataBase.Business business, LoggedInUser u)
        {
            user = u;
            this.business = business;
            InitializeComponent();
            BusinessGrid.ItemsSource = isl.generateCouponsForBusinesses(business);
            BusinessGrid.Visibility = Visibility.Hidden;
            BusinessGrid.IsReadOnly = true;
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            if (BusinessGrid.Columns.Count > 5)
            {
                BusinessGrid.Columns.RemoveAt(6);
                BusinessGrid.Columns.RemoveAt(5);
            }
            BusinessGrid.Visibility = Visibility.Visible;
        }
    }
}
