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
    public partial class NewUserWindow : Window
    {

        ISL isl = new ISL();

        private void Button_Click_1(object sender, RoutedEventArgs e)
        {

            var win = new Login();
            win.Show(); 
            Close();
        }
        public NewUserWindow()
        {
            InitializeComponent();
        }

        private Boolean checkPWs(String password1, String password2)
        {
            return ((String.Compare(password1, password2) == 0) && (password1.Length >= 7));
        }

        private Boolean CheckEmail(String email)
        {
            Boolean ret = false;
            for (int i = 1; i < email.Length-1; i++)
            {
                if (email[i] == '@')
                {
                    ret = true;
                }
            }
            return ret;
        }

        private Boolean checkInput(String username, String FName, String LName, String Password, String Retype, String Email, String PNumber)
        {
            Boolean correct = true;
            if ((username == "") || (FName == "") || (LName == "") || (Email == "") ||
                (isl.hasNumbers(FName)) || (isl.hasNumbers(LName)) || 
                (!checkPWs(Password, Retype)) || (!isl.IsDigitsOnly(PNumber)) || (!CheckEmail(Email)))
                    correct = false;

            return correct;
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            DateTime DateOfBirth = DateTime.Now;
            String msg = "";
            Boolean correct = checkInput(UserName.Text, FirstName.Text, LastName.Text, PWord.Password, Retype.Password, Email.Text, PNumber.Text);
            try
            {
                DateOfBirth = (DateTime)DoB.SelectedDate;
            }
            catch
            {
                MessageBox.Show("Please pick a date");
            }
            if ((correct) && (DateOfBirth < DateTime.Now))
            {
                Boolean exist = isl.TryAddNewUser(UserName.Text, Email.Text);
                if (exist)
                    msg = "Username and/or Email already exist. Please try again.";
                else
                {

                    isl.addClient(UserName.Text, FirstName.Text, LastName.Text, PWord.Password, Email.Text, PNumber.Text, DateOfBirth, Gender.Text);
                    msg = "Username created successfully.";
                    Login login = new Login();
                    login.Show();
                    Close();
                }
            }
            else
                msg = "Invalid parameters. Try again.";
            MessageBox.Show(msg);
                
        }
        private void ComboBox_Loaded(object sender, RoutedEventArgs e)
        {
            // ... A List.
            List<string> data = new List<string>();
            data.Add("Male");
            data.Add("Female");

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
    }
}
