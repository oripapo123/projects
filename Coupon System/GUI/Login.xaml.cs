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
using System.Windows.Navigation;
using System.Windows.Shapes;
using ServerBusinessLogic;
using ServerServiceLayer;

namespace GUI
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class Login : Window
    {

        ISL isl = new ISL();

        public Login()
        {
            InitializeComponent();
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            LoggedInUser user = null;
            String name = username.Text;
            String pw = password.Password;
            user = isl.TryLogin(name, pw);
            if (user != null)
            {
                switch (user.getUserType())
                {
                    case 0:
                        clientMenu ClientMenu = new clientMenu(user);
                        ClientMenu.Show();
                        break;
                    case 1:
                        buissnesOwnerMenu BusiMenu = new buissnesOwnerMenu(user);
                        BusiMenu.Show();
                        break;
                    case 2:
                        systemManagerMenu SysMenu = new systemManagerMenu(user);
                        SysMenu.Show();
                        break;  
                }
                Close();
            }
            else
            {
                MessageBox.Show("Invalid username/password");
            }
        }

         private void Button_Click_1(object sender, RoutedEventArgs e)
         {
             NewUserWindow newUser = new NewUserWindow();
             newUser.Show();
             Close();
         }

         private void Button_Click_2(object sender, RoutedEventArgs e)
         {
             RetreivePassword Retreive = new RetreivePassword();
             Retreive.Show();
         }
    }
}
