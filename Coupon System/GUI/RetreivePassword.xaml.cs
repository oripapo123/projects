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
    /// Interaction logic for RetreivePassword.xaml
    /// </summary>
    public partial class RetreivePassword : Window
    {

        ISL isl = new ISL();
        
        private void Button_Click_1(object sender, RoutedEventArgs e)
        {
 
            Close();
        }
        public RetreivePassword()
        {
            InitializeComponent();
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            Boolean sent = isl.TryForgotPassword(Email.Text);
            String msg = "";
            if (sent)
                msg = "Email sent successfully.";
            else
                msg = "No account was found with this Email. Please try again.";
            MessageBox.Show(msg);
            Close();
        }
    }
}
