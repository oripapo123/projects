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
    /// Interaction logic for ChangePassword.xaml
    /// </summary>
    public partial class ChangePassword : Window
    {

        LoggedInUser user;

        private void Button_Click_1(object sender, RoutedEventArgs e)
        {
            Close();
        }
        public ChangePassword(LoggedInUser u)
        {
            InitializeComponent();
            user = u;
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            String msg = "";
            if (String.Compare(NewPW.Password, ConfirmNewPW.Password) != 0)
                msg = "Invalid Confirmaion on new password. Please try again.";
            else if (String.Compare(OldPW.Password, (user.getPassword()).Trim()) != 0)
                msg = "Invalid Confirmaion on old password. Please try again.";
            else if (NewPW.Password.Length < 7)
                msg = "New password must be atleast 7 characters.";
            else
            {
                if (user.changePassword(NewPW.Password))
                    msg = "password changed successfully.";
                else
                    msg = "There was a problem with changing the password. Please try again later.";

            }
            MessageBox.Show(msg);
            Close();
        }
    }
}
