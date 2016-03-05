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
    /// Interaction logic for MyBusinesses.xaml
    /// </summary>
    public partial class MyBusinesses : Window
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
        public MyBusinesses(LoggedInUser u)
        {
            InitializeComponent();
            user = u;
            Expand.IsEnabled = false;
            BusinessGrid.ItemsSource = isl.generateBusinessesByOwner(user);
            BusinessGrid.Visibility = Visibility.Hidden;
            BusinessGrid.SelectionMode = DataGridSelectionMode.Single;
            BusinessGrid.IsReadOnly = true;
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            Expand.IsEnabled = false;
            if (BusinessGrid.Columns.Count > 5)
            {
                BusinessGrid.Columns.RemoveAt(6);
                BusinessGrid.Columns.RemoveAt(5);
            }
            BusinessGrid.Visibility = Visibility.Visible;
        }

        private void Expand_Click(object sender, RoutedEventArgs e)
        {
            buisnessMenu BM = new buisnessMenu((DataBase.Business)BusinessGrid.SelectedItem, user);
            BM.Show();
            Close();
        }

        private void BusinessGrid_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            Expand.IsEnabled = true;
        }
    }
}
