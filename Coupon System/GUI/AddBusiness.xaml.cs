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
    /// Interaction logic for AddBusiness.xaml
    /// </summary>
    public partial class AddBusiness : Window
    {

        ISL isl = new ISL();
        LoggedInUser user;

        public AddBusiness(LoggedInUser u)
        {
            user = u;
            InitializeComponent();
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            String msg = "";
            Boolean correct = isl.checkBusinessInput(BusinessName.Text, Address.Text, City.Text, Category.Text);
            if (correct)
            {
                Boolean exist = isl.addBusiness(BusinessName.Text, Address.Text, City.Text, Description.Text, Category.Text);
                if (exist)
                    msg = "Business name already exist. Please try again.";
                else
                {
                    msg = "Business name created successfully.";
                    Close();
                }
            }
            else
                msg = "Invalid parameters. Try again.";
            MessageBox.Show(msg);

        }
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
            if (type == 0) {
                var win = new clientMenu(user);
                win.Show();
            }
            
            Close();
        }
    }
}
