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
    /// Interaction logic for NewCouponWindow.xaml
    /// </summary>
    public partial class NewCouponWindow : Window
    {
        LoggedInUser user;
        ISL isl = new ISL();

        private void Button_Click_3(object sender, RoutedEventArgs e)
        {
            var type =  user.getUserType();
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
        private Business business;

        public NewCouponWindow(LoggedInUser u)
        {
            InitializeComponent();
            user = u;
            ChooseBusiness.Visibility = Visibility.Visible;
            Businesses.Visibility = Visibility.Visible;
        }

        public NewCouponWindow(Business business, LoggedInUser u)
        {
            user = u;
            this.business = business;
            InitializeComponent();
        }

        private void ComboBox_Loaded(object sender, RoutedEventArgs e)
        {
            // ... A List.
            List<string> data = new List<string>();
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

        private Boolean checkInput(String cat, String CatID, String Name, String Desc, String OPrice, String DPrice, String SNumber)
        {
            Boolean correct = true;
            if ((cat == "") || (DPrice == "") || (!isl.IsDigitsOnly(cat)) ||
                (!isl.IsDigitsOnly(CatID)) || (!isl.IsDigitsOnly(OPrice)) ||
                (!isl.IsDigitsOnly(DPrice)) || (!isl.IsDigitsOnly(SNumber)) ||
                (SNumber == "") || (Name == "") || (CatID == "") || (OPrice == ""))
                correct = false;
            else if (int.Parse(DPrice) > int.Parse(OPrice))
                correct = false;
            return correct;
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            DateTime LastDate = DateTime.Now;
            String msg = "";
            Boolean correct = checkInput(cat.Text, CatID.Text, CName.Text, Desc.Text, OPrice.Text, DPrice.Text, SNumber.Text);
            try
            {
                LastDate = (DateTime)LD.SelectedDate;
            }
            catch
            {
                MessageBox.Show("Please pick a date");
            }
            if ((correct) && (LastDate > DateTime.Now))
            {
                Boolean exist = isl.TryAddNewCoupon(SNumber.Text);
                if (exist)
                    msg = "Serial Number is taken. Please Try Again";
                else
                {
                    business = (isl.SearchBusinesses("", "", Businesses.Text)).First();
                    isl.addCoupon(cat.Text, CatID.Text, CName.Text, Desc.Text, OPrice.Text, DPrice.Text, SNumber.Text, Rating.Text, LastDate, business);
                    msg = "Coupon created successfully.";
                }
            }
            else
                msg = "Invalid parameters. Try again.";
            MessageBox.Show(msg);

        }

        private void Businesses_Loaded(object sender, RoutedEventArgs e)
        {
            List<String> list = new List<String>();
            foreach (Business b in isl.generateBusinesses())
            {
                list.Add(b.business_name);
            }
            var comboBox = sender as ComboBox;

            // ... Assign the ItemsSource to the List.
            Businesses.ItemsSource = list;

            // ... Make the first item selected.
            Businesses.SelectedIndex = 0;
        }
    }
}
