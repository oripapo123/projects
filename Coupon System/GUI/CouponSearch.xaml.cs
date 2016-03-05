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
    /// Interaction logic for CouponSearch.xaml
    /// </summary>
    public partial class CouponSearch : Window
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
        public CouponSearch(LoggedInUser u)
        {
            InitializeComponent();
            user = u;
            if (user.getUserType() != 0)
                Order.Visibility = Visibility.Hidden;
            Order.IsEnabled = false;
            CouponGrid.ItemsSource = isl.generateCoupons(user);
            CouponGrid.Visibility = Visibility.Hidden;
            CouponGrid.SelectionMode = DataGridSelectionMode.Single;
            CouponGrid.IsReadOnly = true;
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            Order.IsEnabled = false;
            if (isl.checkCouponSearchTerms(CatID.Text, SNumber.Text, Rating.Text, MinPrice.Text, MaxPrice.Text, Cat.Text))
                CouponGrid.ItemsSource = isl.SearchCoupons(CatID.Text, SNumber.Text, Rating.Text, MinPrice.Text, MaxPrice.Text, Cat.Text);
            else
                MessageBox.Show("Invalid input on search. Please try again.");
            if (CouponGrid.Columns.Count > 10)
            {
                CouponGrid.Columns.RemoveAt(11);
                CouponGrid.Columns.RemoveAt(10);
                CouponGrid.Columns.RemoveAt(2);
            }
            CouponGrid.Visibility = Visibility.Visible;

        }

        private void ComboBox_Loaded(object sender, RoutedEventArgs e)
        {
            // ... A List.
            List<string> data = new List<string>();
            data.Add("No Rating");
            data.Add("1");
            data.Add("2");
            data.Add("3");
            data.Add("4");
            data.Add("5");

            // ... Get the ComboBox reference.
            var comboBox = sender as ComboBox;

            // ... Assign the ItemsSource to the List.
            comboBox.ItemsSource = data;

            // ... Make the first item selected.
            comboBox.SelectedIndex = 0;
        }

        private void ComboBox_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            // ... Get the ComboBox.
            var comboBox = sender as ComboBox;

            // ... Set SelectedItem as Window Title.
            string value = comboBox.SelectedItem as string;
        }

        private void Order_Click(object sender, RoutedEventArgs e)
        {
            String msg = "";
            Boolean okay = true;
            if (CouponGrid.SelectedItem.GetType() == typeof(DataBase.Coupon))
                okay = isl.OrderCoupon((Coupon)CouponGrid.SelectedItem, user);
            else
               msg = "Please select a real coupon.";
            if (!okay)
                msg = "Coupon could not be ordered. Please try again later.";
            else
                msg = "Coupon ordered!";
            MessageBox.Show(msg);
        }

        private void CouponGrid_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            Order.IsEnabled = true;
        }

        private void GPS_Click(object sender, RoutedEventArgs e)
        {
            ISensors sensor = new Sensors();
            try
            {
                string address = sensor.getAdressFromGPS();
                MessageBox.Show(address);
           }
            catch (Exception exe)
            {
                MessageBox.Show(exe.Message);
            }
        }
    }
}
