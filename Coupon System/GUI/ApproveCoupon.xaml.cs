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
    /// <summary>
    /// Interaction logic for ApproveCoupon.xaml
    /// </summary>
    public partial class ApproveCoupon : Window
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
        public ApproveCoupon(LoggedInUser u)
        {
            InitializeComponent();
            user = u;
            CouponGrid.ItemsSource = isl.generateUnApprovedCoupons();
            CouponGrid.Visibility = Visibility.Hidden;
            CouponGrid.IsReadOnly = true;
            CouponGrid.SelectionMode = DataGridSelectionMode.Single;
            Approve.IsEnabled = false;
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            Approve.IsEnabled = false;
            if (CouponGrid.Columns.Count > 10)
            {
                CouponGrid.Columns.RemoveAt(11);
                CouponGrid.Columns.RemoveAt(10);
            }
            CouponGrid.Visibility = Visibility.Visible;
            ShowCoupons.IsEnabled = false;

        }

        private void Approve_Click(object sender, RoutedEventArgs e)
        {
            String msg = "";
            Boolean okay = true;
            if (CouponGrid.SelectedItem.GetType() == typeof(DataBase.Coupon))
                okay = isl.ApproveCoupon((Coupon)CouponGrid.SelectedItem);
            else
                msg = "Please select a real coupon.";
            if (!okay)
                msg = "Coupon could not be approved. Please try again later.";
            else
            {
                msg = "Coupon approved!";
            }
            MessageBox.Show(msg);
            Approve.IsEnabled = false;
        }

        private void CouponGrid_SelectionChanged_1(object sender, SelectionChangedEventArgs e)
        {
            if (CouponGrid.SelectedItem.GetType() == typeof(DataBase.Coupon))
                if ((((Coupon)CouponGrid.SelectedItem).isApproved == false))
                {
                    Approve.IsEnabled = true;
                }
                else
                    Approve.IsEnabled = false;

        }
    }
}