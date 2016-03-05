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
    /// Interaction logic for EditBusiness.xaml
    /// </summary>
    public partial class EditBusiness : Window
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
        public EditBusiness(LoggedInUser u)
        {
            InitializeComponent();
            user = u;
            BusinessGrid.ItemsSource = isl.generateBusinesses();
            BusinessGrid.Visibility = Visibility.Hidden;
            BusinessGrid.SelectionMode = DataGridSelectionMode.Single;
            Edit.IsEnabled = false;
            Remove.IsEnabled = false;
        }

        private void BusinessGrid_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            Edit.IsEnabled = true;
            Remove.IsEnabled = true;
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            BusinessGrid.Columns[0].IsReadOnly = true;
            if (BusinessGrid.Columns.Count > 5)
            {
                BusinessGrid.Columns.RemoveAt(6);
                BusinessGrid.Columns.RemoveAt(5);
            }
            BusinessGrid.Visibility = Visibility.Visible;
            ShowBusinesses.IsEnabled = false;
        }

        private void Edit_Click(object sender, RoutedEventArgs e)
        {
            String msg = "";
            Boolean okay = false;
            if (BusinessGrid.SelectedItem.GetType() == typeof(DataBase.Business))
            {
                okay = isl.TryEdit((Business)BusinessGrid.SelectedItem);
            }
            else
                msg = "Please Choose a Business to edit.";
            if (!okay)
                msg = "Cannot edit Business. Please try again later.";
            else if (String.Compare(msg, "") == 0)
                msg = "Business edited successfully.";
            MessageBox.Show(msg);
            BusinessGrid.ItemsSource = isl.generateBusinesses();
            BusinessGrid.Columns.RemoveAt(6);
            BusinessGrid.Columns.RemoveAt(5);
        }

        private void Remove_Click(object sender, RoutedEventArgs e)
        {
            String msg = "";
            Boolean okay = false;
            if (BusinessGrid.SelectedItem.GetType() == typeof(DataBase.Business))
            {
                okay = isl.TryRemove((Business)BusinessGrid.SelectedItem);
            }
            else
                msg = "Please Choose a Business to remove.";
            if (!okay)
                msg = "Cannot remove Business. Please try again later.";
            else if(String.Compare(msg, "") == 0)
                msg = "Business removed successfully.";
            MessageBox.Show(msg);
            BusinessGrid.ItemsSource = isl.generateBusinesses();
            BusinessGrid.Columns.RemoveAt(6);
            BusinessGrid.Columns.RemoveAt(5);
        }
    }
}
