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
using ServerBusinessLogic;
using ServerServiceLayer;

namespace GUI
{
    /// <summary>
    /// Interaction logic for MyCoupons.xaml
    /// </summary>
    public partial class MyCoupons : Window
    {
        LoggedInUser user;
        ISL isl = new ISL();

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
        public MyCoupons(LoggedInUser u)
        {
            InitializeComponent();
            user = u;
            CouponGrid.ItemsSource = isl.generateCoupons(user);
            CouponGrid.Visibility = Visibility.Hidden;
            CouponGrid.SelectionMode = DataGridSelectionMode.Single;
            CouponGrid.IsReadOnly = true;
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            if (CouponGrid.Columns.Count > 10)
            {
                CouponGrid.Columns.RemoveAt(11);
                CouponGrid.Columns.RemoveAt(10);
            }
            CouponGrid.Visibility = Visibility.Visible;
            
        }
    }
}
